package com.mvcExample.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mvcExample.spring.service.MenuService;

@Controller
public class HomeController {


	@RequestMapping(value = {"/","home"}, method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public String user(Model model) {
		model.addAttribute("menuList", MenuService.getMenu());
		return "menu";
	}
}
