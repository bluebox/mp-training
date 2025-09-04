package com.medplus.exptracker.Dao;

import java.util.List;
import java.util.Optional;

import com.medplus.exptracker.Model.User;

public interface UserDAO {
	Optional<User> findByUsername(String username);
	public void addUser(User user);
	public Optional<User> getUserById(Integer userId);
	List<User> getUsersByManagerId(Integer managerId);
}