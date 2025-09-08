package dev.kaushik.userManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.kaushik.userManagement.model.Role;
import dev.kaushik.userManagement.model.UserRole;
import dev.kaushik.userManagement.service.RoleService;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:3000")
public class RoleController {
	private final RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	@GetMapping("/getRoles")
	public ResponseEntity<List<Role>> getRoles() {
		List<Role> rolesList = roleService.getRoles();
		return new ResponseEntity<>(rolesList, HttpStatus.OK);
	}

	@PutMapping("/changeRoleStatus")
	public ResponseEntity<Boolean> changeRoleStatus(@RequestParam("roleId") int roleId) {
		Boolean success = roleService.changeRoleStatus(roleId);
		return new ResponseEntity<>(success, HttpStatus.OK);
	}
	
	@PutMapping("/updateUserRoles")
	public ResponseEntity<Boolean> updateUserRoles(@RequestParam("userName") String userName,
			@RequestBody List<UserRole> userRoles) {
		List<Integer> roleIds = userRoles.stream().map(u -> u.getRoleId()).toList();
		roleService.checkRoleStatus(roleIds);
		roleService.deleteUserRoles(userName);
		int rolesAdded = roleService.assignRolesToUser(userRoles);
		if (rolesAdded > 0) {
			return new ResponseEntity<>(true, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getUserRoles")
	public ResponseEntity<List<UserRole>> getUserRoles() {
		List<UserRole> userRoles = roleService.getUserRoles();
		return new ResponseEntity<>(userRoles, HttpStatus.OK);
	}
	
	@GetMapping("/getConflictingRoles")
	public ResponseEntity<List<String>> getConflictingRoles() {
		List<String> conflictingRoles = roleService.getConflictingRoles();
		return new ResponseEntity<>(conflictingRoles, HttpStatus.OK);
	}

}
