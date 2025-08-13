package com.example.web.service;

import java.util.List;

import com.example.web.model.Student;

public interface StudentService {
	public String saveStudent(Student student);
	public List<Student> getStudents();
	public Student getStudentById(int id);
	public String updateStudent(Student student,int id);
	public String deleteStudent(Student student,int id);
}
