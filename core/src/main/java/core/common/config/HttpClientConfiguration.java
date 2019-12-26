package core.common.config;

import core.common.util.HttpClient;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.common.util.CustomObjectMapper;
import core.common.util.HttpLoggingInterceptor;

@Configuration
@EnableWebMvc
public class HttpClientConfiguration implements WebMvcConfigurer {
  @Bean
  public ObjectMapper objectMapper() {
    return configureObjectMapper(new CustomObjectMapper());
  }

  private ObjectMapper configureObjectMapper(ObjectMapper objectMapper) {
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);

    return objectMapper;
  }

  @Bean
  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
    MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
    jsonConverter.setObjectMapper(configureObjectMapper(objectMapper()));
    return jsonConverter;
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(mappingJackson2HttpMessageConverter());
  }

  @Bean
  public RestTemplate restTemplate() {
    ObjectMapper objectMapper = objectMapper();

    HttpLoggingInterceptor httpLoggingRequestInterceptor = new HttpLoggingInterceptor();
    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
    interceptors.add(httpLoggingRequestInterceptor);

    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(new InterceptingClientHttpRequestFactory(
        new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory()),
        interceptors));

    for (HttpMessageConverter<?> converter : restTemplate.getMessageConverters()) {
      if (converter.getClass().equals(MappingJackson2HttpMessageConverter.class)) {
        ((MappingJackson2HttpMessageConverter) converter).setObjectMapper(objectMapper);
      }
    }
    return restTemplate;
  }

  @Bean
  public HttpClient httpClient() {
    return new HttpClient(WebClient.builder());
  }
}
