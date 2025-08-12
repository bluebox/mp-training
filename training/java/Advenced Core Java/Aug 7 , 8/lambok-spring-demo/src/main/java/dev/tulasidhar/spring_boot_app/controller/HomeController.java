package dev.tulasidhar.spring_boot_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.tulasidhar.spring_book_app.service.UserService;
import dev.tulasidhar.spring_boot_app.models.User;
import jakarta.websocket.server.PathParam;


@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/page")
	public String homeController(Model model) {
		User user = new User();
		model.addAttribute("userObj",user);
		model.addAttribute("name", "Tulasihdar");
		return "home";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String postController(@ModelAttribute User userObj) {
		userService.addUser(userObj);
		return "home";
	}
	
	
	//testing request params in url
	@RequestMapping("/test")
	public String testController(@PathParam(value="name") String name) {
		User user = new User();
		user.setName(name);
		
		userService.addUser(user);
		
		return "home";
		
	}
	
}
