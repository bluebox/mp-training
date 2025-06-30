package com.library.domain;

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
    private long mobile;
    private Gender gender;
    private String address;

    
}
