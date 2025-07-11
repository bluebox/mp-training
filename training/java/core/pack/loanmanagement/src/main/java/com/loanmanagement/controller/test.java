package com.loanmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {
 
	@GetMapping("/test")
	public String testing() {
		return "working efficiently";
	}
}
