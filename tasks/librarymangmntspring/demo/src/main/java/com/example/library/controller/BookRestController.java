package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dto.ApiResponse;
import com.example.library.model.Book;
import com.example.library.model.BookCategoryCount;
import com.example.library.service.BookService;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(
	    origins = "http://localhost:5173",
	    allowCredentials = "true"
	)
public class BookRestController {
	private final BookService bookService;

	@Autowired
	public BookRestController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("")
	public ResponseEntity<ApiResponse<List<Book>>> viewBooks() {
		try {
			List<Book> books = bookService.getAllBooks();
			return ResponseEntity.ok(new ApiResponse<>(true, "Books fetched successfully", books));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(false, "Failed to fetch books: " + e.getMessage(), null));
		}
	}

	@PostMapping("/add")
	public ResponseEntity<ApiResponse<Book>> addBook(@RequestBody Book book) {
		try {
			bookService.addBook(book);
			return ResponseEntity.ok(new ApiResponse<>(true, "Book added successfully", book));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(false, "Failed to add book: " + e.getMessage(), null));
		}
	}
	
	@GetMapping("/category-count")
	public ResponseEntity<ApiResponse<List<BookCategoryCount>>> bookCategoryCount() {
		List<BookCategoryCount> categoryCounts;
		try {
			categoryCounts = bookService.getBookCountPerCategory();
			return ResponseEntity.ok(new ApiResponse<>(true, "Book category count fetched", categoryCounts));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(false, "Failed to fetch data: " + e.getMessage(), null));
		}
	}

	@PostMapping("/update")
	public ResponseEntity<ApiResponse<Book>> updateBook(@RequestBody Book book) {
		try {
			bookService.updateBookAvailability(book.getBookId(),book.getAvailability());
			return ResponseEntity.ok(new ApiResponse<>(true, "Book updated successfully", book));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(false, "Failed to updata data: " + e.getMessage(), null));
		}
	}

}
