package taskmanager.domain.task;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import taskmanager.domain.task.model.QTaskDefinitionEntity;
import taskmanager.domain.task.model.QTaskEntity;
import taskmanager.domain.task.model.Task;
import taskmanager.domain.task.model.TaskDefinitionEntity;
import taskmanager.domain.task.model.TaskEntity;

public class TaskRepositoryImpl extends QuerydslRepositorySupport
    implements TaskRepositoryInterface {

  public TaskRepositoryImpl() {
    super(TaskEntity.class);
  }

  @Override
  public List<Task> getTasksByTaskDefinitionId(String taskDefinitionId) {
    QTaskEntity qTask = QTaskEntity.taskEntity;
    QTaskDefinitionEntity qTaskDefinition = QTaskDefinitionEntity.taskDefinitionEntity;

    List<TaskEntity> entities = from(qTask).innerJoin(qTask.taskDefinition, qTaskDefinition)
        .fetchJoin().where(qTaskDefinition.id.eq(taskDefinitionId)).select(qTask).fetch();

    return entities.stream().map(e -> Task.getInstance(e)).collect(Collectors.toList());
  }

  @Override
  public Long createTask(String taskDefinitionId) {
    QTaskDefinitionEntity qTaskDefinition = QTaskDefinitionEntity.taskDefinitionEntity;

    TaskDefinitionEntity taskDefinitionEntity =
        from(qTaskDefinition).where(qTaskDefinition.id.eq(taskDefinitionId)).fetchOne();

    TaskEntity entity = new TaskEntity();
    entity.setStatus("CREATED");
    entity.setTaskDefinition(taskDefinitionEntity);

    getEntityManager().persist(entity);

    return entity.getId();
  }

  @Override
  public Task updateTaskStatus(String taskId, String status) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteTask(String taskId) {
    // TODO Auto-generated method stub

  }

}
