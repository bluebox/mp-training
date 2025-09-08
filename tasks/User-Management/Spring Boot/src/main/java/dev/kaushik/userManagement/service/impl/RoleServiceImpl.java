package dev.kaushik.userManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kaushik.userManagement.dao.RoleDao;
import dev.kaushik.userManagement.exception.UserException;
import dev.kaushik.userManagement.model.Role;
import dev.kaushik.userManagement.model.UserRole;
import dev.kaushik.userManagement.model.enums.Status;
import dev.kaushik.userManagement.service.RoleService;
import dev.kaushik.userManagement.validator.UserRoleValidator;

@Service
public class RoleServiceImpl implements RoleService {

	private final RoleDao roleDao;

	@Autowired
	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public boolean changeRoleStatus(int roleId) {
		if (roleId <= 0) {
			throw new UserException("Role Id cant be negative");
		}
		List<Role> roles = getRoles();
		if (!roles.stream().anyMatch(role -> role.getRoleId() == roleId)) {
			throw new UserException("Role with Id: " + roleId + " does not exist");
		}
		return roleDao.changeRoleStatus(roleId);
	}

	@Override
	public int assignRolesToUser(List<UserRole> userRoles) {
		userRoles.forEach(UserRoleValidator::validate);
		List<Integer> roleIds = userRoles.stream().map(u -> u.getRoleId()).toList();
		checkRoleStatus(roleIds);
		List<Role> roles = getRoles();
		if (userRoles.size() > 1) {
			for (int i = 0; i < userRoles.size() - 1; i++) {
				for (int j = i + 1; j < userRoles.size(); j++) {
					if (roleDao.checkRolesConflict(userRoles.get(i).getRoleId(), userRoles.get(j).getRoleId())) {
						String roleA = findRoleNameById(roles, userRoles.get(i).getRoleId());
						String roleB = findRoleNameById(roles, userRoles.get(i).getRoleId());
						throw new UserException(roleA + " and " + roleB + " are conflicting");
					}
				}
			}
		}
		int rolesAdded = 0;
		for (UserRole userRole : userRoles) {
			rolesAdded += roleDao.addUserRole(userRole);
		}
		return rolesAdded;
	}

	@Override
	public List<Role> getRoles() {
		return roleDao.getRoles();
	}

	@Override
	public List<UserRole> getUserRoles() {
		return roleDao.getUserRoles();
	}

	@Override
	public void deleteUserRoles(String userName) {
		roleDao.deleteUserRoles(userName);
	}

	@Override
	public void checkRoleStatus(List<Integer> roleIds) {
		List<Role> roles = getRoles();
		roles.forEach(role -> {
			if (roleIds.contains(role.getRoleId()) && role.getStatus() == Status.INACTIVE) {
				throw new UserException("Role: " + role.getRoleName() + " is inactive, unable to assign");
			}
		});
	}

	@Override
	public List<String> getConflictingRoles() {
		return roleDao.getConflictingRoles();
	}

	private String findRoleNameById(List<Role> roles, int roleId) {
		return roles.stream().filter(r -> r.getRoleId() == roleId).map(r -> r.getRoleName()).findFirst().orElse(null);
	}
}