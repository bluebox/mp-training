package com.kbc.KbcApp.service;

import java.util.List;
import java.util.Optional;

import com.kbc.KbcApp.handler.KbcException;
import com.kbc.KbcApp.pojos.Users;

public interface UserService {
	
	public void addUser(Users u) throws KbcException;
	public Optional<Users> validLogin(String username, String password) throws KbcException;
	public List<Users> getAllUsers();
	public Optional<Users> getUserById(int userId) throws KbcException;
	public boolean updateUserRole(Users u, String modifiedBy) throws Exception;
	public boolean updateUserStatus(Users u, String modifiedBy) throws Exception;
	public boolean updateUser(Users u, String modifiedBy) throws Exception;
}
