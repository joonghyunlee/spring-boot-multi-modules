package taskmanager.domain.task;

import javax.annotation.PostConstruct;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;
import taskmanager.domain.action.HelloAction;

@Log4j2
@Service
public class TaskService {

  @Autowired
  private SchedulerFactoryBean schedulerFactory;

  @PostConstruct
  public void init() throws SchedulerException {
    Scheduler scheduler = schedulerFactory.getScheduler();

    log.debug("launch a main job");

    JobDetail jobDetail = JobBuilder.newJob(HelloAction.class)
      .withIdentity("main", Scheduler.DEFAULT_GROUP)
      .storeDurably()
      .build();

    Trigger trigger = TriggerBuilder.newTrigger()
      .startNow()
      .withIdentity("mainTrigger", Scheduler.DEFAULT_GROUP)
      .withSchedule(SimpleScheduleBuilder.simpleSchedule()
        .withIntervalInMinutes(1)
        .repeatForever())
      .build();

    scheduler.scheduleJob(jobDetail, trigger);
  }
}
