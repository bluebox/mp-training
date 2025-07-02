package com.example.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MemberLog {
	private int memberId;
    private String name;
    private String email;
    private long mobile;
    private char gender;
    private String address;
    private LocalDateTime time;
}
