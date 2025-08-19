package com.example.library.domain;

import com.example.library.constants.MemberGender;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	

	private int memberId;

	@NotBlank(message = "please enter your name")
	@Size(min = 3, max = 60, message = "Name length is must be greater than 3 and lessthan 60")
	@Pattern(regexp = "[a-zA-Z\\s]+", message = "Name contains only alphabets")
	private String memberName;

	@NotBlank(message = "please enter your email")
	@Email(message = "Invalid email format")
	private String memberMail;

	@NotBlank(message = "please enter your mobile number")
	@Size(min = 10, max = 10, message = "mmobile number must be 10 digits")
	@Pattern(regexp = "^\\d+$", message = "Mobile Number not conatins alphabeticals")
	private String mobileNo;

	@NotNull(message = "Please select your gender")
	private MemberGender gender;

	@NotBlank(message = "Address required")
	private String memberAddress;
	
	
}
