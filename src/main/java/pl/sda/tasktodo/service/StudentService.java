package pl.sda.tasktodo.service;

import pl.sda.tasktodo.entity.StudentTask;

import java.util.List;

public interface StudentService {

    List<StudentTask> findAllTaskForStudent(long studentId);
    void finishStudentTask(long studentId, StudentTask task);
}
