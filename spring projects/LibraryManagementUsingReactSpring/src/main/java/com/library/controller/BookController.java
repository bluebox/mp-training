package com.library.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.domain.Book;
import com.library.service.BookService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/add")
	public ResponseEntity<Map<String, String>> createBook(@Valid @RequestBody Book book) {
		Map<String, String> map = new HashMap<>();

		if (bookService.createBook(book)) {
			map.put("status", "200");
			map.put("message", "Book added successfully");
			return ResponseEntity.ok(map); // returns HTTP 200 with body
		}

		map.put("status", "400");
		map.put("message", "Book not added");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
	}

	// returns all books and returns book by id
	@GetMapping("/list")
	public ResponseEntity<?> viewBooks(@RequestParam(value = "id", required = false) Integer id) {
	    if (id != null) {
	        Book book = bookService.getBookById(id);
	        if (book != null) {
	            return ResponseEntity.ok(book);
	        } else {
	            Map<String, String> error = new HashMap<>();
	            error.put("status", "404");
	            error.put("message", "Book not found with ID: " + id);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	        }
	    } else {
	        List<Book> books = bookService.getAllBooks();
	        return ResponseEntity.ok(books != null ? books : new ArrayList<>());
	    }
	}

	
	@PutMapping("/update/{id}")
	public ResponseEntity<Map<String, String>> updateBook(@PathVariable int id, @RequestBody @Valid Book book) {
	    book.setBookId(id); 

	    boolean updated = bookService.updateBook(book);

	    Map<String, String> response = new HashMap<>();
	    if (updated) {
	        response.put("status", "200");
	        response.put("message", "Book updated successfully");
	        return ResponseEntity.ok(response);
	    } else {
	        response.put("status", "404");
	        response.put("message", "Book not found or not updated");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }
	}

	@GetMapping("/category-count")
	public ResponseEntity<?> getBooksCountPerCategory() {
	    Map<String, Long> categoryCount = bookService.getBooksCountPerCategory();

	    if (categoryCount == null || categoryCount.isEmpty()) {
	        // No books found
	        Map<String, String> response = new HashMap<>();
	        response.put("status", "204");
	        response.put("message", "No books found to count categories");
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	    }

	    return ResponseEntity.ok(categoryCount);
	}
 
}