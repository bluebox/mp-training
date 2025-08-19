package com.lms.LMS_Springboot.Controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMS_Springboot.DAO.BookDao;
import com.lms.LMS_Springboot.Model.*;
import com.lms.LMS_Springboot.Service.BookService;

import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:3000") 
@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	BookService bookservice;
	@GetMapping("/viewallbooks")
	public ResponseEntity<List<Book>> getAllbooks() throws SQLException {
		return ResponseEntity.ok(bookservice.getAllBooks());
		
	}
	@PostMapping("/addbook")

	public ResponseEntity<String> addbook(@Valid @RequestBody Book book){
	if(bookservice.addBook(book)) {
        return ResponseEntity.ok("book added successfully!");

	}
	else {
		return ResponseEntity.ok("book failed to add");
	}
		
	}
	@PostMapping("/updatebook/{bookid}")
	public ResponseEntity<String> UPDATEbook(@RequestBody Book book,@PathVariable int bookid) throws SQLException{
		if(bookservice.updateBookDetails(bookid, book)) {
	        return ResponseEntity.ok("book details updated!");

		}
		else {
			return ResponseEntity.ok("failed to update book details");
		}
			
		}
	@GetMapping("/bookbyid/{bookid}")
	public ResponseEntity<String> bookgetbyid(@PathVariable int bookid) throws SQLException{
		if(bookservice.getBookById(bookid) != null) {
	        return ResponseEntity.ok("book is available!");

		}
		else {
			return ResponseEntity.ok("book not avaialable");
		}
			
		}

}
