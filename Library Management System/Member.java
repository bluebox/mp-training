package com.LibraryManagement.models;

import javafx.beans.property.*;

public class Member {

	private IntegerProperty memberId = new SimpleIntegerProperty();
	private StringProperty name = new SimpleStringProperty();
	private StringProperty email = new SimpleStringProperty();
	private LongProperty mobile = new SimpleLongProperty();
	private StringProperty gender = new SimpleStringProperty();
	private StringProperty address = new SimpleStringProperty();

	public Member(int memberId, String name, String email, Long mobile, String gender, String address) {

		this.memberId.set(memberId);
		this.name.set(name);
		this.email.set(email);
		this.mobile.set(mobile);
		this.gender.set(gender);
		this.address.set(address);
	}

	public Member(String name, String email, Long mobile, String gender, String address) {
		this.name.set(name);
		this.email.set(email);
		this.mobile.set(mobile);
		this.gender.set(gender);
		this.address.set(address);
	}

	public Member() {
	}

	public int getMemberId() {
		return memberId.get();
	}

	public String getName() {
		return name.get();
	}

	public String getEmail() {
		return email.get();
	}

	public long getMobile() {
		return mobile.get();
	}

	public String getGender() {
		return gender.get();
	}

	public String getAddress() {
		return address.get();
	}

	public void setMemberId(int memberId) {
		this.memberId.set(memberId);
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public void setMobile(long l) {
		this.mobile.set(l);
	}

	public void setGender(String gender) {
		this.gender.set(gender);
	}

	public void setAddress(String address) {
		this.address.set(address);
	}
	
	public IntegerProperty memberIdProperty() {
		return memberId;
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	
	public StringProperty emailProperty() {
		return email;
	}
	public LongProperty mobileProperty() {
		return mobile;
	}
	public StringProperty genderProperty() {
		return gender;
	}
	public StringProperty addressProperty() {
		return address;
	}
}
