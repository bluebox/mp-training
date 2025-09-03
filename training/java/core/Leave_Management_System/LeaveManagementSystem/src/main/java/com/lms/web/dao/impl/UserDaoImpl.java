package com.lms.web.dao.impl;

import com.lms.web.dao.UserDao;
import com.lms.web.exceptions.DAOException;
import com.lms.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	private RowMapper<User> mapper;
	@Override
    public User findByEmail(String email) {
    	if(email==null) {
    		throw new DAOException("Email cannot be null");
    	}
    	try {
    		String sql = "SELECT * FROM users WHERE userEmail = ?";
            mapper = (rs,rowNum) -> {
            	 User user = new User();
            	 user.setEmployeeId(rs.getInt("userId"));
            	 user.setUserEmail(rs.getString("userEmail"));
            	 user.setUserPassword(rs.getString("userPassword"));
            	 user.setUserRole(rs.getString("userRole"));
                 return user;
            };
            User user = jdbcTemplate.queryForObject(sql,mapper, email);
            return user;
    	}
        catch (Exception e) {
            throw new DAOException("Error fetching user", e.getCause());
        }
    }
}
