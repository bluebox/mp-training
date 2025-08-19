package com.lms.controller;

import com.lms.exceptions.DAOException;
import com.lms.model.Book;
import com.lms.serviceImpl.BookServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookServiceImpl bookService;
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        log.info("Fetching all books");

             try {
            	 
                 List<Book> books = bookService.getAllBooks();

                 books.forEach(book -> log.info("Book fetched: {}", book));

            return ResponseEntity.ok(bookService.getAllBooks());
        } catch (Exception e) {
            log.error("Error fetching books: {}", e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable String id) {
        log.info("Fetching book with bookId {}", id);
        try {
            Book book = bookService.getBookById(id);
            if (book == null) {
                return ResponseEntity.status(404)
                        .body("Book with ID " + id + " not found.");
            }
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            log.error("Error fetching book by ID: {}", e.getMessage(), e);
            return ResponseEntity.status(500)
                    .body("Unexpected error occurred: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addBook(@Valid @RequestBody Book book, BindingResult result) {
        if (result.hasErrors()) {
            String firstError = result.getFieldErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(firstError);
        }

        try {
            log.info("Adding new book {}", book.getBookTitle());
            bookService.addBook(book);
            return ResponseEntity.ok("Book added successfully");
        } catch (Exception e) {
            log.error("Error adding book: {}", e.getMessage(), e);
            return ResponseEntity.status(500)
                    .body("Invalid request: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @Valid @RequestBody Book book, BindingResult result) {
        if (result.hasErrors()) {
            String firstError = result.getFieldErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(firstError);
        }

        try {
            book.setId(id);
            log.info("Updating book with ID {}", id);
            bookService.updateBook(book);
            return ResponseEntity.ok("Book updated successfully");
        } catch (Exception e) {
            log.error("Error updating book: {}", e.getMessage(), e);
            return ResponseEntity.status(500)
                    .body("Invalid request: " + e.getMessage());
        }
    }
}

