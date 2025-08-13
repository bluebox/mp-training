package com.example.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.web.model.Student;
import com.example.web.repo.StudentRepository;
import com.example.web.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentRepository studentRepository;
	
	public String saveStudent(Student student) {
		System.out.println("Before log");
		log.info("This is log");
		System.out.println("AFTER LOG");
		studentRepository.save(student);
		return "Student Registered Successfully";
	}
	
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(int id) {
		return studentRepository.getStudentById(id);
	}

	@Override
	public String updateStudent(Student student,int id) {
		try {
			student.setId(id);
			studentRepository.updateStudent(student);
		}
		catch (Exception e) {
			return "Error in updating the student";
		}
		return "Student Details Updated Successfully";
	}

	@Override
	public String deleteStudent(Student student,int id) {
		try {
			student.setId(id);
			studentRepository.deleteStudent(student);
		}
		catch (Exception e) {
			return "Error in deleting the student";
		}
		return "Student deleted successfully";
	}
}
