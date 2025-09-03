package com.sms.Dao.Interfaces;

import com.sms.models.User;

public interface UserDao {
	
	public User findByUsername(String username);
}
