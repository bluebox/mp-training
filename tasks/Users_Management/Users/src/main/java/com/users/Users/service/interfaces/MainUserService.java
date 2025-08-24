package com.users.Users.service.interfaces;

import java.util.List;

import com.users.Users.model.User;

public interface MainUserService {

	boolean addUserToMain(User user);

	List<User> getMainUsers();
	
	User getMainUserByEmail(String email);

	
	User getPassword(String username);
	
	boolean updatePassword(String userCode, String encodedPassword);


}
