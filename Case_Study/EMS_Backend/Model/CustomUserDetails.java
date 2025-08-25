package com.EventManagement.EMS_Backend.Model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {
    private String userid;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                             String userid) {
        super(username, password, authorities);
        this.userid = userid;
    }

    public String userid() { return userid; }
}
