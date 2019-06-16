package taskmanager.domain.task;

import java.util.List;
import taskmanager.domain.task.model.Task;

public interface TaskRepositoryInterface {
  List<Task> getTasksByTaskDefinitionId(String taskDefinitionId);

  Long createTask(String taskDefinitionId);

  Task updateTaskStatus(String taskId, String status);

  void deleteTask(String taskId);
}
