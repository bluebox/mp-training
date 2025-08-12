package com.example.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.model.User;
import com.example.spring.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	 public void saveUsers(User user){	          	
	      userRepository.saveUsers(user);
	 }
	 
	 public List<User> findUsers(){
	        List<User> userdata = userRepository.findUsers();
	        return userdata;
	  }


}
