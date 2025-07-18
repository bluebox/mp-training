package com.user.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.app.dto.ApiResponse;
import com.user.app.dto.LoginDTO;
import com.user.app.model.User;
import com.user.app.service.UserService;
import com.user.app.util.JwtUtil;
import com.user.app.util.ResponseUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
	    origins = "http://localhost:5173",
	    allowCredentials = "true"
	)
public class UserController {

	private final UserService userService;
	private final JwtUtil jwtUtil;

	@Autowired
	public UserController(UserService userService,JwtUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
		try {
			User createdUser = userService.createUser(user);
			return ResponseUtil.success("User created successfully", createdUser);
		} catch (Exception e) {
			return ResponseUtil.failure("Failed to create user: " + e.getMessage());
		}
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<Map<String, Object>>> findUser(@RequestBody LoginDTO loginDTO) throws Exception {
		Optional<User> userOpt = userService.login(loginDTO.getEmail(), loginDTO.getPassword());
		try {
			if (userOpt.isPresent()) {
				User user = userOpt.get();
				
				String token = jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getUserId());
				
				Map<String, Object> response = new HashMap<>();
			    response.put("user", user);
			    response.put("token", token);
				return ResponseUtil.success("Login successful", response);
			} else {
				return ResponseUtil.failure("Invalid email or password");
			}
		} catch (Exception e) {
			return ResponseUtil.failure("Failed to login user: " + e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
		try {
			Optional<User> user = userService.getUserById(id);
			return user.map(u -> ResponseUtil.success("User found", u))
					.orElseGet(() -> ResponseUtil.failure("User not found with ID: " + id));
		} catch (Exception e) {
			return ResponseUtil.failure("Failed to fetch user: " + e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
		try {
			List<User> users = userService.getAllUsers();
			return ResponseUtil.success("User list retrieved", users);
		} catch (Exception e) {
			return ResponseUtil.failure("Failed to fetch all users: " + e.getMessage());
		}

	}
}
