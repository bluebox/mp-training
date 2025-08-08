package com.eazybytes.serviceImpl;

import com.eazybytes.dao.StudentDaoImpl;
import com.eazybytes.model.Student;
import com.eazybytes.service.*;

import java.lang.annotation.Annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StudentImpl implements ServiceInf{
	
	
	@Autowired
	public StudentDaoImpl studentdao;
	
	public boolean addStudent(Student s) {
		
		
		return studentdao.addStudentDao(s);
		
	}

	@Override
	public String viewStudents() {
		
		return studentdao.viewStudents();
	}
	

	
	
	
	

}
