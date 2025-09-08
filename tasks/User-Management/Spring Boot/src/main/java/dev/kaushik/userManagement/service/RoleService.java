package dev.kaushik.userManagement.service;

import java.util.List;

import dev.kaushik.userManagement.model.Role;
import dev.kaushik.userManagement.model.UserRole;

public interface RoleService {
	boolean changeRoleStatus(int roleId);
	 
	int assignRolesToUser(List<UserRole> userRoles);

	List<Role> getRoles();

	List<UserRole> getUserRoles();
	
	void deleteUserRoles(String userName);
	
	void checkRoleStatus(List<Integer> roleIds);
	
	List<String> getConflictingRoles();
}