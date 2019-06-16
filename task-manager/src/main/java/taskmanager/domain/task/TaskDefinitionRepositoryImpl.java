package taskmanager.domain.task;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import taskmanager.domain.task.model.QTaskDefinitionEntity;
import taskmanager.domain.task.model.TaskDefinition;
import taskmanager.domain.task.model.TaskDefinitionEntity;

@Repository
public class TaskDefinitionRepositoryImpl extends QuerydslRepositorySupport
    implements TaskDefinitionRepositoryInterface {

  public TaskDefinitionRepositoryImpl() {
    super(TaskDefinitionEntity.class);
  }

  @Override
  public TaskDefinition getTaskDefinitionById(String id) {
    QTaskDefinitionEntity taskDefinition = QTaskDefinitionEntity.taskDefinitionEntity;

    TaskDefinitionEntity entity = from(taskDefinition).where(taskDefinition.id.eq(id)).fetchOne();

    return TaskDefinition.getInstance(entity);
  }

  @Override
  public TaskDefinition createTaskDefinition(TaskDefinition taskDefinition) {
    TaskDefinitionEntity entity = new TaskDefinitionEntity();
    entity.setName(taskDefinition.getName());
    entity.setType(taskDefinition.getType());
    entity.setDescription(taskDefinition.getDescription());

    getEntityManager().persist(entity);

    return TaskDefinition.getInstance(entity);
  }

  @Override
  public void deleteTaskDefinition(String id) {
    // TODO Auto-generated method stub

  }

}
