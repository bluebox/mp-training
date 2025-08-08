package com.example.Validation.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class User {

	@NotBlank(message = "Username is mandatory")
	@Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
	private String username;

	@NotBlank(message = "Email is mandatory")
	@Email(message = "Invalid email address")
	private String email;

	@NotBlank(message = "Password is mandatory")
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;

	
}
