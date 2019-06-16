package taskmanager.domain.task;

import org.springframework.data.jpa.repository.JpaRepository;
import taskmanager.domain.task.model.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long>, TaskRepositoryInterface {

}
