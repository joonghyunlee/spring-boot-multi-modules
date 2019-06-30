package taskmanager.domain.task;

import java.util.List;
import taskmanager.domain.task.model.Task;
import taskmanager.domain.task.model.TaskStatus;

public interface TaskRepositoryInterface {
  List<Task> getTasksByTaskDefinitionId(String taskDefinitionId);

  public List<Task> getRunnableTasks();

  Task createTask(String taskDefinitionId);

  Task updateTaskStatus(long taskId, TaskStatus status);

  void deleteTask(long taskId);
}
