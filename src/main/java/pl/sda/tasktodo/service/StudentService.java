package pl.sda.tasktodo.service;

import pl.sda.tasktodo.entity.StudentTask;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<StudentTask> findStudentTaskById(long id);
    List<StudentTask> findAllTaskForStudent(long studentId);
    void finishStudentTask(long studentId, StudentTask task);
}
