package dev.tulasidhar.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.tulasidhar.lms.Exceptions.NoRowsEffectedException;
import dev.tulasidhar.lms.Exceptions.ValidationException;
import dev.tulasidhar.lms.model.Book;
import dev.tulasidhar.lms.service.BookService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
	@Autowired
	BookService bookService;
	
	public BookController() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	@GetMapping(value="/getallbooks")
	public List<Book> getAllBooks(){
		List<Book> books = bookService.getAllBooks();
		return books;
	}
	
	@PostMapping(value="/addbook")
	public ResponseEntity<Object> addBook(@RequestBody @Valid Book book) throws ValidationException, NoRowsEffectedException{
		book.setBook_Availability('A');
		book.setBook_Status('A');
		bookService.addNewBook(book);
		
		Map<String, String> response = new HashMap<String,String>();
		response.put("message", "Book Added Successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/updatebook")
	public int updatebook(@RequestBody @Valid Book book) throws ValidationException{
		bookService.updateBook(book);
		return 0;
	}
	
	
	
	
}