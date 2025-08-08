package com.example.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

	@RequestMapping(value= {"","/"})
	 public String displayHomePage(){
        return "Home.html";
    }
	
	@GetMapping("/{about}")
    public String handleDynamicSection(@PathVariable("about") String about, Model model) {
        model.addAttribute("about", about);
        return "about"; 
    }
}
