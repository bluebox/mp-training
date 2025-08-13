package com.example.web.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.web.model.Student;

@Repository
public class StudentRepository {
	
	private JdbcTemplate template;
	RowMapper<Student> mapper;
	public JdbcTemplate getTemplate() {
		return template;
	}
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public void save(Student student){
		String query = "insert into students(fname,lname,email,dob,gender) values (?,?,?,?,?)";
		System.out.println("Added");
		template.update(query,student.getFname(),student.getLname(),student.getEmail(),student.getDob(),student.getGender());
	}
	public List<Student> findAll(){
		String query= "select * from students";
		mapper = (rs, rowNum) -> {
				Student student = new Student();
				student.setId(rs.getInt(1));
				student.setFname(rs.getString(2));
				student.setLname(rs.getString(3));
				student.setEmail(rs.getString(4));
				student.setDob(String.valueOf(rs.getDate(5)));
				student.setGender(rs.getString(6));
				return student;
		};
		List<Student> students=template.query(query,mapper);
		return students;
	}
	
	public Student getStudentById(int id) {
		String query = "select * from students where id=?";
		mapper = (rs, rowNum) -> {
			Student student = new Student();
			student.setId(rs.getInt(1));
			student.setFname(rs.getString(2));
			student.setLname(rs.getString(3));
			student.setEmail(rs.getString(4));
			student.setDob(String.valueOf(rs.getDate(5)));
			student.setGender(rs.getString(6));
			return student;
		};
		Student student=template.queryForObject(query, mapper,id);
		return student;
	}
	
	public int updateStudent(Student student){
		String query = "update students set fname=?,lname=?,email=?,dob=?,gender=? where id=?";
		int rowsAffected=template.update(query,student.getFname(),student.getLname(),student.getEmail(),student.getDob(),student.getGender(),student.getId());
		System.out.println("updated");
		return rowsAffected;
	}
	
	public int deleteStudent(Student student){
		String query = "delete from students where id=?";
		int rowsAffected = template.update(query,student.getId());
		System.out.println("student deleted");
		return rowsAffected;
	}
}
