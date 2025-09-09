package com.vardhan.main.util;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vardhan.main.service.BookService;
import com.vardhan.main.service.IssueBookService;
import com.vardhan.main.service.MemberService;
import com.vardhan.main.service.ReturnBookService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidationUtil {

    private final Validator beanValidator;
    private final BookService bookService;
    private final MemberService memberService;
    private final IssueBookService issueBookService;
    private final ReturnBookService returnBookService;

    public <T> void validateBean(T object) {
        Set<ConstraintViolation<T>> violations = beanValidator.validate(object);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    @Transactional(readOnly = true)
    public void assertUniqueBookTitle(String title) {
        if (title != null && bookService.isTitleExists(title)) {
            throw new IllegalArgumentException("A book with this title already exists: " + title);
        }
    }

    @Transactional(readOnly = true)
    public void assertUniqueBookTitleForUpdate(String title, String currentBookId) {
        if (title != null && bookService.isTitleExists(title)) {
            var existingBook = bookService.findBookByBookId(currentBookId);
            if (existingBook.isPresent() && !existingBook.get().getTitle().equals(title)) {
                throw new IllegalArgumentException("Another book with this title already exists: " + title);
            }
        }
    }

    public void validateBookId(String bookId) {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be empty");
        }
        if (!bookId.matches("B\\d{4}")) {
            throw new IllegalArgumentException("Book ID must be in format B0001");
        }
    }

    @Transactional(readOnly = true)
    public void validateBookAvailableForIssue(String bookId) {
        var bookOpt = bookService.findBookByBookId(bookId);
        if (bookOpt.isEmpty()) {
            throw new IllegalArgumentException("Book not found: " + bookId);
        }

        var book = bookOpt.get();
        if (!book.isActive()) {
            throw new IllegalArgumentException("Book is inactive: " + bookId);
        }

        if (!book.isAvailable()) {
            throw new IllegalArgumentException("Book is not available: " + bookId);
        }

        if (issueBookService.isBookCurrentlyIssued(bookId)) {
            throw new IllegalArgumentException("Book is already issued: " + bookId);
        }
    }

    @Transactional(readOnly = true)
    public void assertMemberEmailUnique(String email) {
        if (email != null && memberService.isEmailExists(email)) {
            throw new IllegalArgumentException("Email already exists: " + email);
        }
    }

    public void validateMobileFormat(String mobile) {
        if (mobile == null || !mobile.matches("\\d{10,15}")) {
            throw new IllegalArgumentException("Mobile must contain 10-15 digits");
        }
    }

    @Transactional(readOnly = true)
    public void validateMemberCanIssueBook(Integer memberId, String bookId) {
        if (!issueBookService.canMemberIssueBook(memberId, bookId)) {
            throw new IllegalArgumentException("Member cannot issue this book (limit exceeded or already issued)");
        }
    }

    @Transactional(readOnly = true)
    public void validateReturnRequest(String mobile, String bookTitle) {
        validateMobileFormat(mobile);
        if (bookTitle == null || bookTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be empty");
        }

        if (returnBookService.getMemberNameByMobile(mobile).isEmpty()) {
            throw new IllegalArgumentException("Member not found for mobile: " + mobile);
        }

        if (!returnBookService.isBookIssuedToMember(mobile, bookTitle)) {
            throw new IllegalArgumentException("Book '" + bookTitle + "' is not issued to this member");
        }
    }

    @Transactional(readOnly = true)
    public void validateIssueBookRequest(Integer memberId, String bookId) {
        if (memberService.findMemberById(memberId).isEmpty()) {
            throw new IllegalArgumentException("Member not found: " + memberId);
        }

        validateBookAvailableForIssue(bookId);
        validateMemberCanIssueBook(memberId, bookId);
    }

    public void validateBookStatus(String status) {
        if (status == null || (!status.equals("A") && !status.equals("I") && !status.equals("D"))) {
            throw new IllegalArgumentException("Status must be 'A' (Active), 'I' (Inactive), or 'D' (Deleted)");
        }
    }

    public void validateBookAvailability(String availability) {
        if (availability == null || (!availability.equals("A") && !availability.equals("I"))) {
            throw new IllegalArgumentException("Availability must be 'A' (Available) or 'I' (Issued)");
        }
    }

    public void validateReturnStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Return status cannot be empty");
        }

        if (!status.equalsIgnoreCase("Active") && !status.equalsIgnoreCase("Inactive")) {
            throw new IllegalArgumentException("Return status must be 'Active' or 'Inactive'");
        }
    }

    public void validatePositiveId(Integer id, String fieldName) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(fieldName + " must be a positive number");
        }
    }
}
