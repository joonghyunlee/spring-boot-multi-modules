package taskmanager.domain.task;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import lombok.extern.log4j.Log4j2;
import taskmanager.domain.task.model.TaskDefinition;

@Log4j2
@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@ActiveProfiles("test")
public class TaskDefinitionRepsitoryTest {
  @Autowired
  private TaskDefinitionRepository taskDefinitionRepository;

  @Test
  public void getTaskDefinitionSuccessfulTest() {
    // given
    TaskDefinition taskDefinition = new TaskDefinition();
    taskDefinition.setName("TEST-TASK");
    taskDefinition.setType("Cron");
    taskDefinition.setDescription("Test Task");
    String taskDefinitionId = taskDefinitionRepository.createTaskDefinition(taskDefinition).getId();

    // when
    TaskDefinition resp = taskDefinitionRepository.getTaskDefinitionById(taskDefinitionId);

    // then
    assertNotNull(resp);
    log.debug(resp);
  }
}
