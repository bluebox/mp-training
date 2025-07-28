package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.User;
import com.app.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repo;

	public boolean addUser(User u) throws Exception {

		if (repo.addUser(u) > 0) {
			return true;
		}
		return false;
	}

	public boolean updateUser(User u) throws Exception {

		if (repo.updateUser(u) > 0) {
			return true;
		}
		return false;

	}

	public boolean deleteUser(int userId) throws Exception {

		if (repo.deleteUser(userId) > 0) {
			return true;
		}
		return false;
	}

	public List<User> getAllUsers() throws Exception {
		return repo.getAllUsers();
	}

	public User getUserbyId(int userId) throws Exception {
		return repo.getUserbyId(userId);
	}
}
