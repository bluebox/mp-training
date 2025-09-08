package com.medplus.Roles_backend.service;

import java.util.List;

import com.medplus.Roles_backend.domain.ActiveMembers;
import com.medplus.Roles_backend.domain.UserRequest;

public interface UserRequestServiceInterface {
	void sendRequest(UserRequest request);

    List<UserRequest> getAllRequests();

    List<ActiveMembers> getAllActiveUsers();

    void acceptRequest(Long reqId);

    void rejectRequest(Long reqId);

    void activeRequest(Long reqId);

    void inactiveRequest(Long reqId);
}
