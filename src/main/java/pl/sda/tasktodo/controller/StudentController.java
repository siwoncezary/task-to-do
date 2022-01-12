package pl.sda.tasktodo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.tasktodo.entity.Student;
import pl.sda.tasktodo.entity.StudentTask;
import pl.sda.tasktodo.service.StudentService;

import java.util.Optional;

@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public String tasks(@AuthenticationPrincipal Student student, Model model){
        model.addAttribute("tasks", studentService.findAllTaskForStudent(student.getId()));
        return "/student/tasks";
    }

    @GetMapping("/student/task/{id}")
    public String showTaskForm(@PathVariable long id, Model model){
        //TODO dodać do serwisu wyciąganie jednego zadania
        final Optional<StudentTask> studentTask = studentService.findStudentTaskById(id);
        if (studentTask.isPresent()) {
            model.addAttribute("task", studentTask.get());
            return "/student/task-form";
        }
        model.addAttribute("message", "Nie ma takie zdania dla studenta!!!");
        return "error";
    }

    @PostMapping("/student/finish-task")
    public String finishStudentTask(@ModelAttribute StudentTask studentTask){
        studentService.finishStudentTask(0, studentTask);
        return "redirect:/student";
    }

    @GetMapping("/student/task-details/{id}")
    public String showTaskDetailsForm(@PathVariable long id, Model model){
        final Optional<StudentTask> taskOptional = studentService.findStudentTaskById(id);
        if (taskOptional.isPresent()){
            model.addAttribute("taskDetails", taskOptional.get().getTask());
            return "/student/task-details";
        }
        model.addAttribute("message", "Brak takiego zadania!");
        return "error";
    }
}
