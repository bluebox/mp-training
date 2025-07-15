package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.app.model.Credentials;
import com.app.repository.CredentialsRepository;

@Service
public class MyCredentialsService implements UserDetailsService {
    @Autowired
    private CredentialsRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials cred = repository.getCredentialsByUsername(username);
        if (cred == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CredPrincipal(cred);
    }
}
