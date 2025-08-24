package com.users.Users.service.interfaces;

import java.util.List;

import com.users.Users.model.Role;
import com.users.Users.model.UserRole;

public interface RoleService {

	List<Role> getRoles();

	void addUserRole(UserRole userRole);

	List<UserRole> getUserAssignedRoles(String usercode);
	
    void changeRoleStatus(String roleId);


	List<String>  getRoleName(String usercode);

}
