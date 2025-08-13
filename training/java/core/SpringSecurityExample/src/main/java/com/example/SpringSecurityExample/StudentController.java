package com.example.SpringSecurityExample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {

	private List<Student> students = new ArrayList<>(List.of(new Student(1, "tarun", 77), new Student(2, "Ravi", 88),
			new Student(3, "Ramana", 87), new Student(4, "King", 88)

	));

	@GetMapping("/students")
	public List<Student> getStudents() {

		return students;

	}

	@GetMapping("/token")
	public CsrfToken getCsrfToken(HttpServletRequest request) {

		return (CsrfToken) request.getAttribute("_csrf");
	}

	@PostMapping("/student")
	public Student addStudent(@RequestBody Student student) {
		students.add(student);
		return student;
	}

}
