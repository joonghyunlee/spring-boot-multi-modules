package taskmanager.domain.task;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class CommandLineLauncherFlowConfiguration {
  public static final String FLOW_NAME = "COMMAND_LAUNCHER";

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job commandLauncherFlow() {
    return jobBuilderFactory.get(FLOW_NAME)
      .start(logStart())
      .build();
  }

  @Bean
  @JobScope
  public Step logStart() {
    return stepBuilderFactory.get("logStart")
      .tasklet((contribution, chunkContext) -> {
        log.debug("Start Command");

        return RepeatStatus.FINISHED;
      })
      .build();

  }
}
