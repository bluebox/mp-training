package com.example.SpringBootExample3;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Employee {

	@NotNull
	@Size(min = 3, max = 30, message = "User name must  morethan 2 or lessthan 30 characters")
	private String name;

	@NotNull
	@Email(message = "Email Should be valid")
	private String email;

	@Pattern(regexp = "^$|^\\d{10}$", message = "Mobile number must be 10 digits")
	private String mobileNo;

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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", email=" + email + ", mobileNo=" + mobileNo + "]";
	}

}
