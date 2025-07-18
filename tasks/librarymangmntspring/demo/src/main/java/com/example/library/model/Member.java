package com.example.library.model;

import java.util.Objects;

public class Member{
    private Integer memberId;
    private String name;
    private String email;
    private String address;
    private Long mobile;
    private Character gender;

    
    public Member(Integer memberId, String name, String email, Long mobile, Character gender, String address) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
        this.gender = gender;
    }


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(gender, other.gender) && Objects.equals(memberId, other.memberId)
				&& Objects.equals(mobile, other.mobile) && Objects.equals(name, other.name);
	}

	public Member() {
        // no-arg constructor for Spring/Thymeleaf
    }

	public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", mobile=" + mobile +
                ", gender=" + gender +
                '}';
    }
}

