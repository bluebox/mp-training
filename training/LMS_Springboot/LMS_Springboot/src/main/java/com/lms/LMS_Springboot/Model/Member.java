package com.lms.LMS_Springboot.Model;

import com.lms.LMS_Springboot.Model.checking_enum.Gender;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private int memberId;
    private String name;
    private String email;
    private String mobile;
    private String address;
    private Gender gender;   // Only gender enum used
}
