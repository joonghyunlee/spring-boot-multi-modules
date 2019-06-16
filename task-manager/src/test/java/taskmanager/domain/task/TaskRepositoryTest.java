package taskmanager.domain.task;

import static org.junit.Assert.assertNotNull;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import lombok.extern.log4j.Log4j2;
import taskmanager.domain.task.model.Task;
import taskmanager.domain.task.model.TaskDefinition;

@Log4j2
@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@ActiveProfiles("test")
public class TaskRepositoryTest {
  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private TaskDefinitionRepository taskDefinitionRepository;

  private TaskDefinition taskDefinition;

  @Before
  public void init() {
    taskDefinition = new TaskDefinition();
    taskDefinition.setName("TEST-TASK");
    taskDefinition.setType("Cron");
    taskDefinition.setDescription("Test Task");

    taskDefinition = taskDefinitionRepository.createTaskDefinition(taskDefinition);
    log.debug(taskDefinition);
  }

  @Test
  public void getTaskRepositorySuccessfulTest() {
    // given
    Long taskId = taskRepository.createTask(taskDefinition.getId());
    log.debug(taskId);

    // when
    List<Task> tasks = taskRepository.getTasksByTaskDefinitionId(taskDefinition.getId());

    // then
    assertNotNull(tasks);
    log.debug(tasks);
  }
}
