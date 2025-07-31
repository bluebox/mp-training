package util;

import exceptions.ManagementException;

public class ValidationUtil {

    public static void validateBookTitle(String title) throws ManagementException {
        if(title == null || title.trim().isEmpty()){
            throw new ManagementException("Book title cannot be empty.");
        }
        if(title.length() > 35){
            throw new ManagementException("Book title cannot exceed 35 characters.");
        }
    }

    public static void validateNotEmpty(String field, String value) throws ManagementException{
        if (value == null || value.trim().isEmpty()) {
            throw new ManagementException(field + " cannot be empty.");
        }
    }
    public static void validateEmail(String email) throws ManagementException {
        if (email == null || email.trim().isEmpty()) {
            throw new ManagementException("Email cannot be empty.");
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
        	 throw new IllegalArgumentException("Invalid email format.");
        }
    }
    public static void validatePhone(String phone) throws ManagementException {
        if (phone == null || phone.trim().isEmpty()) {
            throw new ManagementException("Phone number cannot be empty.");
        }
        if (!phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
        }
        }
}
