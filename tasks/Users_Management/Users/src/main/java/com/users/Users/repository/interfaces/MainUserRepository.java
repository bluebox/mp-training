package com.users.Users.repository.interfaces;

import java.util.List;

import com.users.Users.model.MainUser;
import com.users.Users.model.UserRequest;

public interface MainUserRepository {

	boolean addUserToMain(UserRequest user);

	List<MainUser> getMainUsers();

	MainUser getMainUserById(String userCode);
	
	MainUser getMainUserByEmail(String email);

	boolean updateMainUser(MainUser user);
	
	
	boolean updatePassword(String userCode, String encodedPassword);


}
