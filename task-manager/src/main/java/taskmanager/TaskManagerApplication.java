package taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "taskmanager")
public class TaskManagerApplication {
  public static void main(String[] args) {
    SpringApplication.run(TaskManagerApplication.class, args);
  }
}
