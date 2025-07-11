package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.app.model.Credentials;
import com.app.repository.CredentialsRepository;
import com.app.service.CredentialsService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CredentialsController {

	@Autowired
	private CredentialsRepository repo;

	@Autowired
	private HttpSession session;

	@Autowired
	private final CredentialsService cs;

	public CredentialsController(CredentialsService cs) {
		this.cs = cs;
	}

	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Credentials c) {
		boolean isAuthenticated = cs.authenticate(c.getUserName(), c.getPassword());
		System.out.println(isAuthenticated);

		if (isAuthenticated) {
			return ResponseEntity.ok("Login was successful!");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}

	}

	@GetMapping("/user/home")
	public String userHome() {
		return "Welcome USER!";
	}

	@PostMapping("/logger")
	public ResponseEntity<String> login(@RequestBody Credentials c) 
	{
		c.setPassword(encoder.encode(c.getPassword()));
		repo.addCredentials(c);
		return ResponseEntity.status(HttpStatus.CREATED).body("User Registered");
	}

	@GetMapping("/admin/home")
	public String adminHome() {
		return "Welcome ADMIN!";
	}

}