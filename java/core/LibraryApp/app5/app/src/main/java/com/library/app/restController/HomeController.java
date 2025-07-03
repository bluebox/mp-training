package com.library.app.restController;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping(value = {"","/","home"})
	public String homePage(Authentication authentication,Model model) {
		  model.addAttribute("username", authentication.getName());
	      model.addAttribute("roles", authentication.getAuthorities().toString());
		return "home.html";
	}
}
