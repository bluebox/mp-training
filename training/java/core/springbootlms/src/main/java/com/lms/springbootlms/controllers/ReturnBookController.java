package com.lms.springbootlms.controllers;

import com.lms.springbootlms.service.ReturnBookServiceInterface;
import com.lms.springbootlms.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/return-books")
public class ReturnBookController {

    private final ReturnBookServiceInterface returnBookService;

    @Autowired
    public ReturnBookController(ReturnBookServiceInterface returnBookService) {
        this.returnBookService = returnBookService;
    }

    @GetMapping("/{mobile}")
    public ResponseEntity<?> fetchBooks(@PathVariable String mobile) {
        try {
            String memberName = Validator.validateAndFetchMemberNameByMobile(mobile, returnBookService);
            List<String> books = returnBookService.getIssuedBooksByMobile(mobile);
            if (books == null) books = new ArrayList<>();

            ReturnBookResponse response = new ReturnBookResponse(memberName, books);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> returnBook(
            @RequestParam String mobile,
            @RequestParam String bookName,
            @RequestParam String status) {
        try {
            Validator.validateReturnBookInputs(mobile, bookName, status);
            boolean success = returnBookService.returnBook(mobile, bookName, status);

            if (success) {
                return ResponseEntity.ok("Book returned successfully.");
            } else {
                return ResponseEntity.status(500).body("Book return failed. Please try again.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    public static class ReturnBookResponse {
        private String memberName;
        private List<String> books;

        public ReturnBookResponse(String memberName, List<String> books) {
            this.memberName = memberName;
            this.books = books;
        }

        public String getMemberName() {
            return memberName;
        }

        public List<String> getBooks() {
            return books;
        }
    }
}
