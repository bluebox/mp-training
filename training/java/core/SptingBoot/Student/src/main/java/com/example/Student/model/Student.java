package com.example.Student.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Student {
	
	@NotBlank(message = "first name cannot be blank")
	@Size(min = 3, message = "first name must be more than 3 letters")
	private String firstName;

	@NotBlank(message = "last name cannot be blank")
	@Size(min = 3, message = "first name must be more than 3 letters")
	private String lastName;

	@NotBlank(message = "gender name cannot be blank")
	@Size(min = 3, message = "first name must be more than 3 letters")
	private String gender;

	@Min(value = 18, message = "Age need to be minimum 18")
	@Max(value = 40, message = "Age cannot be greater than 40")
	private int age;

	@NotBlank(message = "address  cannot be blank")
	@Size(min = 3, message = "first name must be more than 3 letters")
	private String address;

	@NotBlank(message = "email cannot be blank")
	@Email(message = "Email need to be valid")
	private String email;

	@NotBlank(message = "password  cannot be blank")
	@Size(min = 3, message = "password name must be more than 8 characters")
	private String password;

	public Student() {
	}

}
