package com.LibraryManagement.utilites.pojos;

public class Member {
   private Integer memberId;
   private String name;
   private String email;
   private Long mobile;
   private Character gender;
   
   public Member(Integer memberId, String name, String email, Long mobile, Character gender){
	   this.memberId=memberId;
	   this.name=name;
	   this.email=email;
	   this.mobile=mobile;
	   this.gender=gender;
   }
   public Member(String name, String email, long mobile, Character gender){
	   this.name=name;
	   this.email=email;
	   this.mobile=mobile;
	   this.gender=gender;
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
   public Long getMobile() {
	return mobile;
   }
   public void setMobile(long mobile) {
	this.mobile = mobile;
   }
   public Character getGender() {
	return gender;
   }
   public void setGender(Character gender) {
	this.gender = gender;
   }
   public String toString() {
	   return memberId+" "+name+" "+email+" "+mobile+" "+gender;
   }
}