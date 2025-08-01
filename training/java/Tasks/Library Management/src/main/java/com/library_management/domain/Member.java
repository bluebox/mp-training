package com.library_management.domain;

import java.util.Objects;

public class Member {
	private int memberId;
	private String memberName;
	private String memberMail;
	private String mobileNo;
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	private String gender;
	private String memberAddress;
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberMail() {
		return memberMail;
	}
	public void setMemberMail(String memberMail) {
		this.memberMail = memberMail;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	
	
	public Member() {
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberMail = memberMail;
		this.gender = gender;
		this.memberAddress = memberAddress;
	}
	public Member(int memberId, String memberName, String memberMail, String mobileNo, String gender,
			String memberAddress) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberMail = memberMail;
		this.mobileNo = mobileNo;
		this.gender = gender;
		this.memberAddress = memberAddress;
	}
	
	
	public Member(String memberName, String memberMail, String mobileNo, String gender, String memberAddress) {
		super();
		this.memberName = memberName;
		this.memberMail = memberMail;
		this.mobileNo = mobileNo;
		this.gender = gender;
		this.memberAddress = memberAddress;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberName=" + memberName + ", memberMail=" + memberMail
				+ ", mobileNo=" + mobileNo + ", gender=" + gender + ", memberAddress=" + memberAddress + "]";
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
		return Objects.equals(gender, other.gender) && Objects.equals(memberAddress, other.memberAddress)
				&& memberId == other.memberId && Objects.equals(memberMail, other.memberMail)
				&& Objects.equals(memberName, other.memberName) && Objects.equals(mobileNo, other.mobileNo);
	}
	
	
	

}
