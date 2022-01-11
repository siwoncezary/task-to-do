package pl.sda.tasktodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.sda.tasktodo.service.StudentService;

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
        //model.addAttribute("task", );
        //TODO zdefiniować widok task-form;
        return "/student/task-form";
    }
}
