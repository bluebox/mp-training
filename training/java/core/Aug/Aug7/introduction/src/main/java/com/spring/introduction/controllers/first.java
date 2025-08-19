package com.spring.introduction.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class first {

	@RequestMapping({ "", "/", "home" })
	public String print(Model model) {
		model.addAttribute("username", "satheesh");
		return "first.html";
	}
}
