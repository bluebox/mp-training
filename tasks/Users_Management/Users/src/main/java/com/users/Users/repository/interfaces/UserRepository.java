package com.users.Users.repository.interfaces;

import java.util.List;

import com.users.Users.model.User;

public interface UserRepository {

	List<User> getUsers();

//	List<User> getUserById(String userCode);

	void addUser(User user);

	boolean updateUser(User user);
	
    boolean updateUserStatus(int userId, String status);

	
	List<User> getUserById(int userCode);

}
