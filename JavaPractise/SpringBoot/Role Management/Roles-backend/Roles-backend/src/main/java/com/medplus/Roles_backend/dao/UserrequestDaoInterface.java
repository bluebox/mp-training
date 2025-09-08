package com.medplus.Roles_backend.dao;

import java.util.List;

import com.medplus.Roles_backend.domain.ActiveMembers;
import com.medplus.Roles_backend.domain.UserRequest;
import com.medplus.Roles_backend.enums.ActiveStatus;
import com.medplus.Roles_backend.enums.ApprovalStatus;

public interface UserrequestDaoInterface {

	int save(UserRequest request);

	List<UserRequest> getAllRequests();

	UserRequest findById(Long reqId);

	void updateApprovalStatus(Long reqId, ApprovalStatus status);

	void updateActiveStatus(Long reqId, ActiveStatus status);

	void insertIntoMainTable(UserRequest request, String encryptedPassword);

	List<ActiveMembers> findAllActiveUsers();
}
