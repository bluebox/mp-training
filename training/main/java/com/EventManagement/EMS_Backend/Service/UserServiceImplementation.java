package com.EventManagement.EMS_Backend.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.EventManagement.EMS_Backend.Model.CustomUserDetails;
import com.EventManagement.EMS_Backend.Model.ModelUser;

@Service
public class UserServiceImplementation implements UserDetailsService {

    @Autowired
    private RegistrationService userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordencoder;
        
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ModelUser user = userRepository.findbyemail(username);
        System.out.println(user); 
        if(user==null) {
            throw new UsernameNotFoundException("User not found with this email"+username);

        }
        System.out.println("Loaded user: " + user.getEmail() + ", Role: ");
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getUsertype().getType()));

//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                
//                user.getPassword(),
//                authorities);
        return new CustomUserDetails(
        		user.getEmail(),
             user.getPassword(),
             authorities,
             user.getUserId()
            );

    }
}