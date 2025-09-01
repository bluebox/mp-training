package com.rms.restaurant_management_system_backend.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.restaurant_management_system_backend.domain.AuthResponse;
import com.rms.restaurant_management_system_backend.domain.LoginRequest;
import com.rms.restaurant_management_system_backend.utilities.CustomResponse;
import com.rms.restaurant_management_system_backend.utilities.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<CustomResponse> login(@RequestBody LoginRequest request) {
		try {
			System.out.println(request.getUsername());
			System.out.println(request.getPassword());
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			String roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.joining(","));

			String token = jwtUtil.generateToken(request.getUsername(), roles);

			CustomResponse response = new CustomResponse(true, "Login successful", new AuthResponse(token, roles));
			return ResponseEntity.ok(response);

		} catch (BadCredentialsException ex) {
			CustomResponse response = new CustomResponse(false, "Invalid username or password", null);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<CustomResponse> logout() {
		CustomResponse response = new CustomResponse(true, "Logged out successfully!", null);
		return ResponseEntity.ok(response);
	}
}
