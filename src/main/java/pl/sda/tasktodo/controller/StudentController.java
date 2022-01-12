package pl.sda.tasktodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.tasktodo.entity.StudentTask;
import pl.sda.tasktodo.service.StudentService;

import java.util.Optional;

@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/{id}")
    public String tasks(@PathVariable long id, Model model){
        model.addAttribute("tasks", studentService.findAllTaskForStudent(id));
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
        return "redirect:/student/" + studentTask.getId();
    }
}
