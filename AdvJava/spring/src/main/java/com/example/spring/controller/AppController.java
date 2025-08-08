package com.example.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AppController {
	
	@RequestMapping(value={"","/","index"})
	public String home(Model model) {
		model.addAttribute("username", "Karthik M");
		return "index.html";
	}

}
