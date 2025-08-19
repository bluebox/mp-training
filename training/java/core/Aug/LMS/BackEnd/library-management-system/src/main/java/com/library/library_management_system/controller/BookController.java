package com.library.library_management_system.controller;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library_management_system.domain.Book;
import com.library.library_management_system.response.CustomResponse;
import com.library.library_management_system.service.BookService;
import com.library.library_management_system.utils.BookAvailability;
import com.library.library_management_system.utils.BookCategory;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/books")
public class BookController {
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping(value = "/addBook")
	public ResponseEntity<CustomResponse<Book>> addBook(@Valid @RequestBody Book book) {

		bookService.addBook(book);
		CustomResponse<Book> response = new CustomResponse<>(true, "Book Added successfully!", book);
		return ResponseEntity.ok(response);

	}

	@PostMapping(value = "/updateBook")
	public ResponseEntity<CustomResponse<Book>> updateBook(@Valid @RequestBody Book book) {

		Book oldBook = bookService.getBookById(book.getId());

		Book newBook = bookService.updateBook(book, oldBook);

		CustomResponse<Book> response = new CustomResponse<>(true, "Book Updated successfully!", newBook);
		return ResponseEntity.ok(response);

	}

//	@PostMapping(value = "/updateBookAvailability")
//	public ResponseEntity<CustomResponse<Book>> updateBookAvailability(@Valid @RequestBody Book book,
//			@RequestParam String availability) {
//		Book newBook = bookService.updateAvailability(book, BookAvailability.fromDisplayName(availability));
//
//		CustomResponse<Book> response = new CustomResponse<>(true, "Book Updated successfully!", newBook);
//		return ResponseEntity.ok(response);
//
//	}

	@DeleteMapping("/deleteBook/{bookId}")
	public ResponseEntity<CustomResponse<Book>> updateDeleteBook(@PathVariable int bookId) {

		Book book = bookService.getBookById(bookId);
		bookService.deleteBook(book);

		CustomResponse<Book> response = new CustomResponse<>(true, "Book deleted successfully!", book);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/getBooks")
	public ResponseEntity<CustomResponse<List<Book>>> getAllBooks() {

		List<Book> books = bookService.getBooks();
		CustomResponse<List<Book>> response = new CustomResponse<>(true, "Books Retrived successfully!", books);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/categories")
	public ResponseEntity<CustomResponse<List<String>>> getCategories() {
		List<String> categories = Stream.of(BookCategory.values()).map(category -> category.getCategory()).toList();
		CustomResponse<List<String>> response = new CustomResponse<>(true, "Books categories Retrived successfully!",
				categories);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/getAvailableBooks")
	public ResponseEntity<CustomResponse<List<Book>>> getAvailableBooks() {

		List<Book> books = bookService.getBooks().stream()
				.filter(book -> book.getAvailability().equals(BookAvailability.AVAILABLE)).toList();
		CustomResponse<List<Book>> response = new CustomResponse<>(true, "Available Books Retrived successfully!",
				books);
		return ResponseEntity.ok(response);

	}
}
