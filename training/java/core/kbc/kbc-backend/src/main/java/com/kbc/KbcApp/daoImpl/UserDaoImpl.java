package com.kbc.KbcApp.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kbc.KbcApp.dao.UserDao;
import com.kbc.KbcApp.helper.UserRowMapper;
import com.kbc.KbcApp.pojos.Users;
import com.kbc.KbcApp.utilites.DBQueries;

@Repository
public class UserDaoImpl implements UserDao{
	
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
	
    
	@Override
	public void addUser(Users user) {
		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("username", user.getUsername())
			.addValue("password", user.getPassword())
			.addValue("createdAt", user.getCreatedAt());
		namedParameterJdbcTemplate.update(DBQueries.INSERT_USER, params);
	}

	
	@Override
	public void updateUser(Users user) throws Exception {
		userLog(user.getUserId());
		int usernameFlag=user.getUsername()!=null?1:0;
		int passwordFlag=user.getPassword()!=null?1:0;
		int roleFlag=user.getRole()!=null?1:0;
		int statusFlag=user.getStatus()!=null?1:0;
		int modifiedAtFlag=user.getModifiedAt()!=null?1:0;
		int modifiedByFlag=user.getModifiedBy()!=null?1:0;
		
		StringBuilder sql = new StringBuilder("UPDATE tbl_users SET ");
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		List<String> setClauses = new ArrayList<>();
		if (usernameFlag == 1) {
		    setClauses.add("Username = :username");
		    params.addValue("username", user.getUsername());
		}
		if (passwordFlag == 1) {
		    setClauses.add("Password = :password");
		    params.addValue("password", user.getPassword());
		}
		if (roleFlag == 1) {
		    setClauses.add("Role = :role");
		    params.addValue("role", user.getRole().name());
		}
		if (statusFlag == 1) {
		    setClauses.add("Status = :status");
		    params.addValue("status", user.getStatus().name());
		}
		if (modifiedAtFlag == 1) {
		    setClauses.add("ModifiedAt = :modifiedAt");
		    params.addValue("modifiedAt", user.getModifiedAt());
		}
		if (modifiedByFlag == 1) {
		    setClauses.add("ModifiedBy = :modifiedBy");
		    params.addValue("modifiedBy", user.getModifiedBy());
		}
		
		if (setClauses.isEmpty()) {
		    throw new IllegalArgumentException("No fields to update.");
		}

		sql.append(String.join(", ", setClauses));
		sql.append(" WHERE UserId = :userId");
		params.addValue("userId", user.getUserId());

		namedParameterJdbcTemplate.update(sql.toString(), params);
	}

	@Override
	public List<Users> getUser(Integer userId, String username, String password) {
		int userIdFlag=userId!=null?1:0;
		int usernameFlag=username!=null?1:0;
		int passwordFlag=password!=null?1:0;
		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("userId", userId)
			.addValue("username", username)
			.addValue("password", password)
			.addValue("userIdFlag", userIdFlag)
			.addValue("usernameFlag", usernameFlag)
			.addValue("passwordFlag", passwordFlag);
		return namedParameterJdbcTemplate.query(DBQueries.GET_USER, params, new UserRowMapper());
	}
	
	private void userLog(Integer userId) throws Exception{
		List<Users> users = getUser(userId,null,null);
		if(users.isEmpty()) {
			throw new Exception("User with ID "+userId+"not found");
		}
		Users userData=users.get(0);
		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("userId", userData.getUserId())
			.addValue("username", userData.getUsername())
			.addValue("password", userData.getPassword())
			.addValue("role", userData.getRole().name())
			.addValue("status", userData.getStatus().name())
			.addValue("createdAt", userData.getCreatedAt())
			.addValue("modifiedAt", userData.getModifiedAt())
			.addValue("modifiedBy", userData.getModifiedBy());
		namedParameterJdbcTemplate.update(DBQueries.INSERT_USER_LOG, params);
	}
}
