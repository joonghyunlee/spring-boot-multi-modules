package core.common.util;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

@Data
@Accessors(chain = true)
public class HttpRequest<RequestBodyType> {
  private HttpMethod method;
  private String url;
  private HttpHeaders headers;
  private MultiValueMap<String, String> queries;
  private RequestBodyType requestBody;
}
