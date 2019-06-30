package taskmanager.common;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;
import taskmanager.common.util.HttpClient;
import taskmanager.common.util.HttpRequest;

@Log4j2
@RunWith(SpringRunner.class)
@WebFluxTest
public class HttpClientTest {

  private HttpClient httpClient = new HttpClient(WebClient.builder());

  @Test
  public void getInvokeApiTest() {
    // given
    HttpRequest<Void> request = new HttpRequest<Void>().setMethod(HttpMethod.GET)
      .setUrl("https://jsonplaceholder.typicode.com/users");

    // when
    ResponseEntity<String> response = httpClient.call(request, String.class);

    // then
    log.debug(response);
  }

  @Test
  public void getInvokeMultipleApiTest() {
    // given
    HttpRequest<Void> request = new HttpRequest<Void>().setMethod(HttpMethod.GET)
      .setUrl("https://jsonplaceholder.typicode.com/users");

    // when
    List<ResponseEntity<String>> responses = new ArrayList<>();

    Long startTime = Instant.now()
      .toEpochMilli();
    responses.add(httpClient.call(request, String.class));
    Long endTime = Instant.now()
      .toEpochMilli();
    Long elapsedTime = endTime - startTime;
    log.debug(elapsedTime);

    startTime = Instant.now()
      .toEpochMilli();
    responses.add(httpClient.call(request, String.class));
    endTime = Instant.now()
      .toEpochMilli();
    elapsedTime = endTime - startTime;
    log.debug(elapsedTime);

    startTime = Instant.now()
      .toEpochMilli();
    responses.add(httpClient.call(request, String.class));
    endTime = Instant.now()
      .toEpochMilli();
    elapsedTime = endTime - startTime;
    log.debug(elapsedTime);

    // then
    startTime = Instant.now()
      .toEpochMilli();
    log.debug(responses);
    endTime = Instant.now()
      .toEpochMilli();
    elapsedTime = endTime - startTime;
    log.debug(elapsedTime);
  }

  @Test
  public void getInvokeSimultaneouslyMultipleApiTest() {
    // given
    HttpRequest<Void> request = new HttpRequest<Void>().setMethod(HttpMethod.GET)
      .setUrl("https://jsonplaceholder.typicode.com/users");

    // when
    List<Mono<ResponseEntity<String>>> responses = new ArrayList<>();

    Long startTime = Instant.now()
      .toEpochMilli();
    responses.add(httpClient.asyncCall(request, String.class));
    Long endTime = Instant.now()
      .toEpochMilli();
    Long elapsedTime = endTime - startTime;
    log.debug(elapsedTime);

    startTime = Instant.now()
      .toEpochMilli();
    responses.add(httpClient.asyncCall(request, String.class));
    endTime = Instant.now()
      .toEpochMilli();
    elapsedTime = endTime - startTime;
    log.debug(elapsedTime);

    startTime = Instant.now()
      .toEpochMilli();
    responses.add(httpClient.asyncCall(request, String.class));
    endTime = Instant.now()
      .toEpochMilli();
    elapsedTime = endTime - startTime;
    log.debug(elapsedTime);

    // then
    Mono.when(responses)
      .block();

    startTime = Instant.now()
      .toEpochMilli();
    responses.stream()
      .forEach(response -> {
        log.debug(response.block());
      });
    endTime = Instant.now()
      .toEpochMilli();
    elapsedTime = endTime - startTime;
    log.debug(elapsedTime);
  }
}
