package com.lms.LMS_Springboot.Model;

import org.springframework.stereotype.Component;

import com.lms.LMS_Springboot.Model.checking_enum.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Component 
@Data
public class Member {
	
	
	private int memberid;
	
	@NotNull(message="name should not be null")
	private String name;
	@NotNull(message="email should not be null")
	@Email(message="email is invalid")
	@NotBlank(message="email should not be blank")
	private String email;
	@NotNull(message="mobile should not be null")
	@Max(value=9999999999L,message="mobile should be 10 digits")
	@Min(value=1000000000L,message="mobile should be 10 digits")
	private Long mobile;
	@NotNull(message="address should not be null")
	private String address;
	@NotNull(message="gender should not be null")
	private checking_enum.Gender gender;

}
