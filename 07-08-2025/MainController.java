package com.mycompany.Company.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping(value={"", "/", "Main"})
	public String display() {
		return "Main.html";
	}
}
