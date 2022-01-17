package pl.sda.tasktodo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.sda.tasktodo.config.AppConfiguration;
import pl.sda.tasktodo.config.IAppConfiguration;
import pl.sda.tasktodo.dto.StudentTaskDto;
import pl.sda.tasktodo.entity.Student;
import pl.sda.tasktodo.entity.StudentTask;
import pl.sda.tasktodo.mapper.StudentTaskMapper;
import pl.sda.tasktodo.service.StudentService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class StudentController {
    private final StudentService studentService;
    private final IAppConfiguration configuration;

    public StudentController(StudentService studentService, IAppConfiguration configuration) {
        this.studentService = studentService;
        this.configuration = configuration;
    }

    @GetMapping("/student")
    public String tasks(@AuthenticationPrincipal Student student, Model model){
        model.addAttribute("tableColor", configuration.getTableColor() + " table");
        model.addAttribute("tasks", studentService.findAllTaskForStudent(student.getId()));
        return "./student/tasks";
    }

    @GetMapping("/student/task/{id}")
    public String showTaskForm(@PathVariable long id, Model model){
        //TODO dodać do serwisu wyciąganie jednego zadania
        final Optional<StudentTask> studentTask = studentService.findStudentTaskById(id);
        if (studentTask.isPresent()) {
            model.addAttribute("task", StudentTaskMapper.toDto(studentTask.get()));
            return "./student/task-form";
        }
        model.addAttribute("message", "Nie ma takie zdania dla studenta!!!");
        return "error";
    }

    @PostMapping("/student/finish-task")
    public String finishStudentTask(@Valid @ModelAttribute  StudentTaskDto dto, Errors errors, Model model){

        if (errors.hasErrors()) {
                model.addAttribute("task", dto);
                model.addAttribute("errors", errors);
                return "./student/task-form";
        }
        studentService.finishStudentTask(0, StudentTask.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .build());
        return "redirect:./student";
    }

    @GetMapping("/student/task-details/{id}")
    public String showTaskDetailsForm(@PathVariable long id, Model model){
        final Optional<StudentTask> taskOptional = studentService.findStudentTaskById(id);
        if (taskOptional.isPresent()){
            model.addAttribute("taskDetails", taskOptional.get().getTask());
            return "./student/task-details";
        }
        model.addAttribute("message", "Brak takiego zadania!");
        return "error";
    }
}
