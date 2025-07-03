package com.library.app.model;

import javax.validation.constraints.*;
import lombok.Data;

@Data
public class Member {
	private int memberId;
	@NotBlank(message = "Name not to be Null!")
	@Size(min = 3, message = "Name has atleast 3 characters")
	private String name;
	@NotBlank(message = "Email not to be Null!")
	@Email(message = "Must be email Pattern")
	private String email;
	@NotNull(message = "Mobile number not to be Null!")
	@Min(value = 1000000000L, message = "Mobile number must be at least 10 digits!")
	@Max(value = 9999999999L, message = "Mobile number must be at most 10 digits!")
	private Long mobile;
	@NotNull(message="Gender not to be null!")
	private Gender gender;
	@NotNull(message="Address not to be null!")
	private String address;

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
//
	public String toString() {
		return "Member{" + "memberId=" + memberId + ", name='" + name + '\'' + ", email='" + email + '\'' + ", mobile="
				+ mobile + ", gender=" + gender + ", address='" + address + '\'' + '}';
	}

}
