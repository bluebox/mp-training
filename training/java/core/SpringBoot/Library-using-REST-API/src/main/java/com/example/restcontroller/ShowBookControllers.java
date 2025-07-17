package com.example.restcontroller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Books;
import com.example.service.BookService;

@RestController
@RequestMapping(path="/books")
@CrossOrigin(origins="http://localhost:3000")
public class ShowBookControllers {
	@Autowired
	private BookService bs;
	@GetMapping("/show")
	public ArrayList<Books> showBooks() {
		return bs.showBooks();
	}
	@PostMapping("/add")
	public String addBook(@Valid() @RequestBody Books books) {
	    return bs.add(books);
	}
	@PutMapping("/update")
	public String updateBook(@RequestBody Books b) {
		return  bs.update(b.getBookId(),b.getTitle(),b.getAuthor(),b.getCategory(),b.getStatus(),b.getAvailability());
	}
	@DeleteMapping("/delete")
	public String deleteBook(@RequestParam(name="bookId") long b) { //or use PathVariable since it takes from position
		return bs.delete(b);
	}
	@PutMapping("/updateAvailability")
	public String updateAvailability(@RequestParam long bookId) {
		return bs.changeStatus(bookId);
	}
}
