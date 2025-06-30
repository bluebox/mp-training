package com.example.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.practice.beans.Contact;
import com.example.practice.beans.Store;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ContactController {
	
	@GetMapping("/contact")
	public String ContactForm(Model model) {
		model.addAttribute("contact",new Contact());
		return "Contact";
	}
	
	@PostMapping("/save")
    public String saveForm(@Valid @ModelAttribute Contact contact,  Errors errors,Model model) {
        if (errors.hasErrors()) {
        	log.error("Validation errors: " + errors);
            model.addAttribute("contact", contact);
            return "Contact";
        }
        
        Store.add(contact);
        model.addAttribute("user", "Saketh");
        return "home";
    }

}
