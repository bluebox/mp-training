package com.kbc.KbcApp.restcontrollers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbc.KbcApp.enums.RoleEnum;
import com.kbc.KbcApp.enums.StatusEnum;
import com.kbc.KbcApp.handler.KbcException;
import com.kbc.KbcApp.pojos.Users;
import com.kbc.KbcApp.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid Users u, HttpSession session) throws KbcException {

		Optional<Users> user = userService.validLogin(u.getUsername(), u.getPassword());
		if (!user.isPresent()) {
			throw new KbcException("Invalid credentials");
		}
		
		session.setAttribute("userId", user.get().getUserId());
		session.setAttribute("userName", user.get().getUsername());
		session.setAttribute("userRole", user.get().getRole().name());
		session.setAttribute("userStatus", user.get().getStatus().name());
		session.setAttribute("password", user.get().getPassword());
		


		return ResponseEntity.ok(user.get());
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok("Logged out successfully");
	}
	
	@GetMapping("/check-auth")
	public ResponseEntity<?> checkAuth(HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			return ResponseEntity.status(401).body("Not authenticated");
		}
		String status=(String)session.getAttribute("userStatus");
		if(status.equals("INACTIVE")) {
			session.invalidate();
			return ResponseEntity.status(401).body("Not authenticated");
		}

		return ResponseEntity.ok("Authenticated");
	}
	
	@GetMapping("/user-info")
	public ResponseEntity<?> getUserInfo(HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		String userRole = (String) session.getAttribute("userRole");
		String password = (String) session.getAttribute("password");
		String userStatus = (String) session.getAttribute("userStatus");

		
		if (userId == null) {
			return ResponseEntity.status(401).body("Not authenticated");
		}
		
		Users userInfo = new Users();
		userInfo.setUserId(userId);
		userInfo.setUsername(userName);
		userInfo.setRole(RoleEnum.valueOf(userRole));
		userInfo.setPassword(password);
		userInfo.setStatus(StatusEnum.valueOf(userStatus));
		
		
		return ResponseEntity.ok(userInfo);
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody Users user) throws KbcException {
		userService.addUser(user);
		return ResponseEntity.ok("User created successfully");
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid Users user) throws KbcException {
		user.setRole(RoleEnum.USER);
		user.setStatus(StatusEnum.ACTIVE);
		userService.addUser(user);
		return ResponseEntity.ok("User registered successfully");
	}
	
	@GetMapping("/allUsers")
	public ResponseEntity<?> getAllUsers() {
		List<Users> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable int userId) throws KbcException {
		Optional<Users> user = userService.getUserById(userId);
		if (!user.isPresent()) {
			throw new KbcException("User not found with ID: " + userId);
		}
		return ResponseEntity.ok(user.get());
	}
	
	@PutMapping("/makeAdmin/{userId}")
	public ResponseEntity<?> updateUserRole(@PathVariable int userId, HttpSession session) throws Exception {
		Optional<Users> user = userService.getUserById(userId);
		if (!user.isPresent()) {
			throw new KbcException("User not found with ID: " + userId);
		}
		String modifiedBy=(String) session.getAttribute("userName");
		Users userToUpdate = user.get();
		if(userToUpdate.getRole()== RoleEnum.valueOf("ADMIN")) {
			userToUpdate.setRole(RoleEnum.valueOf("USER"));

		}else {
			userToUpdate.setRole(RoleEnum.valueOf("ADMIN"));
		}
		userService.updateUserRole(userToUpdate, modifiedBy);
		return ResponseEntity.ok("User role updated successfully");
	}
	
	@PatchMapping("/{userId}/status")
	public ResponseEntity<?> updateUserStatus(@PathVariable int userId, @RequestBody @Valid Users userstatus, HttpSession session) throws Exception {
	    Optional<Users> user = userService.getUserById(userId);
	    if (!user.isPresent()) {
	        throw new KbcException("User not found with ID: " + userId);
	    }
	    String modifiedBy = (String) session.getAttribute("userName");
	    Users userToUpdate = user.get();
	    userToUpdate.setStatus(userstatus.getStatus());
	    userService.updateUserStatus(userToUpdate, modifiedBy);

	    if (userstatus.getStatus() == StatusEnum.INACTIVE) {
	        session.invalidate();
	    }

	    return ResponseEntity.ok("User status updated successfully");
	}

	@PatchMapping("/{userId}/update")
	public ResponseEntity<?> updateUser(
	    @PathVariable int userId,
	    @RequestBody Users updateRequest,
	    HttpSession session) throws Exception {
	    Optional<Users> userOpt = userService.getUserById(userId);
	    if (!userOpt.isPresent()) {
	        throw new KbcException("User not found with ID: " + userId);
	    }
	    
	    Users user = userOpt.get();
	    
	    if (updateRequest.getUsername() != null) {
	        user.setUsername(updateRequest.getUsername());
	    }
	    if (updateRequest.getPassword() != null) {
	        user.setPassword(updateRequest.getPassword());
	    }
	    if (updateRequest.getStatus() != null) {
	        user.setStatus(updateRequest.getStatus());
	    }
	    String modifiedBy=(String) session.getAttribute("userName");

	    user.setModifiedBy(modifiedBy);
	    
	    userService.updateUser(user, modifiedBy);
	    session.setAttribute("userName", updateRequest.getUsername());
	    session.setAttribute("password", updateRequest.getPassword()); 
	    session.setAttribute("userStatus", updateRequest.getStatus().name()); 

	    return ResponseEntity.ok("User updated successfully");
	}
}
