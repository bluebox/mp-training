package com.users.Users.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.Users.model.User;
import com.users.Users.repository.interfaces.MainUserRepository;
import com.users.Users.service.interfaces.MainUserService;

@Service
public class MainUserServiceImpl implements MainUserService{
	
	private MainUserRepository mainUserRepository;
	
	@Autowired
	public MainUserServiceImpl(MainUserRepository mainUserRepository) {
		this.mainUserRepository=mainUserRepository;
	}

	@Override
	public boolean addUserToMain(User user) {
		return mainUserRepository.addUserToMain(user);
	}

	@Override
	public List<User> getMainUsers() {
		return mainUserRepository.getMainUsers();
	}
	
	@Override
	public User getPassword(String username) {
		return mainUserRepository.getPassword(username);
	}

	@Override
	public boolean updatePassword(String userCode, String encodedPassword) {
	    return mainUserRepository.updatePassword(userCode, encodedPassword);
	}

	@Override
	public User getMainUserByEmail(String email) {
		return mainUserRepository.getMainUserByEmail(email);
	}


}
