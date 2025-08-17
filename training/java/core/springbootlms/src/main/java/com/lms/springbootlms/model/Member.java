package com.lms.springbootlms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private int memberId;
    private String name;
    private String email;
    private String mobile;
    private String gender;
    private String address;
}
