package com.medplus.marketing.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UiController {

	@GetMapping(value = {"/","/ui/**","/accessDenied"})
	public String uiMethod(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}
}
