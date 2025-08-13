package com.example.JDBC_Template.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.JDBC_Template.entity.Student;

@Repository
public class StudentDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//	public boolean insertStudent(Student student) {
//		boolean status=false;
//		try {
//			String query="Insert into student(id,name,branch) values(?,?,?)";
//			int count=jdbcTemplate.update(query,student.getId(),student.getName(),student.getBranch());
//			if(count>0) status=true;
//			else status=false;
//		}
//		catch(Exception e) {
//			status=false;
//			e.printStackTrace();
//		}
//		return status;
//	}
	
	// using named parameters 
	public boolean insertStudent(Student student) {
		boolean status = false;
		try {
			String query = "Insert into student(id,name,branch) values(:id,:name,:branch)";

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("id", student.getId());
			params.addValue("name", student.getName());
			params.addValue("branch", student.getBranch());

			int count = namedParameterJdbcTemplate.update(query, params);

			if (count > 0) {
				status = true;
			} else {
				status = false;
			}
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	public boolean updateStudent(Student student) {
		boolean status = false;
		try {
			String query = "update student set name=?,branch=? where id=?";
			int count = jdbcTemplate.update(query, student.getName(), student.getBranch(), student.getId());
			if (count > 0)
				status = true;
			else
				status = false;
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	public boolean deleteStudent(int id) {
		boolean status = false;
		try {
			String query = "delete from student where id=?";
			int count = jdbcTemplate.update(query, id);
			if (count > 0)
				status = true;
			else
				status = false;
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	public Student getStudentById(int id) {
		String query = "select * from student where id=?";
		return jdbcTemplate.queryForObject(query, new StudentRowMapper(), id);
	}

	public List<Student> getAllStudents() {
		String query = "Select * from student";
		return jdbcTemplate.query(query, new StudentRowMapper());
	}

	public static final class StudentRowMapper implements RowMapper<Student> {

		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student student = new Student();
			student.setId(rs.getInt("id"));
			student.setName(rs.getString("name"));
			student.setBranch(rs.getString("branch"));
			return student;
		}

	}
}
