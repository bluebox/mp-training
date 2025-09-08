package dev.kaushik.userManagement.service;

import java.util.List;

import dev.kaushik.userManagement.model.MainUser;
import dev.kaushik.userManagement.model.UserRequest;

public interface UserService {
	int createUser(UserRequest user);

	boolean approveUser(int requestId, String userName, String password);

	List<MainUser> getMainUsers();

	List<UserRequest> getUserRequests();

	boolean rejectUser(int requestId);

	boolean changeStatus(String userName);
	
	MainUser verifyMainUser(String userName, String password);
	
	boolean updateUser(MainUser mainUser);
}
