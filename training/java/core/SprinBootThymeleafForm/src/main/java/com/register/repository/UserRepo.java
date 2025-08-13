package com.register.repository;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.register.model.User;

@Repository
public class UserRepo {
	
	
private JdbcTemplate template;
	
public JdbcTemplate getTemplate() {
	return template;
}

@Autowired
public void setTemplate(JdbcTemplate template) {
	this.template = template;
}

public void save(User user) {
	
	String sql="insert into Users (name , email , password , gender) values(?,?,?,?) ";
	int rows=template.update(sql,user.getName(),user.getEmail(),user.getPassword(),user.getGender());
	System.out.println(rows+" rows affected");
}

public List<User> findAll(){
	
	String sql="select * from Users";
	

	List<User> users=template.query ( sql, (rs,row)->{
		User a=new User();
		a.setName(rs.getString(1));
		a.setEmail(rs.getString(2));
		a.setPassword(rs.getString(3));
		a.setGender(rs.getString(4));
		
		return a;
	});
	
	System.out.println("asdasd");
	return users;
}
}
