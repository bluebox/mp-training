package com.app.model;
import com.app.enums.Gender;
import com.app.enums.Status;
import lombok.Data;

@Data
public class User {
    private int userId;
    private String name;
    private String phnNumber;
    private String email;
    private String role;
    private Gender gender;   
    private Status status;  
    private String dept;
	
}
