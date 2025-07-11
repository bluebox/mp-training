package com.app.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.Mapper.UserMapper;
import com.app.model.User;

@Repository
public class UserRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public UserRepository(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate=jdbcTemplate;
	}
	
	public int addUser(User user) throws Exception
	{
		String sql="INSERT INTO users (user_id,name,phn_number,email,role,gender,status,dept) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		int rows=jdbcTemplate.update(
				sql,
				user.getUser_id(),
				user.getName(),
				user.getPhn_number(),
				user.getEmail(),
				user.getRole(),
				user.getGender().getGender(),
				user.getStatus().getStatus(),
				user.getDept()
				);
		if(rows>0)
		{
			System.out.println("User Added Successfully");
		}
		else
		{
			throw new Exception("Failed to Add the User");
		}
		return rows;
	}
	public int updateUser(User user) throws Exception
	{
		String sql="UPDATE users SET name = ? ,phn_number = ? ,email = ?,role =? ,gender =? ,status =?, dept =? WHERE user_id=?";
		int rows=jdbcTemplate.update(
				sql,
				user.getName(),
				user.getPhn_number(),
				user.getEmail(),
				user.getRole(),
				user.getGender().getGender(),
				user.getStatus().getStatus(),
				user.getDept(),
				user.getUser_id()
				);
		if(rows>0)
		{
			System.out.println("User Updated Successfully");
		}
		else
		{
			throw new Exception("Failed to Update the User");
		}
		return rows;
	}
	
	public int deleteUser(int user_id) throws Exception
	{
		if(user_id<0)
		{
			throw new Exception("Invalid User_Id");
		}
		String sql="DELETE FROM users where user_id=?";
		int rows=jdbcTemplate.update(
				sql,
				user_id
				);
		if(rows>0)
		{
			System.out.println("User Updated Successfully");
		}
		else
		{
			throw new Exception("Failed to Update the User");
		}
		return rows;
	}
	
	public List<User> getAllUsers()
	{
		String sql="select * from users";
		return jdbcTemplate.query(sql, new UserMapper());
	}
	
	public User getUserbyId(int user_id) throws Exception
	{
		if(user_id<0)
		{
			throw new Exception("Invalid User_Id");
		}
		String sql="select * from users where user_id=?";
		return jdbcTemplate.queryForObject(sql,new UserMapper(),sql);
	}

}
