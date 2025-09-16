package com.users.Users.model;

import java.time.LocalDateTime;
import java.util.List;

import com.users.Users.enums.UserAssignedRoleStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRole extends Role {    
    private String usercode;        
    private String usernameString;
    private List<String> rolecodes;
    private String country;
    private String state;
    private String city;
    
    private UserAssignedRoleStatus statusString;
    
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
