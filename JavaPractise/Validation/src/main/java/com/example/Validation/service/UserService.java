package com.example.Validation.service;

import com.example.Validation.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	public void registerUser(User user) {
		System.out.println("Registered user: " + user.getUsername());
	}
}
