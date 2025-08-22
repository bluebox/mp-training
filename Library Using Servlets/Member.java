package com.library.model;

public class Member {
    private int memberId;
    private String name;
    private String email;
    private long mobile;
    private String gender;
    private String address;

    public Member() {
    }

    public Member(int memberId, String name, String email, long mobile, String gender, String address) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.address = address;
    }

    public Member(String name, String email, long mobile, String gender, String address) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
