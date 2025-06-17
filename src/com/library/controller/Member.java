package com.library.controller;

public class Member {
    private int memberId;
    private String name;
    private String email;
    private long mobile;
    private Gender gender;
    private String address;
    public enum Gender{
    	MALE,FEMALE
    }
    public Member() {
    }
    public Member(int memberId,String name,String email,long mobile,Gender gender,String address) {
    	this.memberId=memberId;
    	this.name=name;
    	this.email=email;
    	this.mobile=mobile;
    	this.gender=gender;
    	this.address=address;
    }
	public Member(String name, String email, long mobile, Gender gender, String address) {
		// TODO Auto-generated constructor stub
		this.name=name;
    	this.email=email;
    	this.mobile=mobile;
    	this.gender=gender;
    	this.address=address;
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
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    
}
