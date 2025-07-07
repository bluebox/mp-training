package com.library.app.restController;

import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.library.app.model.Book;
import com.library.app.model.Response;
import com.library.app.service.LibraryService;

@Slf4j
@RestController
@RequestMapping("/api/book")
@CrossOrigin(origins="http://localhost:3000/")
//@CrossOrigin(origins="*")

public class BookController {
	
	private LibraryService libraryService;
	@Autowired
	public BookController(LibraryService libraryService) {
		this.libraryService=libraryService;
	}
	
	@Autowired
	WebClient webClient;

	
	@PostMapping(value="/addBook")
	public ResponseEntity<Response> addBookPage(@RequestHeader("invocationFrom") String invocationFrom,@RequestBody Book book) {
	    
			Response response = new Response();
	        log.info(String.format("Header invocationFrom = %s", invocationFrom));
	        try {
				libraryService.addBook(book);
				response.setStatusCode("200");
				response.setStatusMsg("Book added successfully!");
				return ResponseEntity
						.status(HttpStatus.CREATED)
						.header("isBookAdded", "true")
						.body(response);
			} 
	        catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        response.setStatusCode("400");
            response.setStatusMsg("Book not added!");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
	}
	
	@PutMapping(value="/updateBook")
	public ResponseEntity<Response> updateBookPage(@RequestHeader("invocationFrom") String invocationFrom,@RequestBody Book book) {
	    
		Response response = new Response();
	        log.info(String.format("Header invocationFrom = %s", invocationFrom));
	        try {
	        	Book isPresentBook = libraryService.getBookById(book.getBookId());
	        	log.info("The BOOK ID is"+isPresentBook.getBookId());
	        	if(isPresentBook !=null && isPresentBook.getBookId() == book.getBookId()) {
				libraryService.updateBook(book);
				response.setStatusCode("200");
				response.setStatusMsg("Book updated successfully!");
				return ResponseEntity
						.status(HttpStatus.CREATED)
						.header("isBookUpdated", "true")
						.body(response);
	        	}
			} 
	        catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        response.setStatusCode("400");
            response.setStatusMsg("No book found on this Book ID!");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
		
	}
	
	@GetMapping(value="/books")
	public List<Book> viewAllBooksPage() throws Exception {
	        List<Book> books = libraryService.viewAllBooks();
		    return books;            
	}
	
//	Rest Consuming

	@GetMapping("/getAllBooks")
    public Flux<Book> getBook(){
        String uri = "http://10.129.242.2:8080/api/book/getAllBooks";        
        return webClient.get().uri(uri)
                .header("invocationFrom","WebClient")
                .retrieve()
                .bodyToFlux(Book.class);
    }
	
	
}



