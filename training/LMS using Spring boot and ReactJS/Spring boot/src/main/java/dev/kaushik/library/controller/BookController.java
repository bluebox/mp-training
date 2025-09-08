package dev.kaushik.library.controller;

import dev.kaushik.library.model.Book;
import dev.kaushik.library.model.enums.BookCategory;
import dev.kaushik.library.model.enums.BookStatus;
import dev.kaushik.library.service.BookService;
import dev.kaushik.library.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping("/addBook")
	public ResponseEntity<Integer> addBook(@RequestBody Book book) {
		BookValidator.validate(book);
		Integer bookId = bookService.addBook(book);
		return new ResponseEntity<>(bookId, HttpStatus.CREATED);
	}

	@PutMapping("/updateBook")
	public ResponseEntity<Boolean> updateBook(@RequestBody Book book) {
		BookValidator.validate(book);
		boolean updated = bookService.updateBook(book);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	@DeleteMapping("/deleteBook/{bookId}")
	public ResponseEntity<Boolean> deleteBook(@PathVariable Integer bookId) {
		boolean deleted = bookService.deleteBook(bookId);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}

	@PostMapping("/batchDeleteBooks")
	public ResponseEntity<Integer> deleteBooksBatch(@RequestBody List<Integer> bookIds) {
		Integer deletedCount = bookService.deleteBooksBatch(bookIds);
		return new ResponseEntity<>(deletedCount, HttpStatus.OK);
	}

	@PostMapping("/updateBooksStatusBatch")
	public ResponseEntity<Integer> updateBookStatusBatch(@RequestBody List<Integer> bookIds) {
		Integer updatedCount = bookService.updateBookStatusBatch(bookIds);
		return new ResponseEntity<>(updatedCount, HttpStatus.OK);
	}

	@GetMapping("/viewBooks")
	public ResponseEntity<List<Book>> viewBooks() {
		List<Book> books = bookService.findBooks(null);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}


	@GetMapping("/getCategories")
	public ResponseEntity<List<BookCategory>> getCategories() {
		List<BookCategory> categories=Arrays.asList(BookCategory.values());
		return new ResponseEntity<>(categories, HttpStatus.OK); 
	}

	@GetMapping("/getStatuses")
	public ResponseEntity<List<BookStatus>> getStatuses() {
		List<BookStatus> statuses = Arrays.asList(BookStatus.values());
		return new ResponseEntity<>(statuses, HttpStatus.OK);
	}
	
}