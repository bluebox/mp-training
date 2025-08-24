package com.users.Users.service.interfaces;

import java.util.List;

import com.users.Users.model.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UserService {

	List<User> getUsers();

//	List<User> getUserById(String userCode);

	void addUser(@NotNull @Valid User user);

	boolean updateUser(User user);

	List<User> getUserById(int userId);

	boolean updateUserStatus(int userId, String newStatus);

}
