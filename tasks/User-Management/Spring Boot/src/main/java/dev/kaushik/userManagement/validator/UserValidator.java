package dev.kaushik.userManagement.validator;

import java.util.regex.Pattern;

import dev.kaushik.userManagement.exception.UserException;
import dev.kaushik.userManagement.model.User;

public class UserValidator {
	public static final Pattern nameRegEx = Pattern.compile("^[A-Za-z. ]+$");
	public static final Pattern emailregEx = Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]{2,}$");;

	public static void validate(User user) {
		if (user == null) {
			throw new UserException("User cant be null");
		}
		int firstNameLength = user.getFirstName().length();
		if (firstNameLength < 2 || firstNameLength > 100) {
			throw new UserException("First Name must be between 2 and 100 characters");
		}

		if (!nameRegEx.matcher(user.getFirstName()).matches()) {
			throw new UserException("First Name can contain only alphabets and spaces");
		}

		int lastNameLength = user.getLastName().length();
		if (!(lastNameLength >= 2) || !(lastNameLength <= 100)) {
			throw new UserException("Last Name must be between 2 and 100 characters");
		}

		if (!nameRegEx.matcher(user.getLastName()).matches()) {
			throw new UserException("Last Name can contain only alphabets and spaces");
		}

		if (!emailregEx.matcher(user.getEmail()).matches()) {
			throw new UserException("Email should be in valid format");
		}

		long phoneNumber = user.getPhoneNumber();
		if (phoneNumber < 1000000000L || phoneNumber > 9999999999L) {
			throw new UserException("Phone number must be only 10 digits");
		}

		int pinCode = user.getPinCode();
		if (pinCode < 100000 || pinCode > 999999) {
			throw new UserException("Pin code must be only 6 digits");
		}
		
		if (user.getCountry() == null || user.getState() == null || user.getCity() == null) {
			throw new UserException("Country/state/city cannot be null");
		}
		
		int countryLength = user.getCountry().length();
		if (countryLength < 2 || countryLength > 100) {
			throw new UserException("Country length must be between 2 to 100");
		}
		if (!nameRegEx.matcher(user.getCountry()).matches()) {
			throw new UserException("Country can contain only alphabets and spaces");
		}
		
		int stateLength = user.getState().length();
		if (stateLength < 2 || stateLength > 100) {
			throw new UserException("State length must be between 2 to 100");
		}
		if (!nameRegEx.matcher(user.getState()).matches()) {
			throw new UserException("State can contain only alphabets and spaces");
		}
		
		int cityLength = user.getCity().length();
		if (cityLength < 2 || cityLength > 100) {
			throw new UserException("City length must be between 2 to 100");
		}
		if (!nameRegEx.matcher(user.getCity()).matches()) {
			throw new UserException("City can contain only alphabets and spaces");
		}
	}

}
