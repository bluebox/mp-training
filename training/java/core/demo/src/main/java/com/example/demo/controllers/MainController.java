package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Student;

@Controller
public class MainController {

	@GetMapping({ "", "/" })
	public String showProductList() {
		Student student = new Student();
		student.setName("John Doe");
		student.setEmail("kwhdcbh");
		student.setPhoneNumber("123-456-789");
		student.setAge(20);
		System.out.println("Student Name: " + student.getName());
		System.out.println("Student Email: " + student.getEmail());
		System.out.println("Student Phone Number: " + student.getPhoneNumber());
		System.out.println("Student Age: " + student.getAge());

		System.out.println();
		return "index.html";
	}

}
