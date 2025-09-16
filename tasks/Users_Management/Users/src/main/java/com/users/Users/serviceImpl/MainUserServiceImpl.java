package com.users.Users.serviceImpl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.Users.model.MainUser;
import com.users.Users.model.UserRequest;
import com.users.Users.repository.interfaces.MainUserRepository;
import com.users.Users.service.interfaces.MainUserService;
import com.users.Users.validate.Validate;

@Service
public class MainUserServiceImpl implements MainUserService{
	
	private MainUserRepository mainUserRepository;
	private Validate validate = new Validate();
	
	@Autowired
	public MainUserServiceImpl(MainUserRepository mainUserRepository) {
		this.mainUserRepository=mainUserRepository;
	}

	@Override
	public boolean addUserToMain(UserRequest user) throws Exception {
		validate.validateUserRequest(user);
				
		try {
			return mainUserRepository.addUserToMain(user);
		} catch (Exception e) {
			if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
				throw new Exception("User Already Found");
			} else {
				e.printStackTrace();
				throw new Exception("Error adding user");
			}
		}
	}

	@Override
	public List<MainUser> getMainUsers() {
		return mainUserRepository.getMainUsers();
	}
	

	@Override
	public boolean updatePassword(String userCode, String encodedPassword) throws Exception {
		if(userCode == "" || userCode.isBlank()) {
			throw new Exception("usercode cannot not be empty");
		}
		
		if(encodedPassword == "" || encodedPassword.isBlank()) {
			throw new Exception("encodedPassword cannot not be empty");
		}
		
	    boolean res = mainUserRepository.updatePassword(userCode, encodedPassword);
	    
	    if(res) {
			return res;
		} else {
			throw new Exception("Failed to update password for : "+ userCode);
		}
	}

	@Override
	public MainUser getMainUserByEmail(String email) throws Exception {
		
		validate.validateEmail(email);
		
		MainUser mainUser = mainUserRepository.getMainUserByEmail(email);
		
		if( mainUser !=null) {
			return mainUser;
		} else {
			throw new Exception("No user found with email: "+ email);
		}
	}

	@Override
	public MainUser getMainUserById(String usercode) throws Exception {
	
		if(usercode == "" || usercode.isBlank()) {
			throw new Exception("usercode cannot not be empty");
		}
		
		MainUser mainUser = mainUserRepository.getMainUserById(usercode);
		
		if( mainUser !=null) {
			return mainUser;
		} else {
			throw new Exception("No user found with usercode: "+ usercode);
		}
	}


}
