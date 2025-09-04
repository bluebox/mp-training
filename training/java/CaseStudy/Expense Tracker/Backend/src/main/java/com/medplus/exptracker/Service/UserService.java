package com.medplus.exptracker.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.medplus.exptracker.Dao.UserDAO;
import com.medplus.exptracker.DTO.UserDTO;
import com.medplus.exptracker.Model.User;

@Service
public class UserService implements UserDetailsService {
    
    
    @Autowired
    private UserDAO userDAO;
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = userDAO.findByUsername(username)
        		.orElseThrow(() ->{ System.out.println("Could'nt find user");throw new UsernameNotFoundException("User not found");} );//new UsernameNotFoundException("User not found"));
        
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .roles(getRoleByRoleId(user.getRole_id()))
            .build();
    }
    
   
    
    private String getRoleByRoleId(Integer roleId) {
        switch (roleId) {
            case 1: return "ADMIN";
            case 2: return "MANAGER";
            default: return "EMPLOYEE";
        }
    }
    
    //Use DTO to remove password and send it 
    public User getUserByUserName(String username) throws UsernameNotFoundException{
    	User user = userDAO.findByUsername(username)
    	            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    	return user;
    }
    
    
    public User getUserById(Integer userId) {
        Optional<User> userOpt = userDAO.getUserById(userId);
        return userOpt.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }
    
    public void addUser(User user) {
    	userDAO.addUser(user);
    }
    
    public List<UserDTO> getEmployeesByManagerId(Integer managerId) {
        List<User> users = userDAO.getUsersByManagerId(managerId);
        return users.stream().map(user -> {
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setRoleId(user.getRole_id());
            dto.setManagerId(user.getManager_id());
            return dto;
        }).collect(Collectors.toList());
    }
}