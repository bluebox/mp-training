package com.library.domain;

public class Member {
	private int id;
	private String name;
	private String email;
	private long mobile;
	private char gender;
	private String address;
	public Member(String name, String email, long mobile, char gender, String address) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.address = address;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public long getMobile() {
		return mobile;
	}
	public char getGender() {
		return gender;
	}
	public String getAddress() {
		return address;
	}
	
	//REMOVE it 
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", gender=" + gender
				+ ", address=" + address + "]";
	}
	
	
	
	

}
