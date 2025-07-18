package com.example.vehicle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vehicle.model.User;
import com.example.vehicle.service.UserService;

@RestController
@RequestMapping("/user")

public class UserController {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	@Autowired
	public UserController(UserService userService,PasswordEncoder passwordEncoder) {
		this.userService=userService;
		this.passwordEncoder=passwordEncoder;
	}
	@PostMapping("/add")
	public String addUser(@RequestBody User u) throws Exception {
		return userService.addUser(u);
	}
	
	@PutMapping("/updatePassword")
	public String updatePassword(@RequestParam String username,@RequestParam String oldPassword,@RequestParam String password,@RequestParam String updatedBy) throws Exception {
		if(passwordEncoder.matches(oldPassword,userService.getUserByUsername(username).getPassword())) {
			return userService.updatePassword(username, password, updatedBy);	
		}
		System.out.println(userService.getUserByUsername(username).getPassword()+" "+oldPassword);
		return "Please enter correct password";
	}
	
	@GetMapping("/show")
	public User getUserById(@RequestParam String username,Authentication authentication) throws Exception {
		System.out.println(authentication.getDetails()+" "+authentication.getPrincipal()+" "+authentication.getAuthorities());
		return userService.getUserByUsername(username);
	}
//	@GetMapping("/check")
//	public String check(@RequestParam String username,@RequestParam String password) throws Exception {
//		return userService.checkCredentials(username, password);
//	}
	@GetMapping("/showAll")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	@DeleteMapping("/delete")
	public String deleteUser(String username) {
		return userService.deleteUser(username);
	}
}
