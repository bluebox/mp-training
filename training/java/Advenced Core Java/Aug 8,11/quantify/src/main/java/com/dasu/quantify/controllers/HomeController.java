package com.dasu.quantify.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public String home(){
		return "home";
	}
	
	@RequestMapping("/login")
	public String signIn() {
		return "signin";
	}
	
	@RequestMapping("/signup")
	public String signUp() {
		return "signup";
	}
	
	@RequestMapping("/track")
	public String track() {
		return "trackcalories";
	}
	
	@RequestMapping("/admin")
	public String adminpage() {
		return "adminonly";
	}

}
