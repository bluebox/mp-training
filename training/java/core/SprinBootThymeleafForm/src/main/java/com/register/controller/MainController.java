package com.register.controller;

import com.register.model.User;
import com.register.repository.UserRepo;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // Import BindingResult
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	
	private final UserRepo repo;
	
	public MainController(UserRepo repo) {
		this.repo=repo;
	}

    @RequestMapping("/")
    public String mainPage() {
        return "home.html";
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register_form";
    }

    @PostMapping("/register")
    public String submitForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            
            return "register_form";
        }
        repo.save(user);
       List<User>li= repo.findAll();
        System.out.println(li);
        return "register_success";
    }
}