package core.common.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

public class HttpLoggingInterceptor implements ClientHttpRequestInterceptor {
  final Logger LOG = LoggerFactory.getLogger(this.getClass());

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body,
      ClientHttpRequestExecution execution) throws IOException {
    logRequest(request, body);
    ClientHttpResponse response = execution.execute(request, body);
    logResponse(response);

    return response;
  }

  private void logRequest(HttpRequest request, byte[] body) {
    StringBuilder builder = new StringBuilder();

    // Method & URI
    builder.append("curl -sX ");
    builder.append(request.getMethod());
    builder.append(" ");
    builder.append(request.getURI());
    builder.append(" \\ \n");

    // Header
    for (Entry<String, List<String>> entry : request.getHeaders().entrySet()) {
      for (String value : entry.getValue()) {
        builder.append("-H ");
        builder.append(entry.getKey());
        builder.append(":");
        builder.append(value);
      }
    }

    // Request Body
    builder.append(body);

    LOG.debug(builder.toString());
  }

  private void logResponse(ClientHttpResponse response) throws IOException {
    StringBuilder builder = new StringBuilder();

    // Header
    builder.append("\n");
    for (Entry<String, List<String>> entry : response.getHeaders().entrySet()) {
      builder.append(entry.getKey());
      builder.append(": ");
      for (String value : entry.getValue()) {
        builder.append(value);
        builder.append(",");
      }
      builder.deleteCharAt(builder.length() - 1);
    }

    // Response Body
    builder.append(StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
  }
}
