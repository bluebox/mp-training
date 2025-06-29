package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Member {
	private int memberId;
    private String name;
    private String email;
    private long mobile;
    private char gender;
    private String address;
}
