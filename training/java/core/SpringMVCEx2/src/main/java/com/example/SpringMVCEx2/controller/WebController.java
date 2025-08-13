package com.example.SpringMVCEx2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

	@GetMapping("/")
	public String HomePage() {
		return "index.html";
	}

	@PostMapping("addEmployee")
	public String addEmployee(@RequestParam String name, @RequestParam String occupation, @RequestParam Integer age,
			@RequestParam String address, Model model) {

		model.addAttribute("name", name);
		model.addAttribute("occupation", occupation);
		model.addAttribute("age", age);
		model.addAttribute("address", address);
		return "Success.html";
	}

}
