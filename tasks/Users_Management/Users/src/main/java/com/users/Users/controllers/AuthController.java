package com.users.Users.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.users.Users.model.MainUser;
import com.users.Users.service.interfaces.MainUserService;
import com.users.Users.service.interfaces.RoleService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  
    @Autowired
    private MainUserService mainUserService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;  
    

    public AuthController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }    
    
    @PostMapping("/change-password")
    public Map<String, Object> changePassword(@RequestBody Map<String, String> body) throws Exception {
        String userCode = body.get("username");   
        String currentPassword = body.get("currentPassword");
        String newPassword = body.get("newPassword");
        
        if (userCode == null || userCode.trim().isEmpty()) {
            throw new RuntimeException("UserId is required");
        }
        
        if (currentPassword == null || newPassword == null || newPassword.trim().isEmpty()) {
            throw new RuntimeException("Current and new passwords are required");
        }

        MainUser user = mainUserService.getMainUserById(userCode);
        if (user == null) {
            throw new RuntimeException("Invalid UserId");
        }
        
        if(user.getPassword().startsWith("$2a$")) {
        	if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
        		throw new RuntimeException("Current password is incorrect");
        	}
        	
        }else {
        	if (!currentPassword.equals(user.getPassword())) {
                throw new RuntimeException("Current password is incorrect");
        	}
        }

        String encodedpassword = passwordEncoder.encode(newPassword);

        boolean updated = mainUserService.updatePassword(userCode, encodedpassword);
        if (!updated) {
            throw new RuntimeException("Failed to update password");
        }

        Map<String, Object> res = new HashMap<>();
        res.put("message", "Password updated");
        return res;
    }
    
    
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Map<String,String> body) throws Exception {
        String userCode = body.get("username");
        String rawPassword = body.get("password");

        MainUser user = mainUserService.getMainUserById(userCode);
        if (user == null) throw new RuntimeException("Invalid credentials");

        if(!user.getPassword().startsWith("$2a$")) {
        	if (!rawPassword.matches(user.getPassword())) {
                throw new RuntimeException("Password is incorrect");
        	}
        }else {
        	if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
        		throw new RuntimeException("Password is incorrect");
        	}
        }

        List<String> roleNames = roleService.getRoleName(user.getUserCode());
        
        
        if (roleNames == null) {
        	roleNames = List.of();        	
        }

        List<SimpleGrantedAuthority> authorities = roleNames.stream()
                .filter(r -> r != null && !r.isBlank())
                .map(r -> r.toUpperCase())
                .map(r -> "ROLE_" + r)
                .map(SimpleGrantedAuthority::new)
                .toList();

        if (authorities.isEmpty()) {
            authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUserCode())
                .password(user.getPassword())
                .authorities(authorities)
                .build();

        if (inMemoryUserDetailsManager.userExists(userDetails.getUsername())) {
            inMemoryUserDetailsManager.updateUser(userDetails);
        } else {
            inMemoryUserDetailsManager.createUser(userDetails);
        }

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);


        
        Map<String,Object> res = new HashMap<>();
        
        res.put("username", user.getUsername());
        res.put("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
        res.put("password", user.getPassword());
        return res;
    }
    
 
}
