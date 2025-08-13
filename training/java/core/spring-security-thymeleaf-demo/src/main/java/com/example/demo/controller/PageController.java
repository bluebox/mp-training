package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class PageController {

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/public")
	public String publicPage() {
		return "public";
	}

	@GetMapping("/user")
	public String userPage(Model model, Principal principal) {
		model.addAttribute("username", principal.getName());
		return "user";
	}

	@GetMapping("/admin")
	public String adminPage() {
		return "public"; // wont reach due to denyAll
	}

	@GetMapping("/submit-form")
	public String submitForm() {
		return "submit";
	}

	@PostMapping("/submit")
	public String submitData(@RequestParam String data, Model model) {
		model.addAttribute("message", "Submitted: " + data);
		return "result";
	}

	@GetMapping("/cause-error")
	public String causeError() {
		throw new ResourceNotFoundException("This resource does not exist!");
	}
}
