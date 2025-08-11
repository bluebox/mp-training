package dev.kaushik.springSecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoursesController {
	@GetMapping("/")
	public String home() {
		return "redirect:/courses";
	}

	@GetMapping("courses")
	public String viewCourses() {
		return "courses.html";
	}
}
