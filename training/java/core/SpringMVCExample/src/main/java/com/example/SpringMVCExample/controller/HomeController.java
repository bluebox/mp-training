package com.example.SpringMVCExample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@GetMapping("home")
	public String getHome() {
		return "index.html";
	}

	@PostMapping("/addCustomer")
	public String addCustomer(@RequestParam String firstName, @RequestParam String secondName,
			@RequestParam String Email, Model model)

	{
		model.addAttribute("firstName", firstName);
		model.addAttribute("secondName", secondName);
		model.addAttribute("Email", Email);
		return "Success.html";

	}

}
