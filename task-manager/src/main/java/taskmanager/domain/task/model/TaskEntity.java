package taskmanager.domain.task.model;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;

@Entity
@Table(name = "task")
@Data
public class TaskEntity {
  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  @Column(name = "deleted_at")
  private ZonedDateTime deletedAt;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  @Enumerated(EnumType.STRING)
  private TaskStatus status;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "task_definition_id")
  private TaskDefinitionEntity taskDefinition;
}
