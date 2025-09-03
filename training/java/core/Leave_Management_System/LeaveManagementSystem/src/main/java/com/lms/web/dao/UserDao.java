package com.lms.web.dao;


import com.lms.web.model.User;

public interface UserDao {
	public User findByEmail(String email);
}
