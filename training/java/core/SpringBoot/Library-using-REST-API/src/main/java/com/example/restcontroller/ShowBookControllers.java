package com.example.restcontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@RequestMapping(path="/books", produces=MediaType.APPLICATION_XML_VALUE)
public class ShowBookControllers {
	@Autowired
	private BookService bs;
	@GetMapping("/show")
	public ArrayList<Books> showBooks() {
		return bs.showBooks();
	}
	@PostMapping("/add")
	public String addBook(@RequestBody Books books) {
		return bs.add(books);
	}
	@PutMapping("/update")
	public String updateBook(@RequestBody Books b) {
//		Response res=new Response();
//		res.setStatus(200,bs.update(b.getBookId(),b.getTitle(),b.getAuthor(),b.getCategory(),b.getStatus(),b.getAvailability()));
//		return ResponseEntity
//				.status(HttpStatus.CREATED)
//				.header("isMsgSaved", "true")
//				.body(res);
		return  bs.update(b.getBookId(),b.getTitle(),b.getAuthor(),b.getCategory(),b.getStatus(),b.getAvailability());
	}
	@DeleteMapping("/delete")
	public String deleteBook(@RequestParam(name="bookId") long b) { //or use PathVariable since it takes from position
		return bs.delete(b);
	}
}
