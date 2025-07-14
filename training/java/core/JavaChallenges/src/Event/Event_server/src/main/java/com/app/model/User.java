package com.app.model;
import com.app.enums.Gender;
import com.app.enums.Status;
import lombok.Data;

@Data
public class User {
    private int user_id;
    private String name;
    private String phn_number;
    private String email;
    private String role;
    private Gender gender;   // Enum: MALE, FEMALE, OTHER
    private Status status;   // Enum: ACTIVE, INACTIVE
    private String dept;
	
}
