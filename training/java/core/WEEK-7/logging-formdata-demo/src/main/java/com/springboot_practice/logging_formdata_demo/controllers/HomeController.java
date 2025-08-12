package com.springboot_practice.logging_formdata_demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;

@Controller
public class HomeController {
	
	@RequestMapping(value="")
	public String displayHomePage(Model model) {
		return "home.html";
	}
}
