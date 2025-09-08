package com.medplus.Roles_backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.medplus.Roles_backend.domain.User;
import com.medplus.Roles_backend.exception.UserValidationException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao implements UserDaoInterface {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public User findByUsername(String username) {
		String sql = """
				select id, first_name, last_name, username, password
				from main_table
				where username = :username
				""";

		MapSqlParameterSource params = new MapSqlParameterSource("username", username);

		try {
			List<User> users = namedParameterJdbcTemplate.query(sql, params, new UserRowMapper());
			return users.isEmpty() ? null : users.get(0);
		} catch (Exception e) {
			throw new UserValidationException("Failed to fetch user by username: " + username);
		}
	}

	public User findById(String Id) {
		String sql = """
				select id, first_name, last_name, username, password
				from main_table
				where id = :id
				""";

		MapSqlParameterSource params = new MapSqlParameterSource("id", Id);

		try {
			List<User> users = namedParameterJdbcTemplate.query(sql, params, new UserRowMapper());
			return users.isEmpty() ? null : users.get(0);
		} catch (Exception e) {
			throw new UserValidationException("Failed to fetch user by id: " + Id);
		}
	}

	public int updatePassword(String userId, String encryptedPassword) {
		String sql = """
				update main_table
				set password = :password
				where id = :id
				""";

		MapSqlParameterSource params = new MapSqlParameterSource().addValue("password", encryptedPassword)
				.addValue("id", userId);

		try {
			return namedParameterJdbcTemplate.update(sql, params);
		} catch (Exception e) {
			throw new UserValidationException("Failed to update password for user id: " + userId);
		}
	}

	private static class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setFirstname(rs.getString("first_name"));
			user.setLastname(rs.getString("last_name"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			return user;
		}
	}
}
