package com.example.Student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Student.model.Student;
import com.example.Student.repo.StudentRepo;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/home")
public class RegistrationController {
	
	@Autowired
	private StudentRepo repo;

	@RequestMapping(value = { "", "/" })
	public String displayHomePage() {
		return "home";
	}

	@RequestMapping("/index")
	public String displayIndexPage(Model model) {
		model.addAttribute("student", new Student());
		return "index";
	}

	@GetMapping("/save")
	public String handleDynamicSection(@Valid @ModelAttribute Student student, BindingResult errors,
			RedirectAttributes redirectAttributes) {
		System.out.println(errors.getErrorCount());
		if (errors.hasErrors()) {
			return "index";
		}
		redirectAttributes.addFlashAttribute("student", student);
		repo.save(student);
		return "redirect:/home/details";
	}
	
	@PostMapping("/api/save")
	public String handleDynamicSections(@RequestBody Student student){
		repo.save(student);
		return "Success";
		
	}

	@GetMapping("/details")
	public String showDetails() {
		return "details";
	}

	@GetMapping("/view")
	public String viewAll(Model model) {
		model.addAttribute("studentList", repo.viewAll());
		return "view";
	}
	
	@GetMapping("/viewu")
	public List<Student> viewAllu(Model model) {
		model.addAttribute("studentList", repo.viewAll());
		return repo.viewAll();
	}
}