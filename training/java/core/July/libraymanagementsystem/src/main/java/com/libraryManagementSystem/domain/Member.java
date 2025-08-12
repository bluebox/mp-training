package com.libraryManagementSystem.domain;

import java.util.Objects;

import com.libraryManagementSystem.utilities.MemberGender;

public class Member {
	private int memberId;
	private String name;
	private String email;
	private long mobile;
	private MemberGender gender;
	private String address;
	private String status;

	public Member(int memberId, String name, String email, long mobile, MemberGender gender, String address) {
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

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public long getMobile() {
		return mobile;
	}

	public MemberGender getGender() {
		return gender;
	}

	public String getAddress() {
		return address;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {

		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Member member = (Member) obj;

		return Objects.equals(member.getName(), name) && Objects.equals(member.getEmail(), email)
				&& Objects.equals(member.getMobile(), mobile) && Objects.equals(member.getGender(), gender)
				&& Objects.equals(member.getAddress(), address) && Objects.equals(member.getStatus(), status);

	}

}
