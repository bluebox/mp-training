package com.spring.gym.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberConfig {
	
	@GetMapping(value = {"","/","/home"})
	public String addMember() {
		return "Hello";
	}

}

