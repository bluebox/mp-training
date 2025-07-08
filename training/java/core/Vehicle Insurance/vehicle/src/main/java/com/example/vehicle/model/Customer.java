package com.example.vehicle.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.vehicle.enums.Gender;

import lombok.Data;

@Data
public class Customer {
	private int customerId;
	@NotBlank(message="Name must not be Blank")
	@Size(min = 3, message = "Name must be atleast 3 characters")
	private String name;
	@NotBlank(message = "Email must not be Null")
	@Email(message = "Must be email Pattern")
	private String email;
	@NotBlank(message = "Mobile number must not be Null")
	@Pattern(regexp="[5-9]{1}[0-9]{9}")
	private String contact;
	@NotNull(message="Gender must not be null")
	private Gender gender;
	@NotNull(message="Age must not be null")
	@Min(message="Age must be 18 or above", value = 18)
	private int age;
	@NotBlank(message="Occupation must not be null")
	private String occupation;
	@NotNull(message="Income must not be null")
	private float income;
	@NotBlank(message="Address must not be null")
	private String address;
	private char status;
	private LocalDateTime customerUpdatedOn;
	private String  customerUpdatedBy;
	private String createdBy;
	

}
