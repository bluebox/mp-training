package com.example.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
	@RequestMapping("/home")
	public String webApp() {
		return "web.html";
	}
	@RequestMapping("/hi")
	public String show(Model m,@RequestParam(required=false) String name,@RequestParam(required=false) int age,@RequestParam(required=false) String sType,@RequestParam(required=false) String sname,@RequestParam(required=false) String marks) {
		m.addAttribute("name",name);
		m.addAttribute("age",age);
		m.addAttribute("stype", sType);
		m.addAttribute("sname", sname);
		m.addAttribute("marks", marks);
		return "thanks.html";
	}
}
