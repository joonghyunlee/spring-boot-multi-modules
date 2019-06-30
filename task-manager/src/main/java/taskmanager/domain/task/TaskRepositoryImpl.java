package taskmanager.domain.task;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import taskmanager.domain.task.model.QTaskDefinitionEntity;
import taskmanager.domain.task.model.QTaskEntity;
import taskmanager.domain.task.model.Task;
import taskmanager.domain.task.model.TaskDefinitionEntity;
import taskmanager.domain.task.model.TaskEntity;
import taskmanager.domain.task.model.TaskStatus;

public class TaskRepositoryImpl extends QuerydslRepositorySupport
    implements TaskRepositoryInterface {
  @PersistenceContext
  private EntityManager em;

  public TaskRepositoryImpl() {
    super(TaskEntity.class);
  }

  @Override
  public List<Task> getTasksByTaskDefinitionId(String taskDefinitionId) {
    QTaskEntity qTask = QTaskEntity.taskEntity;
    QTaskDefinitionEntity qTaskDefinition = QTaskDefinitionEntity.taskDefinitionEntity;

    List<TaskEntity> entities = from(qTask).innerJoin(qTask.taskDefinition, qTaskDefinition)
      .fetchJoin()
      .where(qTaskDefinition.id.eq(taskDefinitionId))
      .select(qTask)
      .fetch();

    return entities.stream()
      .map(e -> Task.getInstance(e))
      .collect(Collectors.toList());
  }

  public List<Task> getRunnableTasks() {
    QTaskEntity qTask = QTaskEntity.taskEntity;
    QTaskDefinitionEntity qTaskDefinition = QTaskDefinitionEntity.taskDefinitionEntity;

    List<TaskEntity> entities = from(qTask).innerJoin(qTask.taskDefinition, qTaskDefinition)
      .fetchJoin()
      .where(qTask.status.eq(TaskStatus.CREATED))
      .limit(10)
      .orderBy(qTask.createdAt.asc())
      .select(qTask)
      .fetch();

    return entities.stream()
      .map(e -> Task.getInstance(e))
      .collect(Collectors.toList());
  }

  @Override
  public Task createTask(String taskDefinitionId) {
    QTaskDefinitionEntity qTaskDefinition = QTaskDefinitionEntity.taskDefinitionEntity;

    TaskDefinitionEntity taskDefinitionEntity =
        from(qTaskDefinition).where(qTaskDefinition.id.eq(taskDefinitionId))
          .fetchOne();

    TaskEntity entity = new TaskEntity();
    entity.setStatus(TaskStatus.CREATED);
    entity.setTaskDefinition(taskDefinitionEntity);

    em.persist(entity);

    return Task.getInstance(entity);
  }

  @Override
  public Task updateTaskStatus(long taskId, TaskStatus status) {
    QTaskEntity qTask = QTaskEntity.taskEntity;

    update(qTask).where(qTask.id.eq(taskId))
      .set(qTask.status, status)
      .execute();

    TaskEntity entity = em.find(TaskEntity.class, taskId);

    return Task.getInstance(entity);
  }

  @Override
  public void deleteTask(long taskId) {
    QTaskEntity qTask = QTaskEntity.taskEntity;

    delete(qTask).where(qTask.id.eq(taskId))
      .execute();
  }

}
