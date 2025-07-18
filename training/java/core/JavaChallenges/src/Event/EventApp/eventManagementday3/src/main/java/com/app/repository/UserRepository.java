package com.app.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.app.Mapper.UserMapper;
import com.app.model.User;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UserRepository {

	@Autowired
	private JdbcTemplate jdbc;

	public int addUser(User u) throws Exception {
		String sql = "INSERT INTO users(user_id, name, phn_number, email, role, gender, status, dept) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		int rowsAffected = jdbc.update(sql, u.getUserId(), u.getName(), u.getPhnNumber(), u.getEmail(), u.getRole(),
				u.getGender().getCode(), u.getStatus().getCode(), u.getDept());
		if (rowsAffected > 0) {
			log.info("User Added Successfully");
		} else {
			throw new Exception("Error in Adding the User");
		}
		return rowsAffected;
	}

	public int updateUser(User u) throws Exception {
		String sql = "UPDATE users SET name=?, phn_number=?, email=?, role=?, gender=?, status=?, dept=? WHERE user_id=?";
		int rowsAffected = jdbc.update(sql, u.getName(), u.getPhnNumber(), u.getEmail(), u.getRole(),
				u.getGender().getCode(), u.getStatus().getCode(), u.getDept(), u.getUserId());
		if (rowsAffected > 0) {
			log.info("User Updated Successfully");
		} else {
			throw new Exception("Error in Updating the User");
		}
		return rowsAffected;
	}

	public int deleteUser(int userId) throws Exception {
		String sql = "DELETE FROM users WHERE user_id=?";
		int rowsAffected = jdbc.update(sql, userId);
		if (rowsAffected > 0) {
			log.info("User Deleted Successfully");
		} else {
			throw new Exception("Error in Deleting the User");
		}
		return rowsAffected;
	}

    public List<User> getAllUsers() throws Exception{
        List<User> users=null;
        try
        {
            String sql = "SELECT user_id, name, phn_number, email, role, gender, status, dept FROM users";
        	users=jdbc.query(sql, new UserMapper());
        }
        catch(Exception e)
        {
        	log.error(e.getMessage());
        	throw new Exception("No Users");
        }
        
        return users;
    }

    public User getUserbyId(int userId) throws Exception{
        User user=null;
        try
        {
            String sql = "SELECT user_id, name, phn_number, email, role, gender, status, dept FROM users WHERE user_id=?";

        	user=jdbc.queryForObject(sql, new UserMapper(), userId);
        }
        catch(Exception e)
        {
        	log.error(e.getMessage());
        	throw new Exception("No Users find with this User Id"+userId);
        }
        return user;
    }
	

}
