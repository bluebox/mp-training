package com.example.SpringSecurityExample;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
	@RequestMapping("/")
	public String homePage(HttpServletRequest req) {
		return "hello" + req.getSession().getId();
	}

}
