package pl.sda.tasktodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.tasktodo.config.AppConfiguration;
import pl.sda.tasktodo.config.IAppConfiguration;

@Controller
public class HomeController {
    private final IAppConfiguration configuration;

    public HomeController(IAppConfiguration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("pageSize", configuration.getPageSize());
        model.addAttribute("pageStart", configuration.getPageStart());
        return "index";
    }
}
