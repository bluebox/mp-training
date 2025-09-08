package dev.kaushik.userManagement.controller;

import dev.kaushik.userManagement.model.MainUser;
import dev.kaushik.userManagement.model.UserRequest;
import dev.kaushik.userManagement.model.UserRole;
import dev.kaushik.userManagement.model.enums.Gender;
import dev.kaushik.userManagement.service.RoleService;
import dev.kaushik.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	private final UserService userService;
	private final RoleService roleService;

	@Autowired
	public UserController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@PostMapping("/createUser")
	public ResponseEntity<Integer> addUser(@RequestBody UserRequest user) {
		int requestId = userService.createUser(user);
		return new ResponseEntity<>(requestId, HttpStatus.CREATED);
	}

	@GetMapping("/getMainUsers")
	public ResponseEntity<List<MainUser>> getUsers() {
		List<MainUser> mainUsersList = userService.getMainUsers();
		return new ResponseEntity<>(mainUsersList, HttpStatus.OK);
	}

	@GetMapping("/getUserRequests")
	public ResponseEntity<List<UserRequest>> getUserRequests() {
		List<UserRequest> userRequestsList = userService.getUserRequests();
		return new ResponseEntity<>(userRequestsList, HttpStatus.OK);
	}

	@PutMapping("/rejectUser")
	public ResponseEntity<Boolean> rejectRequest(@RequestParam("requestId") int requestId) {
		Boolean success = userService.rejectUser(requestId);
		return new ResponseEntity<>(success, HttpStatus.OK);
	}

	@PutMapping("/changeStatus")
	public ResponseEntity<Boolean> changeStatus(@RequestParam("userName") String userName) {
		Boolean success = userService.changeStatus(userName);
		return new ResponseEntity<>(success, HttpStatus.OK);
	}

	@PostMapping("/approveAndAssignRoles")
	@Transactional
	public ResponseEntity<Boolean> approveAndAssignRoles(@RequestParam("requestId") int requestId,
			@RequestBody List<UserRole> userRoles) {

		String userName = getUserName();
		String password = generatePassword();
		userService.approveUser(requestId, userName, password);
		for (UserRole userRole : userRoles) {
			userRole.setUserName(userName);
		}
		int rolesAdded = roleService.assignRolesToUser(userRoles);
		return new ResponseEntity<>(rolesAdded > 0, HttpStatus.CREATED);
	}

	@PutMapping("/updateUserProfile")
	public ResponseEntity<Boolean> updateUserProfile(@RequestBody MainUser mainUser) {
		Boolean success = userService.updateUser(mainUser);
		return new ResponseEntity<>(success, HttpStatus.OK);
	}

	@GetMapping("/getGenders")
	public ResponseEntity<List<Gender>> getGenders() {
		List<Gender> genders = Arrays.asList(Gender.values());
		return new ResponseEntity<>(genders, HttpStatus.OK);
	}

	@GetMapping("/userLogin")
	public ResponseEntity<MainUser> userLogin(@RequestParam("userName") String userName,
			@RequestParam("password") String password) {
		MainUser mainUser = userService.verifyMainUser(userName, password);
		return new ResponseEntity<>(mainUser, HttpStatus.OK);
	}

	private String getUserName() {
		List<MainUser> users = userService.getMainUsers();
		return String.format("U%03d", users.size() + 1);
	}

	private String generatePassword() {
		String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lower = "abcdefghijklmnopqrstuvwxyz";
		String digits = "0123456789";
		String symbols = "!@#$%^&*()-_=+[]{}";
		String allChars = upper + lower + digits + symbols;
		SecureRandom random = new SecureRandom();
		StringBuilder password = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			int index = random.nextInt(allChars.length());
			password.append(allChars.charAt(index));
		}
		return password.toString();
	}

}