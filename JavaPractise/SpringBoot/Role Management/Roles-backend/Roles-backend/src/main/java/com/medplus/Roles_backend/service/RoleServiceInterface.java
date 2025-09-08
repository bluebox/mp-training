package com.medplus.Roles_backend.service;

import java.util.List;
import java.util.Map;

import com.medplus.Roles_backend.domain.RoleLocation;
import com.medplus.Roles_backend.domain.RoleResponse;

public interface RoleServiceInterface {
	List<String> getAvailableRoles(String userId);

	List<String> getAssignedActiveRoles(String userId);

	List<RoleResponse> getActiveRoleDetails(String userId);

	Map<String, Object> assignRoles(String userId, List<RoleLocation> request);

	Map<String, Object> disableRolesByLocation(String userId, List<RoleLocation> items);

}
