package com.example.Security.service;

import com.example.Security.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	public void registerUser(User user) {
		System.out.println("Registered user: " + user.getUsername());
	}
}
