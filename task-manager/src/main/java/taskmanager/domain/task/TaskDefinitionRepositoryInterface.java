package taskmanager.domain.task;

import taskmanager.domain.task.model.TaskDefinition;

public interface TaskDefinitionRepositoryInterface {
  TaskDefinition getTaskDefinitionById(String id);

  TaskDefinition createTaskDefinition(TaskDefinition taskDefinition);

  void deleteTaskDefinition(String id);
}
