package com.casestudy.spring.library.beans;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private int memberId;
	@NotBlank
	@Size(min = 3, max = 50, message = "The title should be between 3 and 50 chars")
	@Pattern(regexp = "^(?!.*  )[A-Za-z ]+$",
    message = "Name Must contain only letters and spaces, and no consecutive spaces")
	private String name;
	@Email
	@NotBlank(message = "Email cannot be blank")
	private String email;
	@Min(value = 1000000000L, message = "Mobile number must be at least 10 digits")
	@Max(value = 9999999999L, message = "Mobile number must be at most 10 digits")
	private long mobile;
	private Gender gender;
	@NotBlank
	@Size(min = 3, max = 200, message = "The title should be between 3 and 200 chars")
	@Pattern(regexp = "^(?!.*  )[A-Za-z \\\\-\\\\/|,:]+$",
    message = "Address  Must contain only letters and spaces, and no consecutive spaces")
	private String address;

//
//	public Member(int memberId, String name, String email, long mobile, Gender gender, String address) {
//		this.memberId = memberId;
//		this.name = name;
//		this.email = email;
//		this.mobile = mobile;
//		this.gender = gender;
//		this.address = address;
//	}

	public Member(String name, String email, long mobile, Gender gender, String address) {
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.address = address;
	}

//	// Getters and Setters
//	public int getMemberId() {
//		return memberId;
//	}
//
//	public void setMemberId(int memberId) {
//		this.memberId = memberId;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public long getMobile() {
//		return mobile;
//	}
//
//	public void setMobile(long mobile) {
//		this.mobile = mobile;
//	}
//
//	public Gender getGender() {
//		return gender;
//	}
//
//	public void setGender(Gender gender) {
//		this.gender = gender;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", name=" + name + ", email=" + email + ", mobile=" + mobile
				+ ", gender=" + gender + ", address=" + address + "]";
	}
}
