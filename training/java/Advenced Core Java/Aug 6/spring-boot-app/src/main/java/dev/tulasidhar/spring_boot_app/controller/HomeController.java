package dev.tulasidhar.spring_boot_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
	
	@RequestMapping("/page")
	public String homeController(Model model) {
		model.addAttribute("name", "Tulasihdar");
		return "home";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<String> postController(@RequestParam String name) {
		System.out.println(name);
		return ResponseEntity.ok("ok");
	}
}
