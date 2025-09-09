package com.medplus.exptracker.Controller;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medplus.exptracker.Model.User;
import com.medplus.exptracker.Service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000/",allowedHeaders = "*",allowCredentials = "true")
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> loginUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String roles = auth.getAuthorities().stream()
						        .map(GrantedAuthority::getAuthority)
						        .collect(Collectors.joining(", "));
		var res = new HashMap<String,String>();
		
		res.put("role",roles);
		return  ResponseEntity.ok(res);
	}
	
	@GetMapping("/getuser")
	public User getUserByUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
        
  		//TODO: set separate service to return user with no password, use a DTO
		User user = userService.getUserByUserName(username);
		log.info("Asked for user details , Sending back user details to store: " + user);
		user.setPassword("");
		return user;
	}
	
//	@GetMapping("/logout")
//	public ResponseEntity<Map<String, String>> logout(){
//		var res = new HashMap<String,String>();
//		SecurityContextHolder.clearContext();
//		res.put("message","Successfully Logged Out");
//		return ResponseEntity.ok(res);
//
//	}
	
	
	
}