package com.example.spring.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.spring.model.User;
import com.example.spring.rowmappers.UserRowMapper;

@Repository
public class UserRepository {
	
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    	this.jdbcTemplate=jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    
//    public int saveContactMsg(User user){
//        String sql = "INSERT INTO users (name,age,pnum,email) VALUES (?,?,?,?)";
//        return jdbcTemplate.update(sql,user.getName(),user.getAge(),user.getPnum(),user.getEmail());
//    }
    
    public int saveUsers(User user){
    	 String sql = "INSERT INTO users (name,age,pnum,email) VALUES (:name,:age,:pnum,:email)";
    	 
	    MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("name", user.getName());
	    params.addValue("age", user.getAge());
	    params.addValue("pnum", user.getPnum());
	    params.addValue("email", user.getEmail());

	    return namedParameterJdbcTemplate.update(sql, params);
    }
    
    
    
    
    public List<User> findUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql,new UserRowMapper());
    }

}
