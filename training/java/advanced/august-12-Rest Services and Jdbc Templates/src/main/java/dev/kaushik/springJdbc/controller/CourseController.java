package dev.kaushik.springJdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.kaushik.springJdbc.service.CourseService;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService; 
    @GetMapping("/")
    public String home() {
        return "redirect:/courses";
    }

    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("courses", courseService.listCourses(null));
        return "courses.html";
    }
}
