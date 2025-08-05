package com.library.dto;

public class MemberRow {
    private int memberId;
    private String name;
    private String email;

    public MemberRow(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    public int getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}