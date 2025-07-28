package com.app.security;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.app.model.Credentials;

public class CredPrincipal implements UserDetails {
    private final Credentials cred;

    public CredPrincipal(Credentials cred) {
        this.cred = cred;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	return List.of(new SimpleGrantedAuthority("ROLE_" + cred.getRole().toUpperCase()));

    }

    @Override public String getPassword() { return cred.getPassword(); }
    @Override public String getUsername() { return cred.getUserName(); }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}