package dev.kaushik.userManagement.dao;

import java.util.List;

import dev.kaushik.userManagement.model.UserRequest;

public interface UserRequestDao {
	int createUser(UserRequest userReq);
	
	List<UserRequest> getAllRequests(); 
	
	boolean updateRequest(UserRequest userReq);
	
	boolean rejectUser(int requestId);
}
