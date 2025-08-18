package com.SpringBoot_LMS.SpringBoot_LMS.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path="/")
public class HomeController {
	
	@Value("${spring.security.user.name}")
    private String Username;
	
	@Value("${spring.security.user.password}")
    private String pass;
	

	
	@GetMapping("/")
	public ResponseEntity<String> home() {
		System.out.println("reached me"+Username);
		return  ResponseEntity.ok().body(Username);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> function(@RequestParam("username")  String username,@RequestParam("password")  String password){
		if(pass.equals(password) && Username.equals(username) ) {
			System.out.println("reached me");
			return  ResponseEntity.ok().body("login successful");
		}
		return ResponseEntity.badRequest().body("error while log in");
	}
	
	
	@GetMapping("/Book")
	public ResponseEntity<String> bookhome() {
		return  ResponseEntity.ok().body("");
	}
	
	@GetMapping("/Member")
	public ResponseEntity<String> memberhome() {
		return  ResponseEntity.ok().body("");
	}
	
	@GetMapping("/Issues")
	public ResponseEntity<String> issuerecordhome() {
		return  ResponseEntity.ok().body("");
	}
	

	@GetMapping("/Reports")
	public ResponseEntity<String> Reportshome() {
		return  ResponseEntity.ok().body("");
	}
	
	
}
