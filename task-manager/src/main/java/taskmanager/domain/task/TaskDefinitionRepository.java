package taskmanager.domain.task;

import org.springframework.data.jpa.repository.JpaRepository;
import taskmanager.domain.task.model.TaskDefinitionEntity;

public interface TaskDefinitionRepository
    extends JpaRepository<TaskDefinitionEntity, String>, TaskDefinitionRepositoryInterface {
}
