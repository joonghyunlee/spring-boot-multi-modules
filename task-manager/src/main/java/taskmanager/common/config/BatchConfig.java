package taskmanager.common.config;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {
  @Bean
  public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
    JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
    jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
    return jobRegistryBeanPostProcessor;
  }

  // MapJobRepositoryFactoryBean 주입하기 위한 필수 Bean
  @Bean
  public ResourcelessTransactionManager transactionManager() {
    return new ResourcelessTransactionManager();
  }

  @Bean
  public JobRepository jobRepository() throws Exception {
    return new MapJobRepositoryFactoryBean(transactionManager()).getObject();
  }

  @Bean
  public SimpleJobLauncher jobLauncher() throws Exception {
    SimpleJobLauncher launcher = new SimpleJobLauncher();
    launcher.setJobRepository(jobRepository());
    return launcher;
  }
}
