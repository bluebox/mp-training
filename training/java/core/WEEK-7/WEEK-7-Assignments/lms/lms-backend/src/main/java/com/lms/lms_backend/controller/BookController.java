package com.lms.lms_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.lms_backend.model.Book;
import com.lms.lms_backend.service.BookService;
import com.lms.lms_backend.utilities.SuccessResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return SuccessResponse.build(HttpStatus.OK, "Books fetched successfully", books);
    }

    @PostMapping
    public ResponseEntity<Object> addBook(@Valid @RequestBody Book book) {
        bookService.addBook(book);
        return SuccessResponse.build(HttpStatus.CREATED, "Book added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable int id, @Valid @RequestBody Book book) {
        bookService.updateBookDetails(id, book);
        return SuccessResponse.build(HttpStatus.OK, "Book updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return SuccessResponse.build(HttpStatus.OK, "Book deleted successfully");
    }

    @GetMapping("/categories")
    public ResponseEntity<Object> getAllCategories() {
        List<String> categories = bookService.getAllCategories();
        return SuccessResponse.build(HttpStatus.OK, "Categories fetched successfully", categories);
    }
}
