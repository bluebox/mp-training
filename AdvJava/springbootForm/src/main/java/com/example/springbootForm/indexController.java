package com.example.springbootForm;

import java.net.Authenticator.RequestorType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {
	
	@GetMapping("/")
	public String index(){
		return "HTML files are in resources/static ... try /index.html to see";
		
	}
	
	@GetMapping("/data")
	public String getdata() {
		String name = "Karthik";
		
		return name;
	}
	
	

}
