package com.example.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring.model.User;
import com.example.spring.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
//	private List<User> userList = new ArrayList<User>();
	
//	@RequestMapping(value = "/user",method = GET)
//	public String user() {
//		return "user";
//	}
//	
//	
//    @RequestMapping(value = "/saveMsg")
//	public String saveUser(@RequestParam(required = false) String username,
//			@RequestParam(required = false) Integer age, @RequestParam(required = false)  String pnum,@RequestParam(required = false) String email,
//			Model model) {
//    	
//    	log.info("Username: {}", username);
//        log.info("Age: {}", age);
//        log.info("Phone: {}", pnum);
//        log.info("Email: {}", email);
//        
//        userList.add(new User(username,age,pnum,email));
//        
//        model.addAttribute("users",userList);
//        
//		
//        return "user";
//	}
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService=userService;
	}
    
    @GetMapping("/user")
    public String userForm(Model model) {
        model.addAttribute("user", new User()); 
        return "user"; 
    }

    @PostMapping("/saveMsg")
    public String saveUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user"; 
        }
        model.addAttribute("user", new User()); 
        userService.saveUsers(user);
        model.addAttribute("users", user); 

        
        return "user";
        
//        return "UserData"; 
    }
    
//    @RequestMapping("/usersdata")
//    public List<User> displayMessages() {
//        List<User> usersdata = userService.findUsers();
//        return usersdata;
//    }
    
    @GetMapping("/usersdata") 
    @ResponseBody
    public List<User> displayMessages() {
        List<User> usersdata = userService.findUsers();
        return usersdata;
    }
	

}


