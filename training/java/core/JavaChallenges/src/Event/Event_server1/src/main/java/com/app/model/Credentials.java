package com.app.model;

import lombok.Data;

@Data
public class Credentials {
    private int user_id;
    private String userName;   
    private String password;
    private String role;      
	
}
