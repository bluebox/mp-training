package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.model.Credentials;
import com.app.repository.CredentialsRepository;

@Service
public class CredentialsService 
{
	@Autowired
	private final CredentialsRepository cred;
	
	@Autowired
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public CredentialsService(CredentialsRepository cred,BCryptPasswordEncoder bCryptPasswordEncoder,BCryptPasswordEncoder  bCryptPasswordEncoder1)
	{
		this.cred=cred;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder1;
	}
	
	   public void register( Credentials c) {

	        c.setPassword(bCryptPasswordEncoder.encode(c.getPassword()));
	        cred.addCredentials(c);
	     
	    }
	
	public boolean authenticate(String username, String password) {
        Credentials user = cred.getCredentialsByUsername(username);

       
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw  new BadCredentialsException("The password is incorrect");
        }
        return  true;
    }

}
