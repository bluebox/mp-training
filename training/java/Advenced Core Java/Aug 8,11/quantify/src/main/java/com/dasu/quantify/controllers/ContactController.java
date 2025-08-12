package com.dasu.quantify.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dasu.quantify.models.User;

import jakarta.validation.Valid;

@Controller
public class ContactController {
	@RequestMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@PostMapping("/send-continfo")
	public String processContactInfo(@Valid @ModelAttribute User userObj,
									BindingResult bindingResult) 
	{
		System.out.println("_"+userObj.getName()+"_");
		
		System.out.println(bindingResult.getAllErrors());
		if(bindingResult.hasErrors()) {
	        return "contact";	
	    }
		return "home";
	}

}

