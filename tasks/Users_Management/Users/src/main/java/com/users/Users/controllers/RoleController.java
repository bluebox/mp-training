package com.users.Users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.users.Users.model.Role;
import com.users.Users.model.UserRole;
import com.users.Users.service.interfaces.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	private RoleService roleService;
	
	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService=roleService;
	}
	
	@GetMapping("/getroles")
	@ResponseBody
	public  List<Role> getRoles() {
		return  roleService.getRoles();
	}
	
	@PostMapping(value="/addUserRole")
    public ResponseEntity<UserRole> addUserRole(@RequestBody UserRole userRole) throws Exception {
		try {
			roleService.addUserRole(userRole);			
		} catch (Exception e) {
			throw new Exception("The User is assigned with the selected roles in selected locations");
		}
        return ResponseEntity.status(HttpStatus.CREATED).body(userRole);
	}
	
	@GetMapping("/getUserAssignedRoles/{userId}")
	@ResponseBody
	public List<UserRole> getUserAssignedRoles(@PathVariable("userId") String userId){
		return roleService.getUserAssignedRoles(userId);
	}
	
	@GetMapping("/changeStatus/{roleId}")
	@ResponseBody
	public ResponseEntity<String> toggleRoleStatus(@PathVariable("roleId") String roleId) {
	    try {
	        roleService.changeRoleStatus(roleId);
	        return ResponseEntity.ok("Status change successfully");
	    } catch (Exception e) {
	        return ResponseEntity.status(500).body("Failed to chnage status");
	    }
	}
	
}
