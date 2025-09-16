package com.users.Users.controllers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.users.Users.enums.Gender;
import com.users.Users.enums.RequestStatus;
import com.users.Users.enums.UserStatus;
import com.users.Users.model.MainUser;
import com.users.Users.model.UserRequest;
import com.users.Users.service.interfaces.MainUserService;
import com.users.Users.service.interfaces.UserService;
import com.users.Users.serviceImpl.SmsService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserService userService;

	private MainUserService mainUserService;

	private SmsService smsService;

	@Autowired
	public UserController(UserService userService, MainUserService mainUserService, SmsService smsService) {
		this.userService = userService;
		this.mainUserService = mainUserService;
		this.smsService = smsService;
	}
	

	@PostMapping(value = "/adduser")
	public ResponseEntity<UserRequest> adduser(@Valid @RequestBody UserRequest user) throws Exception {		
		userService.addUserRequest(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@GetMapping("/getusers")
	@ResponseBody
	public List<UserRequest> getUsers() {
		return userService.getAllUserRequests();
	}

	@PutMapping("/reject/{requestId}")
	public ResponseEntity<?> rejectUser(@PathVariable("requestId") int requestId) throws Exception {
		List<UserRequest> users = userService.getUserRequestById(requestId);

		UserRequest user = users.get(0);
		user.setAprovedStatus(RequestStatus.valueOf("REJECTED"));
		user.setUpdated_at(LocalDateTime.now());

		boolean change = userService.updateUserRequest(user);
		if (!change) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No changes saved");
		}

		return ResponseEntity.ok(true);
	}

	@PutMapping("/aprove/{requestId}")
	@Transactional
	public ResponseEntity<?> aproveUser(@PathVariable("requestId") int requestId) throws Exception {
		List<UserRequest> users = userService.getUserRequestById(requestId);

		UserRequest user = users.get(0);
		user.setAprovedStatus(RequestStatus.valueOf("APROVED"));
		user.setUpdated_at(LocalDateTime.now());

		boolean change = userService.updateUserRequest(user);
		if (!change) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No changes saved");
		}

		boolean res = mainUserService.addUserToMain(user);

		if (res) {
			MainUser mainUser = mainUserService.getMainUserByEmail(user.getEmail());
			smsService.sendSms("+91 " + user.getPhoneNumber(),
					"Welcome! Here is your User ID : " + mainUser.getUserCode()+ " and Password : "+ mainUser.getPassword() +" Continue to Login.");
			return ResponseEntity.ok("Data saved and SMS sent.");
		} else {
			return ResponseEntity.badRequest().body("Failed to save data.");
		}

	}

	@PutMapping("/changestatus/{requestId}")
	public ResponseEntity<String> changeUserStatus(@PathVariable int requestId, @RequestBody Map<String, String> data) throws Exception {
		String newStatus = data.get("status");
		boolean updated = userService.updateUserRequestStatus(requestId, newStatus);
		if (updated)
			return ResponseEntity.ok("Status updated");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update status");
	}
	
	@GetMapping("/dropdowns")
	@ResponseBody
	public Map<String, List<Map<String, String>>> getDropdowns() {
	    Map<String, List<Map<String, String>>> response = new HashMap<>();

	    List<Map<String, String>> genders = Arrays.stream(Gender.values())
	            .map(category -> Map.of("displayName", category.name(), "code", category.name()))
	            .collect(Collectors.toList());

	    List<Map<String, String>> statuses = Arrays.stream(UserStatus.values())
	            .map(status -> Map.of("displayName", status.name(), "code", status.name()))
	            .collect(Collectors.toList());

	    response.put("genders", genders);
	    response.put("status", statuses);

	    return response;
	}

}
