package taskmanager.domain.action;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class HelloAction implements Job {
  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    log.debug("Hello Action");
  }
}
