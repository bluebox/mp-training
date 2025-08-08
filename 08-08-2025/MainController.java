package com.mycompany.Company.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	

	@RequestMapping(value={"home"})
	public String display(Model model) {
		model.addAttribute("name", "Prana");
		return "home.html";
	}
	
	@RequestMapping(value={"", "/", "Main"})
	public String display() {
		return "Main.html";
	}
}
