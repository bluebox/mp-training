package com.rms.restaurant_management_system_backend.domain;

import java.sql.Date;

import com.rms.restaurant_management_system_backend.constant.Designation;
import com.rms.restaurant_management_system_backend.constant.EmployeeStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employees {

	private Integer empId;

	@NotBlank(message = "please enter your name")
	@Size(min = 3, max = 60, message = "Name length is must be greater than 3 and lessthan 60")
	@Pattern(regexp = "[a-zA-Z\\s]+", message = "Name contains only alphabets")
	private String name;

	@NotBlank(message = "please enter your email")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "please enter your mobile number")
	@Size(min = 10, max = 10, message = "mobile number must be 10 digits")
	@Pattern(regexp = "^\\d+$", message = "Mobile Number not conatins alphabeticals")
	private String phone;

	private EmployeeStatus status = EmployeeStatus.ACTIVE;

	private Designation designation;

	private Date join_date;

	private Date leaving_date;

}
