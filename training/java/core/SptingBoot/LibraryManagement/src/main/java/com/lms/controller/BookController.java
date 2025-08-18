package com.lms.controller;

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

import com.lms.model.Book;
import com.lms.service.interfaces.implementation.BookServiceImplementation;

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
    public ResponseEntity<Book> newBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> one(@PathVariable int id) {
        return bookService.getBookById(id)
            .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book newBook, @PathVariable int id) {
        if (bookService.getBookById(id).isPresent()) {
            newBook.setBookId(id);
            Book updatedBook = bookService.updateBook(newBook);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            Book createdBook = bookService.addBook(newBook);
            return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
        }
    }
    
    @PutMapping("/{id}/availability")
    public ResponseEntity<Book> updateAvailability(@RequestBody Book newBook, @PathVariable int id) {
        if (bookService.getBookById(id).isPresent()) {
            newBook.setBookId(id);
            Book updatedBook = bookService.updateAvailability(newBook);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            Book createdBook = bookService.addBook(newBook);
            return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
        }
    }
}
