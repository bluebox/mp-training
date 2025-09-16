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
	
	@PostMapping("/addUserRole")
    public ResponseEntity<UserRole> addUserRole(@RequestBody UserRole userRole) throws Exception {
			roleService.addUserRole(userRole);			
		
        return ResponseEntity.status(HttpStatus.CREATED).body(userRole);
	}
	
	
	
	@GetMapping("/getUserAssignedRoles/{userCode}")
	@ResponseBody
	public List<UserRole> getUserAssignedRoles(@PathVariable("userCode") String userCode) throws Exception{
		return roleService.getUserAssignedRoles(userCode);
	}
	
	@PostMapping("/changeStatus/{roleCode}")
	public ResponseEntity<String> changeRoleStatus(@PathVariable("roleCode") String roleCode) throws Exception {
	        roleService.changeRoleStatus(roleCode);
	        return ResponseEntity.ok("Status change successfully");
	    
	}
	
	@PostMapping("/changeUserRoleStatus")
	public ResponseEntity<UserRole> changeUserRoleStatus(@RequestBody UserRole userRole) throws Exception {
	      roleService.changeUserRoleStatus(userRole);
	    
		return ResponseEntity.status(HttpStatus.CREATED).body(userRole);
	}
	
}
