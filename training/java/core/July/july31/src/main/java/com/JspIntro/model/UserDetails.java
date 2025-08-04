package com.JspIntro.model;

public class UserDetails {

	private String name;
	private String email;
	private long phonenumber;

	private UserDetails details;

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

	public long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.details = userDetails;
	}

	public UserDetails getUserDetails() {
		return details;
	}
}
