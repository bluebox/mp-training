package com.users.Users.service.interfaces;

import java.util.List;

import com.users.Users.model.UserRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UserService {

	List<UserRequest> getAllUserRequests();

	void addUserRequest(@NotNull @Valid UserRequest user) throws Exception;

	boolean updateUserRequest(UserRequest user) throws Exception;

	List<UserRequest> getUserRequestById(int requestId) throws Exception;

	boolean updateUserRequestStatus(int requestId, String newStatus) throws Exception;

}
