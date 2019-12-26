package core.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Slf4j
public class HttpClient {

  private final WebClient webClient;

  public HttpClient(WebClient.Builder webClientBuilder) {
    this.webClient =
        webClientBuilder.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_UTF8_VALUE)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            .build();
  }

  public <RequestType, ResponseType> ResponseEntity<ResponseType> call(
      HttpRequest<RequestType> request, Class<ResponseType> responseType) {
    return this.asyncCall(request, responseType).block();
  }

  public <RequestType, ResponseType> Mono<ResponseEntity<ResponseType>> asyncCall(
      HttpRequest<RequestType> request, Class<ResponseType> responseType) {
    WebClient.RequestBodySpec spec = webClient.method(request.getMethod())
        .uri(UriComponentsBuilder.fromHttpUrl(request.getUrl())
            .queryParams(request.getQueries()).build().toUriString())
        .headers(headers -> headers.addAll(request.getHeaders()));

    if (request.getRequestBody() != null) {
      spec.body(BodyInserters.fromObject(request.getRequestBody()));
    }

    return spec.exchange()
        .flatMap(response -> response.toEntity(responseType))
        .doOnNext(
            response -> {
              StringBuilder sb = new StringBuilder();
              logRequest(sb, request);
              logResponse(sb, response);
            }
        );
  }

  private void logRequest(StringBuilder sb, HttpRequest<?> request) {
    sb.append(request.getMethod());
    sb.append(" ");
    sb.append(request.getUrl());

    // Query
    if (request.getQueries() != null && !request.getQueries().isEmpty()) {
      request.getQueries().entrySet()
          .forEach(query -> query.getValue()
              .forEach(value -> {
                sb.append(query.getKey());
                sb.append("=");
                sb.append(value);
                sb.append("&");
              }));
    }

    // Header
    request.getHeaders().entrySet().forEach(entry -> {
      sb.append("\t");
      sb.append(entry.getKey());
      sb.append(":");
      entry.getValue().forEach(value -> sb.append(value));
      sb.append("\n");
    });

    // Body
    sb.append(request.getRequestBody().toString());
  }

  private void logResponse(StringBuilder sb, ResponseEntity<?> responseEntity) {
    // HTTP Status Code
    sb.append(responseEntity.getStatusCodeValue());
    sb.append("\n");

    // Header
    responseEntity.getHeaders().forEach((key, value1) -> {
      sb.append("\t");
      sb.append(key);
      sb.append(":");
      value1.forEach(value -> sb.append(value));
      sb.append("\n");
    });

    // Body
    if (responseEntity.getBody() != null) {
      sb.append(responseEntity.getBody().toString());
    }
  }
}
