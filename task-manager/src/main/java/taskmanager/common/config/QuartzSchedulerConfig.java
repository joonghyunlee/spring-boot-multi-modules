package taskmanager.common.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import taskmanager.common.util.quartz.ContextAwareSpringBeanJobFactory;

@Configuration
public class QuartzSchedulerConfig {
  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private QuartzProperties quartzProperties;

  /**
   * create scheduler factory
   */
  @Bean
  public SchedulerFactoryBean schedulerFactoryBean() {
    ContextAwareSpringBeanJobFactory jobFactory = new ContextAwareSpringBeanJobFactory();
    jobFactory.setApplicationContext(applicationContext);

    Properties properties = new Properties();
    properties.putAll(quartzProperties.getProperties());

    SchedulerFactoryBean factory = new SchedulerFactoryBean();
    factory.setOverwriteExistingJobs(true);
    factory.setQuartzProperties(properties);
    factory.setJobFactory(jobFactory);
    return factory;
  }
}
