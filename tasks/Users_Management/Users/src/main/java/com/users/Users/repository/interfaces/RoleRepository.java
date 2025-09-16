package com.users.Users.repository.interfaces;

import java.util.List;

import com.users.Users.model.Role;
import com.users.Users.model.UserRole;

public interface RoleRepository {

	List<Role> getRoles();

	void addUserRole(UserRole userRole);

	List<UserRole> getUserAssignedRoles(String userCode);

	List<String>  getRoleName(String userCode);
	
	boolean changeRoleStatus(String roleCode);

	boolean changeUserRoleStatus(UserRole userRole);

}
