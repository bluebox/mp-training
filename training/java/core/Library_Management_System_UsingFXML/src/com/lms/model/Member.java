package com.lms.model;

public class Member {
	private int member_Id;
	private String member_Name;
	private String email;
	private String mobile_No;
	private char gender;
	private String address;
	
	
	
	public Member(String member_Name, String email, String mobile_No, char gender,String address) {
		this.member_Name = member_Name;
		this.email = email;
		this.mobile_No = mobile_No;
		this.gender = gender;
		this.setAddress(address);
	}

	public Member(int member_Id, String member_Name, String email, String mobile_No, char gender,String address) {
		this.member_Id = member_Id;
		this.member_Name = member_Name;
		this.email = email;
		this.mobile_No = mobile_No;
		this.gender = gender;
		this.setAddress(address);
	}
	
	public String getMember_Name() {
		return member_Name;
	}
	public void setMember_Name(String member_Name) {
		this.member_Name = member_Name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile_No() {
		return mobile_No;
	}
	public void setMobile_No(String mobile_No) {
		this.mobile_No = mobile_No;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getMember_Id() {
		return member_Id;
	}
	public void setMember_Id(int member_Id) {
		this.member_Id = member_Id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
