package pl.sda.tasktodo.service;

import org.springframework.stereotype.Service;
import pl.sda.tasktodo.entity.Student;
import pl.sda.tasktodo.entity.StudentTask;
import pl.sda.tasktodo.repository.StudentRepository;
import pl.sda.tasktodo.repository.StudentTaskRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class JpaStudentService implements StudentService{
    private final StudentRepository studentRepository;
    private final StudentTaskRepository studentTaskRepository;

    public JpaStudentService(StudentRepository studentRepository, StudentTaskRepository studentTaskRepository) {
        this.studentRepository = studentRepository;
        this.studentTaskRepository = studentTaskRepository;
    }

    @Override
    public List<StudentTask> findAllTaskForStudent(long studentId) {
        final Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            return studentTaskRepository.findByStudent(optionalStudent.get());
        }
        return Collections.emptyList();
    }

    @Override
    public void finishStudentTask(long studentId, StudentTask task) {

    }
}
