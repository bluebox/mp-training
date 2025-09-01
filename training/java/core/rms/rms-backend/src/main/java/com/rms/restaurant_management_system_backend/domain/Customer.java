package com.rms.restaurant_management_system_backend.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	private int custId;

	@NotBlank(message = "please enter your name")
	@Size(min = 3, max = 60, message = "Name length is must be greater than 3 and lessthan 60")
	@Pattern(regexp = "[a-zA-Z\\s]+", message = "Name contains only alphabets")
	private String name;
	
	@NotBlank(message = "please enter your mobile number")
	@Size(min = 10, max = 10, message = "mobile number must be 10 digits")
	@Pattern(regexp = "^\\d+$", message = "Mobile Number not conatins alphabeticals")
	private String phone;
	
	public Customer(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}
}
