package com.example.Backend.controllersImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Backend.dao.BookDao;
import com.example.Backend.domain.Book;
import com.example.Backend.serviceImplementation.BookServiceImplementation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

	@Autowired
	private BookServiceImplementation bookService;

	@Autowired
	private BookDao bookDao;

	@PostMapping
	public ResponseEntity<?> addBook(@Valid @RequestBody Book book) {
		bookService.addBook(book);
		return ResponseEntity.ok(Map.of("status", "success", "message", "book added successfully"));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateBook(@PathVariable int id, @Valid @RequestBody Book book) {
		bookService.updateBook(id, book);
		return ResponseEntity.ok(Map.of("status", "success", "message", "book Updated successfully"));
	}

	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable int id) {

		bookDao.booklog(id);
		bookService.deleteBook(id);
		return "Book Deleted Successfully";
	}

	@GetMapping("/{id}")
	public Book getBookById(@PathVariable int id) {
		return bookService.getBookById(id);
	}

	@GetMapping
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

}
