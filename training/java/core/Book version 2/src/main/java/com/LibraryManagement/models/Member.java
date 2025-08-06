package com.LibraryManagement.models;

public class Member {

	private int memberId;
	private String name;
	private String email;
	private Long mobile;
	private char gender;
	private String address;

	public Member(int memberId, String name, String email, Long mobile, char gender, String address) {
		this.memberId = memberId;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.address = address;
	}

	public Member(String name, String email, Long mobile, char gender, String address) {
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.address = address;
	}

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

	public char getGender() {
		return gender;
	}

	public Long getMobile() {
		return mobile;
	}
	
	public String getGenderAsString() {
	    return String.valueOf(gender);
	}

	
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
