package taskmanager.domain.action;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import lombok.extern.log4j.Log4j2;
import taskmanager.common.util.http.HttpClient;
import taskmanager.common.util.http.HttpRequest;

@Log4j2
public class HelloAction extends QuartzJobBean {
  @Autowired
  private HttpClient httpClient;

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    // TODO Auto-generated method stub
    log.debug("Hello");

    HttpRequest<Void> request = new HttpRequest<Void>().setMethod(HttpMethod.GET)
      .setUrl("https://jsonplaceholder.typicode.com/users");

    ResponseEntity<String> response = httpClient.call(request, String.class);
    log.debug(response.getBody());
  }
}
