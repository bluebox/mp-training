package com.example.Validation.controller;

import com.example.Validation.model.User;
import com.example.Validation.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "registrationForm";
	}

	@PostMapping("/user/register")
	public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "registrationForm";
		}
		userService.registerUser(user);
		model.addAttribute("message", "User registered successfully!");
		return "registrationForm";
	}
}
