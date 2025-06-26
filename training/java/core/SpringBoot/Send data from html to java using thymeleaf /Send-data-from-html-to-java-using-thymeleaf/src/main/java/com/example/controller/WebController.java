package com.example.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Repository
public class WebController {
	@RequestMapping("/home")
	public String webApp(Model m) {
		ArrayList<Integer> l=new ArrayList<Integer>();
		l.add(5);
		l.add(6);
		l.add(7);
		m.addAttribute("l",l);
		return "web.html";
	}
}
