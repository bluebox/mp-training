package com.example.springbootdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello Hello";
	}
}
