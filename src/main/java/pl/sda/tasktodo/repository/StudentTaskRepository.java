package pl.sda.tasktodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.tasktodo.entity.Student;
import pl.sda.tasktodo.entity.StudentTask;

import java.util.List;

@Repository
public interface StudentTaskRepository extends JpaRepository<StudentTask, Long> {
    List<StudentTask> findByStudent(Student student);
}
