package com.example.springbootForm.controller;

import java.net.Authenticator.RequestorType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {
	
	@RequestMapping("/index")
	public String index(){
		return "index.html";
	}
	
}
