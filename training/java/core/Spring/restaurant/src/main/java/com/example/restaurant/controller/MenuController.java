package com.example.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.restaurant.service.MenuService;

@Controller
public class MenuController {

	@Autowired
	private MenuService menuService;

	@GetMapping("/menu")
	public String displayMenu(Model model) {

		model.addAttribute("menuList", menuService.getMenu());
		return "menu.html";
	}
}
