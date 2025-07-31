package domain;

import java.time.LocalDateTime;

public class Member {
	private int memberId;
	private String name;
	private String email;
	private String mobile;
	private Gender gender;

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	private String address;
	private String AddedBy;
	private LocalDateTime dateAdded;

	public Member() {

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
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

	public int getMemberId() {
		return memberId;
	}

	public void setId(int memberId) {
		this.memberId = memberId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(LocalDateTime dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getAddedBy() {
		return AddedBy;
	}

	public void setAddedBy(String addedBy) {
		AddedBy = addedBy;
	}

}