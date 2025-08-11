package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@GetMapping("/greet")
	public String greetUser(@RequestParam String name, @RequestParam(required = false) String city) {
		return "Hello, " + name + " from " + (city != null ? city : "hyderabad") + "!";
	}

	@GetMapping({ "/greet/{name}", "/greet/{name}/{city}" })
	public String greetUsingPath(@PathVariable String name, @PathVariable(required = false) String city) {
		return "Hello, " + name + " from " + (city != null ? city : "hyderabad") + "!";
	}

}
