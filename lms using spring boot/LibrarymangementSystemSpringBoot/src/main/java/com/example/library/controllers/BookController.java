package com.example.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.domain.Book;
import com.example.library.serviceimplementation.BookServiceImpl;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

	@Autowired
	private BookServiceImpl bookService;

	@PostMapping
	public String addBook(@RequestBody Book book) {
		bookService.addBook(book);
		return "Book Added Successfully";
	}

	@PutMapping("/{id}")
	public String updateBook(@PathVariable int id, @RequestBody Book book) {
		bookService.updateBook(id, book);
		return "Book Updated Successfully";
	}

	@PutMapping("/{id}/availability")
	public String updateAvailability(@PathVariable int id, @RequestParam String availability) {
		bookService.updateAvailability(id, availability);
		return "Book Availability Updated Successfully";
	}

	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable int id) {
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

	@GetMapping("/member/{memberId}")
	public List<Book> getBooksByMember(@PathVariable int memberId) {
		return bookService.getBooksByMember(memberId);
	}
}
