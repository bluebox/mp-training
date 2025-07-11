package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private final UserRepository u;
	
	public UserService(UserRepository u)
	{
		this.u=u;
	}
	
	public boolean addUser(User user) throws Exception
	{
		boolean isAdded=false;
		int rows=u.addUser(user);
		if(rows>0)
		{
			isAdded=true;
		}
		return isAdded;
	}
	
	public boolean updateUser(User user) throws Exception
	{
		boolean isUpdated=false;
		int rows=u.updateUser(user);
		if(rows>0)
		{
			isUpdated=true;
		}
		return isUpdated;
	}
	
	public boolean deleteUser(int user_id) throws Exception
	{
		boolean isDeleted=false;
		int rows=u.deleteUser(user_id);
		if(rows>0)
		{
			isDeleted=true;
		}
		return isDeleted;
	}
	
	public List<User> getAllUsers()
	{
		return u.getAllUsers();
	}
	
	public User getUserbyId(int user_id) throws Exception
	{
		return u.getUserbyId(user_id);
	}
	
}
