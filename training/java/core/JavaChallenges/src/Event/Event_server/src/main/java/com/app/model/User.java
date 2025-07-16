package com.app.model;
import com.app.enums.Gender;
import com.app.enums.UserStatus;
import lombok.Data;

@Data
public class User {
    private int userId;
    private String name;
    private String phnNumber;
    private String email;
    private String role;
    private Gender gender;   
    private UserStatus status;  
    private String dept;
	
}
