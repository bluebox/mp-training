package com.example.vehicle.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vehicle.dao.UserDao;
import com.example.vehicle.model.User;

@Service
@Transactional
public class UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDao userDao;

	public User getUserByUsername(String username) throws Exception {
		User user = userDao.getUserByUsername(username);
		if (user == null) {
			throw new Exception("Invalid username or password.");
		}
		return user;
	}



	public String addUser(User user) throws Exception {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setPasswordUpdatedBy(user.getPasswordUpdatedBy());
		newUser.setCustomerId(user.getCustomerId());

		return userDao.addUser(newUser);
	}

	public String updatePassword(String username, String password, String passwordUpdatedBy) throws Exception {
		password = passwordEncoder.encode(password);
		return userDao.updatePassword(username, password, passwordUpdatedBy);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public String deleteUser(String username) {
		return userDao.deleteUser(username);
	}

}