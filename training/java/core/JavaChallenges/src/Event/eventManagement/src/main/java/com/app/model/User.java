package com.app.model;

import com.app.enums.Gender;
import com.app.enums.Status;

import lombok.Data;

@Data
public class User {
	
	int user_id;
	String name;
	String phn_number;
	String email;
	String role;
	Gender gender;
	Status status;
	String dept;


}
