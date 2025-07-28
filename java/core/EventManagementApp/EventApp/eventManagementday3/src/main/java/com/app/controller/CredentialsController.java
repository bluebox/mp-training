package com.app.controller;

import com.app.model.Credentials;
import com.app.model.Response;
import com.app.security.CredPrincipal;

import lombok.extern.slf4j.Slf4j;

import com.app.repository.CredentialsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class CredentialsController {

	@Autowired
	private CredentialsRepository repo;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody Credentials c) {

		Response res = new Response();
		try {
//			log.info(c.toString());
			log.info("Register :"+c.getUserId()+" "+c.getUserName() + "  " + c.getPassword()+" "+c.getRole());
			c.setPassword(encoder.encode(c.getPassword()));
			repo.addCredentials(c);

			res.setStatusCode("200");
			res.setStatusMsg("User registered successfully");

			return ResponseEntity.ok(res);
		} catch (Exception e) {
			log.error(e.getMessage());
			res.setStatusCode("400");
			res.setStatusMsg("User registration is Unsuccessfully");

			return ResponseEntity.ok(res);

		}

	}

	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody Credentials c) {
		Response res = new Response();
		log.info("login :"+c.getUserName() + "  " + c.getPassword()+" "+c.getRole());
		try {
			Authentication auth = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(c.getUserName(), c.getPassword()));

			if (auth.isAuthenticated()) {
				CredPrincipal user = (CredPrincipal) auth.getPrincipal();
				String role = user.getAuthorities().iterator().next().getAuthority();
				res.setStatusCode("200");
				res.setStatusMsg("Login successful as " + role + " " + "user_id :" + c.getUserId());
				return ResponseEntity.ok(res);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			res.setStatusCode("401");
			res.setStatusMsg("Invalid username or password");
			return ResponseEntity.status(401).body(res);
		}

		res.setStatusCode("401");
		res.setStatusMsg("Authentication failed");
		return ResponseEntity.status(401).body(res);
	}
}

