package com.example.vehicle.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.vehicle.model.User;
import com.example.vehicle.rowMappers.UserRowMapper;

@Repository
public class UserDao {
	public JdbcTemplate jdbcTemplate; 
	
	@Autowired
	public UserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	public String addUser(User u) throws SQLException {
		int rowEffected=jdbcTemplate.update("insert into users(username,password,password_updated_on,password_updated_by,customer_id) values(?,?,?,?,?)",u.getUsername(),u.getPassword(),LocalDateTime.now(),u.getPasswordUpdatedBy(),u.getCustomerId());
		if(rowEffected>0) {
			return "User inserted successfully";
		}
		else {
			return "Failed to register user";
		}
	}
	
	public String updatePassword(String username,String password,String passwordUpdatedBy) throws SQLException{
		int rowEffected=jdbcTemplate.update("update users set password=?,password_updated_on=?,password_updated_by=? where username=?",password,LocalDateTime.now(),passwordUpdatedBy,username);
		if(rowEffected>0) {
			return "Password updated successfully";
		}
		else {
			return "Failed to update password";
		}
	}
	
	public User getUserByUsername(String username) throws SQLException {
	  User user;
	  String sql="Select username,password,password_updated_on,password_updated_by,customer_id from users where username=?";
	  user=jdbcTemplate.queryForObject(sql,new UserRowMapper(),username);
	  return user;
	}
	
	public List<User> getAllUsers(){
		String sql="Select username,password,password_updated_on,password_updated_by,customer_id from users";
		return jdbcTemplate.query(sql,new UserRowMapper());
	}
	
	public String deleteUser(String username) {
		String sql="Delete from users where username=?";
		int rowsAffected=jdbcTemplate.update(sql,username);
		if(rowsAffected==0) {
			return "Deleted User Successfully";
		}
		else {
			return "Error Occured During User Deletion";
		}
	}
}
