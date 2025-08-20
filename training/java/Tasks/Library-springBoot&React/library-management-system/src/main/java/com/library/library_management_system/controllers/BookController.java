package com.library.library_management_system.controllers;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.library_management_system.domain.Book;
import com.library.library_management_system.services.BookServiceInterface;

@RestController
@RequestMapping("/library/books")
public class BookController {

	private final BookServiceInterface bookService;

	public BookController(BookServiceInterface bookService) {
	    this.bookService = bookService;
	}

	@PostMapping("/add")
	public ResponseEntity<Book> addBook(@Valid @RequestBody Book bookInput) {
		Book book = new Book(bookInput.getTitle(), bookInput.getAuthor(), bookInput.getCategory());
		return ResponseEntity.ok(bookService.addBook(book));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book bookInput) {
		Book updatedBook = new Book(bookInput.getTitle(), bookInput.getAuthor(), bookInput.getCategory());
		updatedBook.setBookId(id);
		return ResponseEntity.ok(bookService.updateBook(id, updatedBook));
	}

	@GetMapping("/allBooks")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = bookService.getAllBooks();
		return ResponseEntity.ok(books);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable int id) {
		Book books = bookService.getBookById(id);
		return ResponseEntity.ok(books);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable int id) {
		bookService.deleteBook(id);
		return ResponseEntity.ok("Book with ID " + id + " deleted successfully");
	}

}
