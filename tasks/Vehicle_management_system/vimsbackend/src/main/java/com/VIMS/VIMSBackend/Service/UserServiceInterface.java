package com.VIMS.VIMSBackend.Service;

import java.util.List;

import com.VIMS.VIMSBackend.Model.UserModel;

public interface UserServiceInterface {
	 int addUser(UserModel user);
	    int updateUser(UserModel user);
	    UserModel getUserById(int userId);
	    List<UserModel> getAllUsers();
		List<String> getAllReviews();
		String addReview(int userId, String review);

}
