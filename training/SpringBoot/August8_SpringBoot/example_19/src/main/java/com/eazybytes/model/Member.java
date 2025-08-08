package com.eazybytes.model;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.*;

@Component
public class Member {
	
	@NotNull(message="the id can't be null")
	private int id;
	
	@NotEmpty(message="name can't be empty i.e. not null+only white space alloweed ")
	private String name;
	
	@NotBlank(message="username can't be blank i.e. not null+(trimmed length>0)")
	@Size(min=2,max=30,message="username length should be in between 2 and 30")
	private String username;
	
	@Positive(message="age should be positive")
	@Min(value=10)
	@Max(value=60)
	private int age;
	
	@Pattern(regexp="^[0-9]{10}$",message="mobile number should consist of 10 digits")
	private String mobile;
	 
	@Email(message="email should in email formate only")
	private String email;
	
	
	@Digits(integer=5,fraction=2,message="the salary max allowed integer places are 5 and the fractional part is 2")
	private double salary;
	
	@Past(message="the dob must be the past value")
	private LocalDate dob;
	
	
	@FutureOrPresent(message="the subscription end date is today or any future date not be past date!!!")
	private LocalDate subscriptionend;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public LocalDate getSubscriptionend() {
		return subscriptionend;
	}

	public void setSubscriptionend(LocalDate subscriptionend) {
		this.subscriptionend = subscriptionend;
	}
	
	
	
	

}
