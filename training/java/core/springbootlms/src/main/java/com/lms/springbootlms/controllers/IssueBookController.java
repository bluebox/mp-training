package com.lms.springbootlms.controllers;


import com.lms.springbootlms.exception.ServiceException;
import com.lms.springbootlms.model.*;
import com.lms.springbootlms.service.IssueBookService;
import com.lms.springbootlms.serviceimpl.IssueBookServiceImpl;
import com.lms.springbootlms.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/issue-books")
public class IssueBookController {

    private final IssueBookServiceImpl issueBookService;

    @Autowired
    public IssueBookController(IssueBookServiceImpl issueBookService) {
        this.issueBookService = issueBookService;
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getAvailableCategories() {
        try {
            Set<BookCategory> categories = issueBookService.getAllAvailableBooks(null).stream()
                    .filter(book -> book.getStatus() == 'A' && book.getAvailability() == 'A')
                    .map(Book::getBookCategory)
                    .collect(Collectors.toSet());

            List<String> categoryList = categories.stream().map(Enum::toString).toList();
            return ResponseEntity.ok(categoryList);
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/books/{category}")
    public ResponseEntity<?> getAvailableBooksByCategory(@PathVariable String category) {
        try {
            List<Book> books = issueBookService.getAvailableBooksByCategory(BookCategory.valueOf(category.toUpperCase()));
            List<String> bookTitles = books.stream()
                    .filter(b -> b.getAvailability() == 'A' && b.getStatus() == 'A')
                    .map(Book::getBookTitle)
                    .toList();

            return ResponseEntity.ok(bookTitles);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> issueBook(
            @RequestParam String mobile,
            @RequestParam String category,
            @RequestParam String bookTitle,
            @RequestParam String dueDate) {

        try {
             Validator.validateAndFetchMemberByMobile(mobile);
             Member currentMember =issueBookService.getMemberByMobile(mobile);
            LocalDate dueDateObj = LocalDate.parse(dueDate);

            if (dueDateObj.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Due date cannot be in the past.");
            }

            List<Book> books = issueBookService.getAvailableBooksByCategory(BookCategory.valueOf(category.toUpperCase()));
            Book selectedBook = books.stream()
                    .filter(book -> book.getBookTitle().equals(bookTitle))
                    .findFirst()
                    .orElseThrow(() -> new ServiceException("Selected book not found."));

            IssueBook issue = new IssueBook();
            issue.setMemberId(currentMember.getMemberId());
            issue.setBookId(selectedBook.getBookId());
            issue.setIssueDate(LocalDate.now());
            issue.setReturnDate(dueDateObj);

            if (issueBookService.issueBook(issue)) {
                selectedBook.setAvailability('U');
                issueBookService.updateBookAvailability(selectedBook.getBookId(), 'U');
                return ResponseEntity.ok("Book issued successfully.");
            } else {
                return ResponseEntity.status(500).body("Book issue failed.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
