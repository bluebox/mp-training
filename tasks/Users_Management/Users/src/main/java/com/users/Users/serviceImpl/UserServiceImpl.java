package com.users.Users.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.Users.model.User;
import com.users.Users.repository.interfaces.UserRepository;
import com.users.Users.service.interfaces.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository=userRepository;
	}

	public List<User> getUsers(){
		return userRepository.getUsers();
	}
	
	public List<User> getUserById(int userCode){
		return userRepository.getUserById(userCode);
	}

	public void addUser(@NotNull @Valid User user) {
		userRepository.addUser(user);
	}

	public boolean updateUser(User user) {
		return userRepository.updateUser(user);
	}

	@Override
	public boolean updateUserStatus(int userId, String newStatus) {
		return userRepository.updateUserStatus(userId, newStatus);
	}


}
