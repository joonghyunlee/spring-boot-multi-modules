package taskmanager.domain.action;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import org.junit.Test;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloActionTest {
  @Test
  public void helloActionTest() throws SchedulerException, InterruptedException {
    JobDetail jobDetail = newJob(HelloAction.class).build();

    Trigger trigger = newTrigger().build();

    Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
    defaultScheduler.start();
    defaultScheduler.scheduleJob(jobDetail, trigger);
    Thread.sleep(3 * 1000);

    defaultScheduler.shutdown(true);
  }
}
