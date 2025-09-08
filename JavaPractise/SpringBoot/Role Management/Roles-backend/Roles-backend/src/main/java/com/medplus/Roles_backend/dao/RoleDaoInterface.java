package com.medplus.Roles_backend.dao;

import com.medplus.Roles_backend.domain.RoleLocation;
import com.medplus.Roles_backend.domain.RoleResponse;
import java.util.List;

public interface RoleDaoInterface {

	List<String> getAvailableRoles(String userId);

	List<String> getAssignedActiveRoles(String userId);

	List<RoleResponse> getActiveRoleDetails(String userId);

	void assignRoles(String userId, List<RoleLocation> request);

	void disableRolesByLocation(String userId, List<RoleLocation> items);
}
