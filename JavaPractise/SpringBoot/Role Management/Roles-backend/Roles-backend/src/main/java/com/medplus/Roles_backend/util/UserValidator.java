package com.medplus.Roles_backend.util;

import java.util.regex.Pattern;

import com.medplus.Roles_backend.exception.UserValidationException;

public class UserValidator {

	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

	private static final Pattern MOBILE_PATTERN = Pattern.compile("^[0-9]{10}$");

	private static final int MAX_NAME_LENGTH = 25;

	private static void validateRequiredString(String value, String fieldName) {
		if (value == null || value.trim().isEmpty()) {
			throw new UserValidationException(fieldName + " cannot be empty");
		}
	}

	private static void validateMaxLength(String value, String fieldName, int maxLength) {
		if (value != null && value.length() > maxLength) {
			throw new UserValidationException(fieldName + " cannot exceed " + maxLength + " characters");
		}
	}

	public static void validateFirstName(String firstName) {
		validateRequiredString(firstName, "First name");
		validateMaxLength(firstName, "First name", MAX_NAME_LENGTH);
	}

	public static void validateLastName(String lastName) {
		validateRequiredString(lastName, "Last name");
		validateMaxLength(lastName, "Last name", MAX_NAME_LENGTH);
	}

	public static void validateUsername(String username) {
		validateRequiredString(username, "Username");
		validateMaxLength(username, "Username", MAX_NAME_LENGTH);
	}

	public static void validateAge(Integer age) {
		if (age == null || age < 18) {
			throw new UserValidationException("Age must be at least 18");
		}
		if(age>100) {
			throw new UserValidationException("Age must be atmost 100");
		}
	}

	public static void validateEmail(String email) {
		validateRequiredString(email, "Email");
		if (!EMAIL_PATTERN.matcher(email).matches()) {
			throw new UserValidationException("Invalid email format");
		}
	}

	public static void validateMobile(String mobile) {
		validateRequiredString(mobile, "Mobile number");
		if (!MOBILE_PATTERN.matcher(mobile).matches()) {
			throw new UserValidationException("Mobile number must be 10 digits");
		}
	}

	public static void validateState(String state) {
		validateRequiredString(state, "State");
	}

	public static void validateCity(String city) {
		validateRequiredString(city, "City");
	}

	public static void validateGender(Object gender) {
		if (gender == null) {
			throw new UserValidationException("Gender must be provided");
		}
	}

	public static void validateReqId(Long reqId) {
		if (reqId == null || reqId <= 0) {
			throw new UserValidationException("Request Id must be a positive number");
		}
	}

}
