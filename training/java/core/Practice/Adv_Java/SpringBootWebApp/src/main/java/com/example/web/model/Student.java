package com.example.web.model;

import org.springframework.stereotype.Component;

import com.example.web.annotations.Age;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Component
@Data
public class Student {
	private int id;
	@NotNull(message="Student Name should not be null")
	@Pattern(regexp = "^[A-Za-z]{2}[A-Za-z\\s]{0,218}$",message="Please enter a valid name")
	private String fname;
	@NotNull(message="Student Sur Name should not be null")
	@Pattern(regexp = "^[A-Za-z]{2}[A-Za-z\\s]{0,218}$", message="Please enter a valid sur name")
	private String lname;
	@NotNull(message="Student email should not be null")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",message="Please enter a valid email address")
	private String email;
	@NotNull(message = "Student Date of Birth Should not be empty")
	@Age(message = "Student must be at least 18 years old")
	@Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$",message="Please enter a valid Date of Birth")
	private String dob;
	@NotNull(message = "Student Gender Should not be empty")
	@Pattern(regexp = "^[MF]$",message="Please select Your gender Correctly")
	private String gender;
}