package pl.sda.tasktodo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.tasktodo.entity.Student;
import pl.sda.tasktodo.entity.StudentTask;
import pl.sda.tasktodo.entity.Task;
import pl.sda.tasktodo.repository.StudentRepository;
import pl.sda.tasktodo.repository.StudentTaskRepository;
import pl.sda.tasktodo.repository.TaskRepository;

import java.time.LocalDateTime;

@SpringBootApplication
public class TaskToDoApplication implements CommandLineRunner {
    private final StudentTaskRepository studentTaskRepository;
    private final StudentRepository studentRepository;
    private final TaskRepository taskRepository;

    public TaskToDoApplication(StudentTaskRepository studentTaskRepository, StudentRepository studentRepository, TaskRepository taskRepository) {
        this.studentTaskRepository = studentTaskRepository;
        this.studentRepository = studentRepository;
        this.taskRepository = taskRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskToDoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }

    public void seedData(){
        final Task test = taskRepository.save(Task.builder()
                .name("Test")
                .description("Kliknij na link i wypełnij test: www.test.pl")
                .deadline(LocalDateTime.of(2022, 01, 30, 10, 0, 0))
                .max(10)
                .build());
        final Student student1 = studentRepository.save(Student.builder()
                .firstName("Adam")
                .lastName("Nowak")
                .build());
        final Student student2 = studentRepository.save(Student.builder()
                .firstName("Ewa")
                .lastName("Kowal")
                .build());
        studentTaskRepository.save(StudentTask.builder().task(test).student(student1).build());
        studentTaskRepository.save(StudentTask.builder().task(test).student(student2).build());


    }
}