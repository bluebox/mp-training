package com.medplus.model;
public class Member {
    private int memberId;
    private String name;
    private String email;
    private int mobile;
    private char gender;
    private String address;

    public Member(String name, String email, int mobile, char gender, String address) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.address = address;
    }

    public Member(int memberId, String name, String email, int mobile, char gender, String address) {
        this(name, email, mobile, gender, address);
        this.memberId = memberId;
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

    public int getMobile() {
        return mobile;
    }

    public char getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member{" +
               "memberId=" + memberId +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", mobile=" + mobile +
               ", gender=" + gender +
               ", address='" + address + '\'' +
               '}';
    }
}