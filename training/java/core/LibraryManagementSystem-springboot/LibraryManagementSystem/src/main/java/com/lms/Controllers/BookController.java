package com.lms.Controllers;


import com.lms.Models.Book;
import com.lms.Services.Implementation.BookServiceImplementation;
import com.lms.Services.Interfaces.BookService;


import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/books")
@CrossOrigin("http://localhost:5173/")
public class BookController {
    
    private final BookServiceImplementation bookService;
    
    @Autowired
    public BookController(BookServiceImplementation bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping
    public ResponseEntity<List<Book>> all() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Integer> newBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) throws Exception {
    	 if (bookService.getBookById(id) != null) {
    		 return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    	 }
//        return bookService.getBookById(id)
//            .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
//            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("/{id}")
    public void updateBook(@RequestBody Book newBook, @PathVariable int id) throws Exception {
        if (bookService.getBookById(id)!=null) {
            newBook.setBookId(id);
           bookService.updateBook(newBook);
            //return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
             bookService.addBook(newBook);
            //return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
        }
    }
    
    @PutMapping("/{id}/availability")
    public void updateAvailability(@RequestBody Book newBook, @PathVariable int id) throws Exception {
        if (bookService.getBookById(id) != null) {
            newBook.setBookId(id);
           bookService.updateAvailability(newBook);
           // return new ResponseEntity<>( HttpStatus.OK);
            return;
        } 
    }
}