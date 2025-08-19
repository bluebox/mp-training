package com.springboot_practice.first_springboot_application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	
	@RequestMapping(value= {""})
	public String displayHomePage(Model model) {
		model.addAttribute("username","Rohit Varma Datla");
		return "home.html";
	}
}
