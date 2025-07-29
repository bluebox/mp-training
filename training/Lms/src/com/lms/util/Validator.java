package com.lms.util;

import java.time.LocalDate;

import com.lms.exceptions.InvalidInputException;
import com.lms.model.BookCategory;

public class Validator {

    public static void validateName(String name) throws InvalidInputException {
        if (name == null || !name.matches("[A-Za-z ]{2,50}")) {
            throw new InvalidInputException("Invalid name: Only letters and spaces allowed (2–50 characters).");
        }
    }

    public static void validateMobileNumber(String mobile) throws InvalidInputException {
        if (mobile == null || !mobile.matches("\\d{10}")) {
            throw new InvalidInputException("Invalid mobile number: Must be exactly 10 digits.");
        }
    }

    public static void validateEmail(String email) throws InvalidInputException {
        if (email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidInputException("Invalid email format.");
        }
    }

    public static void validateAddress(String address) throws InvalidInputException {
        if (address == null || address.trim().isEmpty()) {
            throw new InvalidInputException("Address cannot be empty.");
        }
    }

    public static void validateBookName(String bookName) throws InvalidInputException {
        if (bookName == null || bookName.trim().isEmpty()) {
            throw new InvalidInputException("Book name cannot be empty.");
        }
    }

    public static void validateAuthorName(String author) throws InvalidInputException {
        if (author == null || !author.matches("[A-Za-z ]{2,50}")) {
            throw new InvalidInputException("Invalid author name: Only letters and spaces allowed (2–50 characters).");
        }
    }
	public static void validateGender(String selectedGender) throws InvalidInputException {
		if (selectedGender == null ){
			throw new InvalidInputException("Gender cannot be empty.");
	}
	}


    public static void serviceValidateName(String name) throws InvalidInputException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Name cannot be empty.");
        }
    }

    public static void serviceValidateMobile(String mobile) throws InvalidInputException {
        if (mobile == null || mobile.trim().isEmpty()) {
            throw new InvalidInputException("Mobile number cannot be empty.");
        }
    }

    public static void serviceValidateEmail(String email) throws InvalidInputException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidInputException("Email cannot be empty.");
        }
    }

    public static void serviceValidateAddress(String address) throws InvalidInputException {
        if (address == null || address.trim().isEmpty()) {
            throw new InvalidInputException("Address cannot be empty.");
        }
         
    }

    public static void serviceValidateBookName(String bookName) throws InvalidInputException {
        if (bookName == null || bookName.trim().isEmpty()) {
            throw new InvalidInputException("Book name cannot be empty.");
        }
    }

    public static void serviceValidateAuthor(String author) throws InvalidInputException {
        if (author == null || author.trim().isEmpty()) {
            throw new InvalidInputException("Author name cannot be empty.");
        }
    }

    public static void serviceValidateCategory(String category) throws InvalidInputException {
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidInputException("Category cannot be empty.");
        }
    }

    public static void serviceValidateMemberId(String memberId) throws InvalidInputException {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new InvalidInputException("Member ID cannot be empty.");
        }
    }

    public static void serviceValidateBookId(String bookId) throws InvalidInputException {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new InvalidInputException("Book ID cannot be empty.");
        }
    }
	public static void serviceValidateGender(String selectedGender) throws InvalidInputException {
		if (selectedGender == null ){
			throw new InvalidInputException("Gender cannot be empty.");
	}
	}
	public static boolean isEmptyBookId(String bookId) {
		if (bookId == null || bookId.trim().isEmpty()) {
			return true;
		}
		return false;
	}
 	 public static void isEmptyMobile(String mobile) throws InvalidInputException{

			if (mobile == null || mobile.trim().isEmpty()) {
				throw new InvalidInputException("Please Enter Mobile number.");
			}
		}

	 public static boolean isTitleDateEmpty(String selectedBookTitle, LocalDate dueDate) {
		 if(selectedBookTitle==null || dueDate==null) {
		 return true;
		 }
		 return false;
	 }

	 public static void validateBookCategory(BookCategory category) throws InvalidInputException {
		if (category == null) {
			throw new InvalidInputException("Please select Book category.");
		}
	 }
 

}
