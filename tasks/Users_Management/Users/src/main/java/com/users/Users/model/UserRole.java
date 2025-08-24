package com.users.Users.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    
    private String username;        // stores user_code
    private String usernameString;
    private List<String> roleId;
    private String roleName;       
    private String country;
    private String state;
    private String city;

}
