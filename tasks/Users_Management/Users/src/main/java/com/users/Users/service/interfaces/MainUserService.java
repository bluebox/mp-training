package com.users.Users.service.interfaces;

import java.util.List;

import com.users.Users.model.MainUser;
import com.users.Users.model.UserRequest;

public interface MainUserService {

	boolean addUserToMain(UserRequest user) throws Exception;

	List<MainUser> getMainUsers();
	
	MainUser getMainUserByEmail(String email) throws Exception;

	MainUser getMainUserById(String usercode) throws Exception;
		
	boolean updatePassword(String userCode, String encodedPassword) throws Exception;


}
