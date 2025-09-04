package com.medplus.exptracker.DaoImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.medplus.exptracker.Dao.UserDAO;
import com.medplus.exptracker.Model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Optional<User> findByUsername(String username) {
		User user = new User();
		String getUserSql = "SELECT id, username, password, role_id, manager_id FROM users WHERE username = ? LIMIT 1";
		user= jdbcTemplate.queryForObject(getUserSql, new BeanPropertyRowMapper<>(User.class), username);
		return Optional.ofNullable(user);
	}
	
	@Override
	public void addUser(User user) {
		String addUserSql = "INSERT INTO users (username,password,role_id,manager_id) VALUES(?,?,?,?)";
		int effectedRows = jdbcTemplate.update(addUserSql,
										user.getUsername(),
										user.getPassword(),
										user.getRole_id(),
										user.getManager_id());
		if(effectedRows == 0 ) {
			throw new RuntimeException("Could not create new user");
		}
	}
	
	@Override
	public Optional<User> getUserById(Integer userId) {
		String getUserByIdSql = "SELECT id, username, password, role_id, manager_id FROM users WHERE id = ?";
		User user= jdbcTemplate.queryForObject(getUserByIdSql, new BeanPropertyRowMapper<>(User.class), userId);
		return Optional.ofNullable(user);
	}
	
	@Override
	public List<User> getUsersByManagerId(Integer managerId) {
        String sql = "SELECT id, username, password, role_id, manager_id FROM users WHERE manager_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), managerId);
    }
	
	
}