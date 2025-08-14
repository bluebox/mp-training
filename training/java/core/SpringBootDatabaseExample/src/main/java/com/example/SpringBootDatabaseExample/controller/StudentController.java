package com.example.SpringBootDatabaseExample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootDatabaseExample.entity.Student;
import com.example.SpringBootDatabaseExample.repo.StudentRepo;

@RestController
public class StudentController {

	@Autowired
	StudentRepo studentRepo;

//	@PostMapping("/api/students")
//	public Student saveStudent(@RequestBody Student student) {
//
//		return studentRepo.save(student);
//
//	}

	@PostMapping("/api/students")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {

		return new ResponseEntity<>(studentRepo.save(student), HttpStatus.CREATED);

	}

	@GetMapping("/api/students")
	public List<Student> getAllStudents() {

		return studentRepo.findAll();
	}

	@GetMapping("/api/students/{id}")
	public Student getStudentById(@PathVariable Integer id) {
		return studentRepo.findById(id).orElse(null);
	}

	@PutMapping("/api/students/{id}")
	public Student updateStudent(@PathVariable Integer id, @RequestBody Student student) {

		Student student1 = studentRepo.findById(id).orElse(null);
		if (student1 != null) {
			student1.setName(student.getName());
			student1.setEmail(student.getEmail());
			student1.setAddress(student.getAddress());
			return studentRepo.save(student1);
		} else {
			return null;
		}
	}

	@DeleteMapping("/api/students/{id}")
	public void deleteStudent(@PathVariable Integer id) {

		studentRepo.deleteById(id);
	}
}
