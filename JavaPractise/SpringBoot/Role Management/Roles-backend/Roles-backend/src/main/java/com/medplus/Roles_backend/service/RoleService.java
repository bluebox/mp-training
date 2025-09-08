package com.medplus.Roles_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.medplus.Roles_backend.dao.RoleDaoInterface;
import com.medplus.Roles_backend.domain.RoleLocation;
import com.medplus.Roles_backend.domain.RoleResponse;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService implements RoleServiceInterface {

	@Autowired
	private RoleDaoInterface roleDao;

	public List<String> getAvailableRoles(String userId) {
		return roleDao.getAvailableRoles(userId);
	}

	public List<String> getAssignedActiveRoles(String userId) {
		return roleDao.getAssignedActiveRoles(userId);
	}

	public List<RoleResponse> getActiveRoleDetails(String userId) {
		return roleDao.getActiveRoleDetails(userId);
	}

	public Map<String, Object> assignRoles(String userId, List<RoleLocation> request) {
		 roleDao.assignRoles(userId, request);
		 Map<String, Object> out = new LinkedHashMap<>();
         out.put("message", "Roles updated successfully!");
         return out;
	}

	public Map<String, Object> disableRolesByLocation(String userId, List<RoleLocation> items) {
		 roleDao.disableRolesByLocation(userId, items);
		 return Map.of("message", "Roles disabled successfully!");
	}

}
