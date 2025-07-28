package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Response;
import com.app.model.User;
import com.app.service.EmailService;
import com.app.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@PostMapping("/add")
	public ResponseEntity<Response> addUser(@Valid @RequestBody User user) {

		Response response = new Response();
		try {
			if (userService.addUser(user)) {

				emailService.sendRegistrationEmail(user.getEmail(), String.valueOf(user.getUserId()));
				response.setStatusCode("200");
				response.setStatusMsg("User added successfully & email sent");
				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			} else {
				response.setStatusCode("400");
				response.setStatusMsg("Failed to add user");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatusCode("400");
			response.setStatusMsg("Failed to add user");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

	}

	@PutMapping("/update")
	public ResponseEntity<Response> updateUser(@RequestBody User user) {
		Response response = new Response();
		try {

			if (userService.updateUser(user)) {

				emailService.sendUpdationEmail(user.getEmail(), String.valueOf(user.getUserId()));
				response.setStatusCode("200");
				response.setStatusMsg("User updated successfully");
				return ResponseEntity.status(HttpStatus.OK).body(response);
			} else {
				response.setStatusCode("400");
				response.setStatusMsg("Failed to update user");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatusCode("400");
			response.setStatusMsg("Failed to update user");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteUser(@RequestParam int userId) {

		Response response = new Response();
		try {
			if (userService.deleteUser(userId)) {
				response.setStatusCode("200");
				response.setStatusMsg("User deleted successfully");
				return ResponseEntity.status(HttpStatus.OK).body(response);
			} else {
				response.setStatusCode("400");
				response.setStatusMsg("Failed to delete user");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatusCode("400");
			response.setStatusMsg("Failed to delete user");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

		}
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {

		try {
			return ResponseEntity.ok(userService.getAllUsers());
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		}

	}

	@GetMapping("/userById")
	public ResponseEntity<User> getUserById(@RequestParam int userId) {
		try {
			return ResponseEntity.ok(userService.getUserbyId(userId));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

	}
}
