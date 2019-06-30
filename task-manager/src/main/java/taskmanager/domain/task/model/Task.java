package taskmanager.domain.task.model;

import java.time.ZonedDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Task {
  @JsonProperty("created_at")
  private ZonedDateTime createdAt;

  @JsonProperty("updated_at")
  private ZonedDateTime updatedAt;

  @JsonProperty("deleted_at")
  private ZonedDateTime deletedAt;

  private Long id;
  private String status;

  @JsonProperty("task_definition_id")
  private String taskDefinitonId;

  public static Task getInstance(TaskEntity entity) {
    Task task = new Task();
    task.setCreatedAt(entity.getCreatedAt());
    task.setUpdatedAt(entity.getUpdatedAt());
    task.setDeletedAt(entity.getDeletedAt());
    task.setId(entity.getId());
    task.setTaskDefinitonId(entity.getTaskDefinition()
      .getId());
    task.setStatus(entity.getStatus()
      .toString());

    return task;
  }
}
