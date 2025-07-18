package com.example.library.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/session")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthRestController {
	    @GetMapping("/check")
	    public ResponseEntity<String> checkSession(HttpServletRequest request) {
	        HttpSession session = request.getSession(false); 

	        if (session != null && session.getAttribute("user") != null) {
	            return ResponseEntity.ok("Session active");
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No active session");
	        }
	    }
	}


