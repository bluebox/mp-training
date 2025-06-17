package com.library.util;

import com.library.domain.Member;

public class MemberValidator {

	public void validator(Member member)throws ValidationException
	{
		if (member.getName() == null || member.getName().trim().isEmpty()) {
		    throw new ValidationException("Name cannot be empty.");
		}
		
		if (member.getEmail() == null || !member.getEmail().matches("^\\S+@\\S+\\.\\S+$")) {
		    throw new ValidationException("Invalid email address.");
		}
		
		if (String.valueOf(member.getMobile()).length() != 10) {
		    throw new ValidationException("Mobile number must be 10 digits.");
		}
		
		String gender = String.valueOf(member.getGender()).toUpperCase();
		if (!gender.equals("M") && !gender.equals("F")) {
		    throw new ValidationException("Gender must be 'M' or 'F'.");
		}
		
		if (member.getAddress() == null || member.getAddress().trim().isEmpty()) {
		    throw new ValidationException("Address cannot be empty.");
		}

	}
}
