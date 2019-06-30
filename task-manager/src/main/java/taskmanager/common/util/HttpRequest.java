package taskmanager.common.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HttpRequest<RequestBodyType> {
  private HttpMethod method;
  private String url;
  private HttpHeaders headers;
  private MultiValueMap<String, String> queries;

  private RequestBodyType requestBody;

  public HttpRequest<RequestBodyType> addQueryParameter(String key, String value) {
    if (this.queries == null) {
      this.queries = new LinkedMultiValueMap<>();
    }

    this.queries.add(key, value);

    return this;
  }

  public HttpRequest<RequestBodyType> addHeaders(HttpHeaders headers) {
    if (this.headers == null) {
      this.headers = new HttpHeaders();
    }

    for (String key : headers.keySet()) {
      this.headers.put(key, headers.get(key));
    }

    return this;
  }

  public HttpRequest<RequestBodyType> addHeader(String headerName, String headerValue) {
    if (this.headers == null) {
      this.headers = new HttpHeaders();
    }
    headers.add(headerName, headerValue);

    return this;
  }

}
