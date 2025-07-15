package com.example.vehicle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	private PasswordEncoder passwordEncoder;
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
		if(userService.getUserByUsername(username).getPassword().matches(oldPassword)) {
			System.out.println(userService.getUserByUsername(username).getPassword()+" "+oldPassword);
			return "Please enter correct password";
		}
		return userService.updatePassword(username, password, updatedBy);
	}
	@GetMapping("/show")
	public User getUserById(@RequestParam String username) throws Exception {
		return userService.getUserByUsername(username);
	}
	@GetMapping("/check")
	public String check(@RequestParam String username,@RequestParam String password) throws Exception {
		User u=userService.getUserByUsername(username);
		if(u.getPassword()==password) {
			return "Login is sucessful";
		}
		else {
			return "login failed";
		}
	}
	@GetMapping("/showAll")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	@DeleteMapping("/delete")
	public String deleteUser(String username) {
		return userService.deleteUser(username);
	}
}
//{
//   "username":"Maneesh",
//   "password":"Maneesh@123",
//   "passwordUpdatedBy":"Bhanu",
//   "customerId":1
//}

