package com.eazybytes.example_18.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;


@Controller
public class MyController {
	
	@RequestMapping("/home")
	public String home() {
		return "home.html";
	}
	

}
