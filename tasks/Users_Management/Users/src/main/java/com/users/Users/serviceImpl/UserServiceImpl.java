package com.users.Users.serviceImpl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.Users.model.UserRequest;
import com.users.Users.repository.interfaces.UserRepository;
import com.users.Users.service.interfaces.UserService;
import com.users.Users.validate.Validate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private Validate validate = new Validate();

	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository=userRepository;
	}

	public List<UserRequest> getAllUserRequests(){
		return userRepository.getAllUserRequests();
	}
	
	public List<UserRequest> getUserRequestById(int requestId) throws Exception{
		
		if(requestId < 0 ) {
			throw new Exception("RequestId cannot not be negative");
		}
		
		List<UserRequest> users = userRepository.getUserRequestById(requestId);
		
		if (users == null || users.isEmpty()) {
			throw new Exception("User Not Found");
		}
		return users;

	}

	public void addUserRequest(@NotNull @Valid UserRequest user) throws Exception {
		
		validate.validateUserRequest(user);
		user.setCreated_at(LocalDateTime.now());
				
		try {
			userRepository.addUserRequest(user);
		} catch (Exception e) {
			if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
				throw new Exception("User Already Found");
			} else {
				e.printStackTrace();
				throw new Exception("Error adding user");
			}
		}
	}

	public boolean updateUserRequest(UserRequest user) throws Exception {
		
		validate.validateUserRequest(user);
		
		boolean res = userRepository.updateUserRequest(user);			
		
		if(res) {
			return res;
		}else {
			throw new Exception("No updates done to UserRequest");	
		} 
	}

	@Override
	public boolean updateUserRequestStatus(int requestId, String newStatus) throws Exception {
		if(requestId <0 ) {
			throw new Exception("RequestId cannot not be negative");
		}
		if(newStatus == "" || newStatus.isBlank()) {
			throw new Exception("status cannot not be empty");
		}
		
		boolean res = userRepository.updateUserRequestStatus(requestId, newStatus);
		
		if(res) {
			return res;
		}else {
			throw new Exception("Failed to update user request status");	
		} 
	}
	
	
}
