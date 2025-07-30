package com.lms.util;

import java.sql.SQLException;
import java.time.LocalDate;

import com.lms.daoImpl.BookDao;
import com.lms.exceptions.InvalidInputException;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.model.Member;
import com.lms.serviceImpl.IssueBookServiceImpl;
import com.lms.service.ReturnBookServiceInterface;

public class Validator {

    public static void validateMobileNumber(String mobile) throws InvalidInputException {
        if (mobile == null || !mobile.matches("\\d{10}")) {
            throw new InvalidInputException("Invalid mobile number: Must be exactly 10 digits.");
        }
    }

    public static void serviceValidateBookId(String bookId) throws InvalidInputException {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new InvalidInputException("Book ID cannot be empty.");
        }
    }
	
	public static boolean isEmptyBookId(String bookId) {
		if (bookId == null || bookId.trim().isEmpty()) {
			return true;
		}
		return false;
	}
 	 
	
//members
	 public static void validateMember(Member member) throws InvalidInputException {
	        String name = member.getName();
	        String email = member.getEmail();
	        String mobile = member.getMobile();
	        String gender = member.getGender();
	        String address = member.getAddress();
	        
	        String errorMessage = "";
	        if (name == null || !name.matches("[A-Za-z ]{2,50}")) {
	        		errorMessage = "Invalid name: Must be 2-50 characters long and contain only letters and spaces.\n";
	        } else if (email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
	        		errorMessage = "Invalid email format.\n";
	        } else if (mobile == null || !mobile.matches("\\d{10}")) {
	        		errorMessage = "Mobile number must be exactly 10 digits.\n";
	        } else if (gender == null || !(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Other"))) {
	        		errorMessage = "Please select Gender from the dropdown.\n";
	        } else if (address == null || address.trim().isEmpty()) {
	        		errorMessage = "Address cannot be empty.\n";
	        }
	        if (!errorMessage.isEmpty()) {
				throw new InvalidInputException(errorMessage);
			}
	    }
//books
	 public static void validateBook(Book newBook) throws InvalidInputException {
		 String err="";
		 if (newBook == null) {
			 err="Book cannot be null.";
		 }else if (newBook.getBookTitle() == null || newBook.getBookTitle().trim().isEmpty()) {
			 err="Book title cannot be empty.";
		 } else if (newBook.getBookAuthor() == null || 
				 newBook.getBookAuthor().trim().isEmpty() || 
				 newBook.getBookAuthor().length() < 2 || 
				 newBook.getBookAuthor().length() > 50 || 
				 !newBook.getBookAuthor().matches("[A-Za-z ]+")) {
			 if (newBook.getBookAuthor() == null || newBook.getBookAuthor().trim().isEmpty()) {
				 err="Author name cannot be empty.";
			 } else {
				 err="Author name must be between 2 and 50 characters and contain only letters and spaces.";
			 }
			 
		 } else if (newBook.getBookCategory() == null) {
			 err="Please select a book category.";
		 }
		 if (!err.isEmpty()) {
			 throw new InvalidInputException(err);
		 }
	 }

	 public static void serviceValidateBook(Book newBook) throws InvalidInputException {
		 String err="";
		 try {
		 validateBook(newBook);
		 } catch (InvalidInputException e) {
			 err = e.getMessage();
		 }
		 if( newBook.getStatus() != 'A' && newBook.getStatus() != 'I') {
			 err = "Status must be either 'A' (Active) or 'I' (Inactive).";
		 }else if (newBook.getAvailability() != 'A' && newBook.getAvailability() != 'I') {
			 err = "Availability must be either 'A' (Available) or 'U' (Unavailable).";
		 }
		 if (!err.isEmpty()) {
			 throw new InvalidInputException(err);
		 }
	 }
//issues and returns
	 public static String validateAndFetchMemberNameByMobile(String mobile, ReturnBookServiceInterface returnBookService) throws InvalidInputException, SQLException {
	     validateMobileNumber(mobile);
	     String err = "";

	     String memberName = returnBookService.getMemberNameByMobile(mobile);
	     if (memberName == null) {
	         err = "Member not found with the provided mobile number.";
	     } else if (memberName.trim().isEmpty()) {
	         err = "Member name cannot be empty.";
	     }
	     if (!err.isEmpty()) {
	         throw new InvalidInputException(err);
	     }

	     return memberName;
	 }

	 
	 public static Member validateAndFetchMemberByMobile(String mobile, IssueBookServiceImpl issueBookService) throws InvalidInputException, SQLException {
	        if (mobile == null || mobile.isEmpty()) {
	            throw new InvalidInputException("Mobile number is required.");
	        }

	        validateMobileNumber(mobile);

	        Member member = issueBookService.getMemberByMobile(mobile);
	        if (member == null) {
	            throw new InvalidInputException("Member not found.");
	        }

	        return member;
	    }
	 
	 public static void validateReturnBookInputs(String mobile, String bookName, String status) throws InvalidInputException {
	        validateMobileNumber(mobile);

	        if (bookName == null || bookName.trim().isEmpty()) {
	            throw new InvalidInputException("Please select a book to return.");
	        }

	        if (status == null || status.trim().isEmpty()) {
	            throw new InvalidInputException("Please select the return status.");
	        }
	    }
	 
	 public static void validateBookCategory(BookCategory category) throws InvalidInputException {
		if (category == null) {
			throw new InvalidInputException("Please select Book category.");
		}
	 }
 

}
