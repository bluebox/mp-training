package com.example.lms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long memberId;  
    private String name;   
    private String email; 
    private String mobile;  
    private String gender;  
    private String address;  
}
