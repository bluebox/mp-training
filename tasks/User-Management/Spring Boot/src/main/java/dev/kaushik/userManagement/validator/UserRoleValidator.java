package dev.kaushik.userManagement.validator;

import java.util.regex.Pattern;

import dev.kaushik.userManagement.exception.UserException;
import dev.kaushik.userManagement.model.UserRole;

public class UserRoleValidator {
	public static final Pattern nameRegEx = Pattern.compile("^[A-Za-z. ]+$");

	public static void validate(UserRole userRole) {
		if (userRole == null) {
			throw new UserException("UserRole cant be null");
		}
		if (userRole.getCountry() == null || userRole.getState() == null || userRole.getCity() == null) {
			throw new UserException("Country/state/city cannot be null");
		}
		
		int countryLength = userRole.getCountry().length();
		if (countryLength < 2 || countryLength > 100) {
			throw new UserException("Country length must be between 2 to 100");
		}
		if (!nameRegEx.matcher(userRole.getCountry()).matches()) {
			throw new UserException("Country can contain only alphabets and spaces");
		}
		
		int stateLength = userRole.getState().length();
		if (stateLength < 2 || stateLength > 100) {
			throw new UserException("State length must be between 2 to 100");
		}
		if (!nameRegEx.matcher(userRole.getState()).matches()) {
			throw new UserException("State can contain only alphabets and spaces");
		}
		
		int cityLength = userRole.getCity().length();
		if (cityLength < 2 || cityLength > 100) {
			throw new UserException("City length must be between 2 to 100");
		}
		if (!nameRegEx.matcher(userRole.getCity()).matches()) {
			throw new UserException("State can contain only alphabets and spaces");
		}
	}
}
