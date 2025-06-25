package com.example.HelloWorld.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {
	@RequestMapping("/home")
	public String Hello()
	{
		return "Home.html";
	}

}
