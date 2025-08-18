package com.example.Student.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.Student.model.Student;

@Repository
public class StudentRepo {

	@Autowired
	private JdbcTemplate template;

	public StudentRepo() {
	}

	public void save(Student student) {

		String que = "insert into Student(firstName,lastName,gender,age,address,email,password) values(?,?,?,?,?,?,?)";
		template.update(que, student.getFirstName(), student.getLastName(), student.getGender(), student.getAge(),
				student.getAddress(), student.getEmail(), student.getPassword());
	}

	public List<Student> viewAll() {

		String que = "select * from Student";
		RowMapper<Student> mapper = new RowMapper<Student>() {

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();
				student.setFirstName(rs.getString(1));
				student.setLastName(rs.getString(2));
				student.setGender(rs.getString(3));
				student.setAge(rs.getInt(4));
				student.setAddress(rs.getString(5));
				student.setEmail(rs.getString(6));
				student.setPassword(rs.getString(7));

				return student;
			}
		};
		return template.query(que, mapper);
	}
}

//
//package com.register.repository;
//import java.util.*;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import com.register.model.User;
//
//@Repository
//public class UserRepo {
//	
//	
//private JdbcTemplate template;
//	
//public JdbcTemplate getTemplate() {
//	return template;
//}
//
//@Autowired
//public void setTemplate(JdbcTemplate template) {
//	this.template = template;
//}
//
//public void save(User user) {
//	
//	String sql="insert into Users (name , email , password , gender) values(?,?,?,?) ";
//	int rows=template.update(sql,user.getName(),user.getEmail(),user.getPassword(),user.getGender());
//	System.out.println(rows+" rows affected");
//}
//
//public List<User> findAll(){
//	
//	String sql="select * from Users";
//	
//
//	List<User> users=template.query ( sql, (rs,row)->{
//		User a=new User();
//		a.setName(rs.getString(1));
//		a.setEmail(rs.getString(2));
//		a.setPassword(rs.getString(3));
//		a.setGender(rs.getString(4));
//		
//		return a;
//	});
//	
//	System.out.println("asdasd");
//	return users;
//}
//}
