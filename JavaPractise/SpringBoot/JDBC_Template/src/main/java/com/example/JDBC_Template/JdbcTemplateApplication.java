package com.example.JDBC_Template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.JDBC_Template.dao.StudentDao;
import com.example.JDBC_Template.entity.Student;

@SpringBootApplication
public class JdbcTemplateApplication implements CommandLineRunner{
	@Autowired
	private StudentDao studentDao;

	public static void main(String[] args) {
		SpringApplication.run(JdbcTemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		// Insert 
		Student s1=new Student();
		s1.setId(3);
		s1.setName("Hanuman");
		s1.setBranch("Mech");
		boolean status=studentDao.insertStudent(s1);
		if(status) {
			System.out.println("Student inserted successfully");
		}
		else {
			System.out.println("Student not inserted due to error");
		}
		
		
		
		
		// UPDATE
		/*
		Student student=new Student();
		student.setId(1);
		student.setName("Akash Madhunala");
		student.setBranch("cse");
		boolean status=studentDao.updateStudent(student);
		if(status) {
			System.out.println("Student updated successfully");
		}
		else {
			System.out.println("Student not updated due to error");
		}
		*/
		
		
		//Delete
		/*
		boolean status=studentDao.deleteStudent(1);
		if(status) {
			System.out.println("Student deleted successfully");
		}
		else {
			System.out.println("Student not deleted due to error");
		}
		*/
		
		/*
		//get by student id
		Student student=studentDao.getStudentById(1);
		System.out.println(student);
		*/
		
		
		// Get all students
		
		studentDao.getAllStudents().stream().forEach(student-> System.out.println(student));
		
		
	}

}
