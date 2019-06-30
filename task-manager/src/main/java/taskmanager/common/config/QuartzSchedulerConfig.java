package taskmanager.common.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import taskmanager.common.util.quartz.ContextAwareSpringBeanJobFactory;

@Configuration
public class QuartzSchedulerConfig {
  @Autowired
  private DataSource dataSource;

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private QuartzProperties quartzProperties;

  @Bean
  public SpringBeanJobFactory jobFactory() {
    ContextAwareSpringBeanJobFactory jobFactory = new ContextAwareSpringBeanJobFactory();
    jobFactory.setApplicationContext(applicationContext);
    return jobFactory;
  }

  /**
   * create scheduler factory
   */
  @Bean
  public SchedulerFactoryBean schedulerFactoryBean() {
    Properties properties = new Properties();
    properties.putAll(quartzProperties.getProperties());

    SchedulerFactoryBean factory = new SchedulerFactoryBean();
    factory.setOverwriteExistingJobs(true);
    factory.setDataSource(dataSource);
    factory.setQuartzProperties(properties);
    factory.setJobFactory(jobFactory());
    return factory;
  }
}
