package com.example.SpringBootExample2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("home")
	public String homePage()
	{
		return "Home.html";
	}
	@GetMapping("header")
	public String headerPage()
	{
		return "header.html";
	}
	
	@GetMapping("footer")
	public String footerPage()
	{
		return "footer.html";
	}
	
	
}
