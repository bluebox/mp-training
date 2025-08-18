package com.lms.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	
	@NotEmpty(message = "BookId cannot be Empty")
	private int memberId;
	
	@NotBlank(message = "name cannot be blank")
	@Size(min = 3, message = "name must not be less than 3 letters")
	private String name;
	
	@NotBlank(message = "email cannot be blank")
	@Email(message = "Email need to be valid")
	private String email;
	
	@Max(10)
	private Long mobile;
	
	@NotBlank(message = "gender cannot be blank")
	private String gender;
	
	@NotBlank(message = "address cannot be blank")
	private String address;
	
	public Member(String name, String email, Long mobile, String gender, String address) {
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.address = address;
	}

}
