package com.users.Users.repository.interfaces;

import java.util.List;

import com.users.Users.model.UserRequest;

public interface UserRepository {

	List<UserRequest> getAllUserRequests();

	void addUserRequest(UserRequest user);

	boolean updateUserRequest(UserRequest user);
	
    boolean updateUserRequestStatus(int requestid, String status);

	
	List<UserRequest> getUserRequestById(int requestid);

}
