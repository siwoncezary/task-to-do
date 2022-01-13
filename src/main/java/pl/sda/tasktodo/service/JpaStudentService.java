package pl.sda.tasktodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.tasktodo.entity.Student;
import pl.sda.tasktodo.entity.StudentTask;
import pl.sda.tasktodo.repository.StudentRepository;
import pl.sda.tasktodo.repository.StudentTaskRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class JpaStudentService implements StudentService{
    private final StudentRepository studentRepository;
    private final StudentTaskRepository studentTaskRepository;
    @Autowired
    private LocalDateTime now;

    public JpaStudentService(StudentRepository studentRepository, StudentTaskRepository studentTaskRepository) {
        this.studentRepository = studentRepository;
        this.studentTaskRepository = studentTaskRepository;
    }

    @Override
    public Optional<StudentTask> findStudentTaskById(long id) {
        return studentTaskRepository.findById(id);
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
    @Transactional
    public void finishStudentTask(long studentId, StudentTask task) {
        final Optional<StudentTask> taskOptional = studentTaskRepository.findById(task.getId());
        if (taskOptional.isPresent()){
            var originalTask = taskOptional.get();
            originalTask.setContent(task.getContent());
            originalTask.setFinishDate(now);
            studentTaskRepository.save(originalTask);
        }
    }
}
