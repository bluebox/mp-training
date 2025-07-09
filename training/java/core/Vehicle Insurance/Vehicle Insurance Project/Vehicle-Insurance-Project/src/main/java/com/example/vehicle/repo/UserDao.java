package com.example.vehicle.repo;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.vehicle.model.User;

@Repository
public class UserDao {
	public JdbcTemplate jdbcTemplate; 
	@Autowired
	public UserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	public String addUser(User u) throws SQLException {
		int rowEffected=jdbcTemplate.update("insert into users values(?,?,?,?,?)",u.getUserName(),u.getPassword(),u.getPasswordUpdatedOn(),u.getPasswordUpdatedBy(),u.getCustomerId());
		if(rowEffected>0) {
			return "User inserted successfully";
		}
		else {
			return "Failed to register user";
		}
	}
	public String updatePassword(String username,String password) throws Exception {
		int rowEffected=jdbcTemplate.update("update users set password=? where username=?",password,username);
		if(rowEffected>0) {
			return "Password updated successfully";
		}
		else {
			return "Failed to update password";
		}
	}
}
