package com.lms.springbootlms.controllers;


import com.lms.springbootlms.exception.InvalidInputException;
import com.lms.springbootlms.exception.ServiceException;
import com.lms.springbootlms.model.Book;
import com.lms.springbootlms.model.BookCategory;
import com.lms.springbootlms.service.BookService;
import com.lms.springbootlms.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/available")
    public ResponseEntity<?> getAvailableBooks() {
        try {
            List<Book> availableBooks = bookService.getAvailableBooks();
            return ResponseEntity.ok(availableBooks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching available books: " + e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book newBook) {
        try {
            Validator.validateBook(newBook);

            newBook.setStatus('A');
            newBook.setAvailability('A');

            bookService.addBook(newBook);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Book added successfully.");
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(
            @PathVariable String bookId,
            @RequestBody Book updatedBook) {
        try {
            updatedBook.setBookId(bookId);
            bookService.updateBook(updatedBook);
            return ResponseEntity.ok("Book updated successfully.");
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Update failed: " + e.getMessage());
        }
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable String bookId) {
        try {
            Book book = bookService.getBookById(bookId);
            return ResponseEntity.ok(book);
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            return ResponseEntity.ok(books);
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching books: " + e.getMessage());
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(BookCategory.values());
    }


}
