package com.users.Users.service.interfaces;

import java.util.List;

import com.users.Users.model.Role;
import com.users.Users.model.UserRole;

public interface RoleService {

	List<Role> getRoles();

	List<String> getRoleName(String usercode) throws Exception;

	List<UserRole> getUserAssignedRoles(String usercode) throws Exception;

	void addUserRole(UserRole userRole) throws Exception;

	boolean changeRoleStatus(String roleCode) throws Exception;

	boolean changeUserRoleStatus(UserRole userRole) throws Exception;

}
