package com.register.model;

import java.sql.Date;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


public class User {
	@NotBlank(message = "Name is mandatory")
	private String name;
	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
	private String email;
	@NotBlank(message="required")
	@Size(min = 5, max = 12,message="length should be from 5-12")
	private String password;
	@NotNull(message="select any one")
	private String gender;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", gender=" + gender + "]";
	}

}
