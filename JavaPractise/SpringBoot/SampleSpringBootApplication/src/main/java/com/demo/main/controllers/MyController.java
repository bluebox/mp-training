package com.demo.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
	@RequestMapping("/home")
	public String getMessage() {
		return "home.html";
	}
}
