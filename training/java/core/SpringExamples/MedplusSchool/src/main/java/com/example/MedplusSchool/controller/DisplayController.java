package com.example.MedplusSchool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DisplayController {
	
	
@RequestMapping("/home")
public String showHomePage() {
	return "home.html";
}
}
