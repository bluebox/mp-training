package com.app.springDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping(value= {"","/","/home"})
	public String homeControllerPage() {
		return "home";
	}
}
