package com.library.dao;

public class MemberLog {
	private int memberId;
    private String name;
    private String email;
    private long mobile;
    private char gender;
    private String address;
	public MemberLog(int memberId, String name, String email, long mobile, char gender, String address) {
		this.memberId = memberId;
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
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public char getGender() {
		return gender;
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
	@Override
	public String toString() {
		return "Member_Log [memberId=" + memberId + ", name=" + name + ", email=" + email + ", mobile=" + mobile
				+ ", gender=" + gender + ", address=" + address + "]";
	}
}