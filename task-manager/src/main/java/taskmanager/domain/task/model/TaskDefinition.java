package taskmanager.domain.task.model;

import lombok.Data;

@Data
public class TaskDefinition {
  String id;
  String name;
  String type;
  String description;

  public static TaskDefinition getInstance(TaskDefinitionEntity entity) {
    TaskDefinition taskDefinition = new TaskDefinition();
    taskDefinition.setId(entity.getId());
    taskDefinition.setName(entity.getName());
    taskDefinition.setType(entity.getType());
    taskDefinition.setDescription(entity.getDescription());

    return taskDefinition;
  }
}
