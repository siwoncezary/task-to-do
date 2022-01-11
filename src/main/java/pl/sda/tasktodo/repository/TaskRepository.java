package pl.sda.tasktodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.tasktodo.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
