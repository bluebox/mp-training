package com.SpringBoot_LMS.SpringBoot_LMS.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path="/")
public class HomeController {
	
	@Value("${spring.security.user.name}")
    private String username;
	
	
	@GetMapping("/")
	public ResponseEntity<String> home() {
		return  ResponseEntity.ok().body(username);
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
