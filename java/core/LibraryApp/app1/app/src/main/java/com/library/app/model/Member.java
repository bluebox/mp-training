package com.library.app.model;

public class Member {
	private int memberId;
	private String name;
	private String email;
	private long mobile;
	private Gender gender;
	private String address;

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public char getGender() {
		return gender.name().toUpperCase().charAt(0);
	}

	public void setGender(char gender) {
		this.gender = (gender == 'M' ? Gender.MALE : Gender.FEMALE);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String toString() {
		return "Member{" + "memberId=" + memberId + ", name='" + name + '\'' + ", email='" + email + '\'' + ", mobile="
				+ mobile + ", gender=" + gender + ", address='" + address + '\'' + '}';
	}

}
