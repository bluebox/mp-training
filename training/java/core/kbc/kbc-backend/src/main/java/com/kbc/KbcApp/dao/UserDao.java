package com.kbc.KbcApp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kbc.KbcApp.pojos.Users;

@Repository
public interface UserDao {
	public void addUser(Users user);
	public void updateUser(Users user) throws Exception;
	public List<Users> getUser(Integer userId, String username, String password);
}
