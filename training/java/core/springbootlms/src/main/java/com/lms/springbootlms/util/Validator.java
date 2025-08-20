package com.lms.springbootlms.util;

import java.sql.SQLException;

import com.lms.springbootlms.exception.InvalidInputException;
import com.lms.springbootlms.exception.ServiceException;
import com.lms.springbootlms.model.Book;
import com.lms.springbootlms.model.BookCategory;
import com.lms.springbootlms.model.Member;
import com.lms.springbootlms.service.ReturnBookServiceInterface;
import com.lms.springbootlms.serviceimpl.IssueBookServiceImpl;



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
        return (bookId == null || bookId.trim().isEmpty());
    }

    public static void validateMember(Member member) throws InvalidInputException {
        StringBuilder errorMessage = new StringBuilder();

        if (member == null) {
            throw new InvalidInputException("Member cannot be null.");
        }

        if (member.getName() == null || !member.getName().matches("[A-Za-z ]{2,50}")) {
            errorMessage.append("Invalid name: Must be 2-50 characters long and contain only letters and spaces.\n");
        }
        if (member.getEmail() == null || !member.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            errorMessage.append("Invalid email format.\n");
        }
        if (member.getMobile() == null || !member.getMobile().matches("\\d{10}")) {
            errorMessage.append("Mobile number must be exactly 10 digits.\n");
        }
        if (member.getGender() == null || !(member.getGender().equalsIgnoreCase("Male") || member.getGender().equalsIgnoreCase("Female") || member.getGender().equalsIgnoreCase("Other"))) {
            errorMessage.append("Please select Gender from the dropdown.\n");
        }
        if (member.getAddress() == null || member.getAddress().trim().isEmpty()) {
            errorMessage.append("Address cannot be empty.\n");
        }

        if (errorMessage.length() > 0) {
            throw new InvalidInputException(errorMessage.toString().trim());
        }
    }

    public static void validateBook(Book newBook) throws InvalidInputException {
        if (newBook == null) {
            throw new InvalidInputException("Book cannot be null.");
        }

        if (newBook.getBookTitle() == null || newBook.getBookTitle().trim().isEmpty()) {
            throw new InvalidInputException("Book title cannot be empty.");
        }

        if (newBook.getBookAuthor() == null || newBook.getBookAuthor().trim().isEmpty()) {
            throw new InvalidInputException("Author name cannot be empty.");
        }

        if (newBook.getBookAuthor().length() < 2 || newBook.getBookAuthor().length() > 50 ||
            !newBook.getBookAuthor().matches("[A-Za-z ]+")) {
            throw new InvalidInputException("Author name must be between 2 and 50 characters and contain only letters and spaces.");
        }

        if (newBook.getBookCategory() == null) {
            throw new InvalidInputException("Please select a book category.");
        }
    }

    public static void serviceValidateBook(Book newBook) throws InvalidInputException {
        validateBook(newBook);

        if (newBook.getStatus() != 'A' && newBook.getStatus() != 'I') {
            throw new InvalidInputException("Status must be either 'A' (Active) or 'I' (Inactive).");
        }

        if (newBook.getAvailability() != 'A' && newBook.getAvailability() != 'U') {
            throw new InvalidInputException("Availability must be either 'A' (Available) or 'U' (Unavailable).");
        }
    }

    public static void validateAndFetchMemberNameByMobile(String mobile)
            throws InvalidInputException, SQLException, ServiceException {
        validateMobileNumber(mobile);
        
    }

    public static void validateAndFetchMemberByMobile(String mobile)
            throws InvalidInputException, SQLException {
        if (mobile == null || mobile.isEmpty()) {
            throw new InvalidInputException("Mobile number is required.");
        }

        validateMobileNumber(mobile);

        
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
