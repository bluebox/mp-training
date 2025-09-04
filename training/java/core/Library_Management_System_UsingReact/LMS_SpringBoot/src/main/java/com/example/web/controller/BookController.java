package com.example.web.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.model.Book;
import com.example.web.service.BookService;


@RestController
@CrossOrigin("*")
public class BookController {
		@Autowired
		private BookService bookService;
		private Book book;
		
		@PostMapping("/addbook")
		public ResponseEntity<String> addBook(@RequestParam("book_Title")String book_Title,@RequestParam("book_Author")String book_Author,@RequestParam("book_Category")String book_Category) {
			book = new Book(book_Title,book_Author, book_Category);
			bookService.addNewBook(book);
			return ResponseEntity.ok("Book created successfully!");
		}
		
		@GetMapping("/getbooks")
		public ResponseEntity<List<Book>> getBooks(){
			List<Book> books = bookService.getAllBooks();
			return ResponseEntity.ok(books);
		}
		
		@PostMapping("/get/{id}")
		public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
			book = bookService.getBookById(id);
			return ResponseEntity.ok(book);
		}
		
		@PutMapping("/updatebook/{book_Id}")
		public ResponseEntity<String> updateBook(@PathVariable("book_Id") String book_Id, @RequestBody Book book){
			book.setBook_Id(Integer.parseInt(book_Id));
			bookService.updateBook(book);
			return ResponseEntity.ok("Book updated successfully!");
		}
		
		@GetMapping("/getAvailablebooks")
		public ResponseEntity<List<Book>> getAvailableBooks() {
				List<Book> books= bookService.getAllBooks().stream().filter(book-> ("A".equals(String.valueOf(book.getBook_Status())) && "A".equals(String.valueOf(book.getBook_Availability()))
				)).collect(Collectors.toList());
				return ResponseEntity.ok(books);
		}
		
		@GetMapping("/getbooksbyCategory")
		public ResponseEntity<Map<String, Long>> getbooksByCategory(){
			Map<String, Long> categoryStats = bookService.getBooksByCategory();
			return ResponseEntity.ok(categoryStats); 
		}
}
