package taskmanager.domain.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TaskService.class})
public class TaskServiceTest {
  @Autowired
  private TaskService taskService;

  @Test
  public void testTaskServiceTest() {
    String msg = taskService.testTaskService();

    log.debug(msg);
  }
}
