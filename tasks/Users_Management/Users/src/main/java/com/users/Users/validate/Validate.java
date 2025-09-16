package com.users.Users.validate;

import java.util.regex.Pattern;

import com.users.Users.model.MainUser;
import com.users.Users.model.UserRequest;
import com.users.Users.model.UserRole;

public class Validate {
	
	public void validateMainUser(MainUser user) throws Exception {
	if(user.getUsername() == "" || user.getUsername().isBlank()){
		throw new Exception("Username cannot be empty");
	}
	
	if(user.getUsername().length()>15 || user.getUsername().length()<5){
		throw new Exception("Username must be atleast 5 and atmost 15 chars only");
	}
	
	if(user.getFirstName() == "" || user.getFirstName().isBlank()) {
		throw new Exception("First name cannot be empty");
	}
	
	Pattern regexPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
	
    if (!regexPattern.matcher(user.getEmail()).matches()) {
    	throw new Exception("Invalid email");
    }
	
	if(user.getLastName() == "" || user.getLastName().isBlank()) {
		throw new Exception("Last name cannot be empty");
	}
	
	if(user.getPostalCode() == "" || user.getPostalCode().isBlank()) {
		throw new Exception("Postal code cannot be empty");
	}
	
}
	
	public void validateUserRequest(UserRequest user) throws Exception {
		if (user.getUsername() == "" || user.getUsername().isBlank()) {
			throw new Exception("Username cannot be empty");
		}

		if (user.getUsername().length() > 15 || user.getUsername().length() < 5) {
			throw new Exception("Username must be atleast 5 and atmost 15 chars only");
		}

		if (user.getFirstName() == "" || user.getFirstName().isBlank()) {
			throw new Exception("First name cannot be empty");
		}
		
		Pattern regexPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
		
        if (!regexPattern.matcher(user.getEmail()).matches()) {
        	throw new Exception("Invalid email");
        }

		if (user.getLastName() == "" || user.getLastName().isBlank()) {
			throw new Exception("Last name cannot be empty");
		}

		if (user.getPostalCode() == "" || user.getPostalCode().isBlank()) {
			throw new Exception("Postal code cannot be empty");
		}
	}
	
	
	public void validateEmail(String emailString) throws Exception {
		Pattern regexPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
		
        if (!regexPattern.matcher(emailString).matches()) {
        	throw new Exception("Invalid email");
        }
	}
	
	public void validateUserRole(UserRole userRole) throws Exception {
		
		if(userRole.getUsercode() == "" || userRole.getUsercode().isBlank()) {
			throw new Exception("UserCode cannot be empty");
		}
		
		if(userRole.getRolecodes().isEmpty()) {
			throw new Exception("Assign roles to the user");
		}
		
		if(userRole.getCountry()=="" || userRole.getCountry().isBlank()) {
			throw new Exception("Role Assigned country cannot be empty");
		}
		
		if(userRole.getState()=="" || userRole.getState().isBlank()) {
			throw new Exception("Role Assigned state cannot be empty");
		}
		if(userRole.getCity()=="" || userRole.getCity().isBlank()) {
			throw new Exception("Role Assigned city cannot be empty");
		}		
	}

}
