package taskmanager.common.util.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
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
    request.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_UTF8_VALUE);

    UriComponentsBuilder.fromHttpUrl(request.getUrl())
      .build()
      .toUriString();

    RequestBodySpec spec = webClient.method(request.getMethod())
      .uri(UriComponentsBuilder.fromHttpUrl(request.getUrl())
        .queryParams(request.getQueries())
        .build()
        .toUriString())
      .headers(headers -> headers.addAll(request.getHeaders()));

    if (request.getRequestBody() != null) {
      spec.body(BodyInserters.fromObject(request.getRequestBody()));
    }

    return spec.exchange()
      .block()
      .toEntity(responseType)
      .block();
  }

  public <RequestType, ResponseType> Mono<ResponseEntity<ResponseType>> asyncCall(
      HttpRequest<RequestType> request, Class<ResponseType> responseType) {
    request.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_UTF8_VALUE);

    UriComponentsBuilder.fromHttpUrl(request.getUrl())
      .build()
      .toUriString();

    RequestBodySpec spec = webClient.method(request.getMethod())
      .uri(UriComponentsBuilder.fromHttpUrl(request.getUrl())
        .queryParams(request.getQueries())
        .build()
        .toUriString())
      .headers(headers -> headers.addAll(request.getHeaders()));

    if (request.getRequestBody() != null) {
      spec.body(BodyInserters.fromObject(request.getRequestBody()));
    }

    return spec.exchange()
      .flatMap(response -> response.toEntity(responseType));
  }
}
