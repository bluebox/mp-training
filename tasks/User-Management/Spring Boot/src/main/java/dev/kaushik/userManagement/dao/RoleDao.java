package dev.kaushik.userManagement.dao;

import java.util.List;

import dev.kaushik.userManagement.model.Role;
import dev.kaushik.userManagement.model.UserRole;

public interface RoleDao {
	boolean changeRoleStatus(int roleId);

	int addUserRole(UserRole userRole);

	List<Role> getRoles();

	List<UserRole> getUserRoles();

	void deleteUserRoles(String userName);

	boolean checkRolesConflict(int roleIdA, int roleIdB); 
	
	List<String> getConflictingRoles();
}