package com.example.demo.controller;

import com.example.demo.models.FoodItem;

import com.example.demo.service.FoodService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FoodController {
	

	
	
	@Autowired
    private  FoodService foodService;
    
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("foodItem", new FoodItem());
        return "add";
    }

    @PostMapping("/add")
    public String addFood(@Valid @ModelAttribute FoodItem foodItem, BindingResult result) {
        if (result.hasErrors()) 
        {
            log.warn("Validation errors: {}", result.getAllErrors());
            return "add";
        }
        
        foodService.addFoodItem(foodItem);
        return "redirect:/";
    }

    @GetMapping("/view/{category}")
    public String viewCategory(@PathVariable String category, Model model)
    {
   // List<FoodItem>fi=	repo.allItems(category);
        model.addAttribute("items", foodService.getByCategory(category));
        model.addAttribute("category", category);
        return "view";
    }
}
