/*package com.lms.util;

import java.sql.SQLException;
import java.time.LocalDate;

import com.lms.daoImpl.BookDaoImpl;
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
		    String err = "";
		    try {
		        validateBook(newBook); // existing validations
		    } catch (InvalidInputException e) {
		        err = e.getMessage();
		    }

		    // Corrected String comparisons
		    if (!"A".equals(newBook.getStatus()) && !"I".equals(newBook.getStatus())) {
		        err = "Status must be either 'A' (Active) or 'I' (Inactive).";
		    } else if (!"A".equals(newBook.getAvailability()) && !"U".equals(newBook.getAvailability())) {
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

	 
	 public static Member validateAndFetchMemberByMobile(String mobile, IssueBookServiceImpl issueBookService) throws Exception {
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
 

}*/



package com.lms.util;

import java.sql.SQLException;

import com.lms.exceptions.InvalidInputException;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.model.Member;
import com.lms.serviceImpl.IssueBookServiceImpl;
import com.lms.service.ReturnBookServiceInterface;

public class Validator {

    public static void validateMobileNumber(String mobile) throws InvalidInputException {
        if (mobile == null || mobile.trim().isEmpty()) {
            throw new InvalidInputException("Mobile number is required to query DAO.");
        }
    }

    public static void validateBookId(String bookId) throws InvalidInputException {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new InvalidInputException("Book ID is required to query DAO.");
        }
    }

    public static void validateMember(Member member) throws InvalidInputException {
        if (member == null) {
            throw new InvalidInputException("Member cannot be null.");
        }

        if (member.getName() == null || member.getName().trim().isEmpty()) {
            throw new InvalidInputException("Member name can't be null or empty.");
        }

        if (member.getMobile() == null || member.getMobile().trim().isEmpty()) {
            throw new InvalidInputException("Member mobile cannot be null or empty.");
        }
        
        if (member.getEmail() == null || member.getEmail().trim().isEmpty()) {
			throw new InvalidInputException("Member email cannot be null or empty.");
		}
        
        if (member.getGender() == null || member.getGender().trim().isEmpty()) {
			throw new InvalidInputException("Member gender cannot be null or empty.");
			}
		if (member.getAddress() == null || member.getAddress().trim().isEmpty()) {
			throw new InvalidInputException("Member address cannot be null or empty.");
		}
        
    }

    public static void validateBook(Book book) throws InvalidInputException {
        if (book == null) {
            throw new InvalidInputException("Book cannot be null.");
        }

        if (book.getBookTitle() == null || book.getBookTitle().trim().isEmpty()) {
            throw new InvalidInputException("Book title is required for DAO.");
        }

        if (book.getBookAuthor() == null || book.getBookAuthor().trim().isEmpty()) {
            throw new InvalidInputException("Book author is required for DAO.");
        }

        if (book.getBookCategory() == null) {
            throw new InvalidInputException("Book category is required for DAO.");
        }

        if (!"A".equals(book.getStatus()) && !"I".equals(book.getStatus())) {
            throw new InvalidInputException("Status must be either 'A' or 'I'.");
        }

        if (!"A".equals(book.getAvailability()) && !"U".equals(book.getAvailability())) {
            throw new InvalidInputException("Availability must be either 'A' or 'U'.");
        }
    }

    public static String validateAndFetchMemberNameByMobile(String mobile, ReturnBookServiceInterface service)
            throws InvalidInputException, SQLException {
        validateMobileNumber(mobile);

        String memberName = service.getMemberNameByMobile(mobile);
        if (memberName == null || memberName.trim().isEmpty()) {
            throw new InvalidInputException("Member not found in database for provided mobile.");
        }

        return memberName;
    }

    public static Member validateAndFetchMemberByMobile(String mobile, IssueBookServiceImpl service)
            throws Exception {
        validateMobileNumber(mobile);

        Member member = service.getMemberByMobile(mobile);
        if (member == null) {
            throw new InvalidInputException("Member not found in database.");
        }

        return member;
    }

    public static void validateReturnBookInputs(String mobile, String bookName, String status)
            throws InvalidInputException {
        validateMobileNumber(mobile);

        if (bookName == null || bookName.trim().isEmpty()) {
            throw new InvalidInputException("Book name is required to process return.");
        }

        if (status == null || status.trim().isEmpty()) {
            throw new InvalidInputException("Return status is required.");
        }
    }

    public static void validateBookCategory(BookCategory category) throws InvalidInputException {
        if (category == null) {
            throw new InvalidInputException("Book category is required for DAO.");
        }
    }
}

