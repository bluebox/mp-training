package com.example.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private int member_Id;
	@NotNull(message="Member Name should not be null")
	@Pattern(regexp = "^[A-Za-z]{2}[A-Za-z\\s]{0,218}$",message="Please enter a valid Member Name")
	private String member_Name;
	@NotNull(message="Email should not be null")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",message="Please enter a valid email address")
	private String email;
	@NotNull(message="Mobile Number should not be null")
	@Pattern(regexp = "\\d{10}", message = "Please enter a valid phone number")
	private String mobile_No;
	@NotNull(message = "Gender Should not be empty")
	@Pattern(regexp = "^[MF]$",message="Please select Your gender Correctly")
	private String gender;
	@NotNull(message = "Address Should not be null")
	@NotBlank(message = "Address Should not be empty")
	private String address;

	public Member(String member_Name, String email, String mobile_No, String gender,String address) {
		this.member_Name = member_Name;
		this.email = email;
		this.mobile_No = mobile_No;
		this.gender = gender;
		this.setAddress(address);
	}

}
