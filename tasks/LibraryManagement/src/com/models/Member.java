package com.models;

public class Member {
    private int memberId;
    private String name;
    private String email;
    private long mobile;
    private char gender;
    private String address;

    public Member(int memberId, String name, String email, long mobile, char gender, String address) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.address = address;
    }

    public int getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public long getMobile() { return mobile; }
    public char getGender() { return gender; }
    public String getAddress() { return address; }
}
