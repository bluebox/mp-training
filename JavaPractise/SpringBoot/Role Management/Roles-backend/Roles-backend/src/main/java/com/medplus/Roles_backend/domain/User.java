package com.medplus.Roles_backend.domain;

import java.util.List;

import lombok.Data;

@Data
public class User {
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private List<String> roles;
}
