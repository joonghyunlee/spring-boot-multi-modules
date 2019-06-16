package taskmanager.domain.task.model;

import java.util.UUID;
import javax.jdo.annotations.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "task_definition")
@Data
public class TaskDefinitionEntity {
  @Id
  String id = UUID.randomUUID().toString();

  @Column
  String name;

  @Column
  String type;

  @Column
  String description;
}
