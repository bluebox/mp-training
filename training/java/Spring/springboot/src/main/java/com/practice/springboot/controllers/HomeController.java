package com.practice.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

//	@RequestMapping("/")
	@RequestMapping(value= {"/","/home"})
	public String display() {
		return "home.html";
	}
}
