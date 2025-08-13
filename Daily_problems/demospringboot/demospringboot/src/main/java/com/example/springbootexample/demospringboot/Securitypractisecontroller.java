package com.example.springbootexample.demospringboot;

import java.util.ArrayList;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
public class Securitypractisecontroller {
	List<String> list=new ArrayList<>();
	@GetMapping("/Home/checkauth")
	public String getcheckauth() {
		
		return "this is user";
	}
	@PostMapping("/Home/checkauth")
	public String postcheckauth(@RequestBody String li) {
		list.add(li);
		return "this is user"+list.toString();
	}
	@GetMapping("/Home/checktoken")
	public CsrfToken getchecktoken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
		
		
	}
	
	

}
