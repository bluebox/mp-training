package com.eazybytes.dao;

import org.springframework.stereotype.Repository;

import com.eazybytes.daoImpl.DaoInf;
import com.eazybytes.model.Student;

@Repository
public class StudentDaoImpl implements DaoInf{

	@Override
	public boolean addStudentDao(Student s) {
		
		System.out.println("Iam dao of student ");
		return true;
	}

	@Override
	public String viewStudents() {
	
		return "Student details are viwed here";
	}

}
