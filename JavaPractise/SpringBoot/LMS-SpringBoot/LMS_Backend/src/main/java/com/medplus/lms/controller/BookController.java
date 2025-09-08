package com.medplus.lms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.medplus.lms.domain.Book;
import com.medplus.lms.service.BookService;
import com.medplus.lms.service.BookServiceInterface;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {

	private final BookServiceInterface service;

	public BookController(BookService service) {
		this.service = service;
	}

	@PostMapping("/addbook")
	public ResponseEntity<String> addBook(@Valid @RequestBody Book book) {
		service.addBook(book);
		return ResponseEntity.ok("Book added successfully");
	}

	@GetMapping("/allbooks")
	public ResponseEntity<List<Book>> getAllBooks() {
		return ResponseEntity.ok(service.getAllBooks());
	}

	@PutMapping("/updatebook")
	public ResponseEntity<String> updateBook(@Valid @RequestBody Book book) {
		service.updateBook(book);
		return ResponseEntity.ok("Book updated successfully");
	}

	@PutMapping("/updateavailability")
	public ResponseEntity<String> updateBookAvailability(@RequestBody Book book) {
		service.updateBookAvailability(book);
		return ResponseEntity.ok("Book availability updated successfully");
	}

	@PutMapping("/deletebook/{bookId}")
	public ResponseEntity<String> deleteBook(@PathVariable int bookId) {
		service.deleteBook(bookId);
		return ResponseEntity.ok("Book inactivated successfully");
	}
	@GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable int bookId) {
        Book book = service.getBookById(bookId);
        return ResponseEntity.ok(book);
    }
}
