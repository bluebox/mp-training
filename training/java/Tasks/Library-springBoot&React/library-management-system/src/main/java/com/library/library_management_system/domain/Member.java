package com.library.library_management_system.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	private int memberId;

	@NotBlank(message = "Name must not be blank")
	@Size(min = 3, max = 60, message = "Name must be between 3 and 60 characters")
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Member name must contain only letters and spaces")
	private String memberName;

	@NotBlank(message = "Email must not be blank")
	@Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email format")
	private String memberMail;

	@NotBlank(message = "Mobile number must not be blank")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Mobile number must be 10 digits and start with 6-9")
	private String mobileNo;

	@NotBlank(message = "Gender must not be blank")
	@Pattern(regexp = "M|F", message = "Gender must be 'M' or 'F'")
	private String gender;

	@NotBlank(message = "Address must not be blank")
	@Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
	private String memberAddress;

	public Member(String memberName, String memberMail, String mobileNo, String gender, String memberAddress) {
		this.memberName = memberName;
		this.memberMail = memberMail;
		this.mobileNo = mobileNo;
		this.gender = gender;
		this.memberAddress = memberAddress;
	}

}
