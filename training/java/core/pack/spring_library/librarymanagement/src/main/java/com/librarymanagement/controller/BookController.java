package com.librarymanagement.controller;

import com.librarymanagement.aspects.Duration;
import com.librarymanagement.exceptions.NoBookException;
import com.librarymanagement.model.Book;
import com.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Duration
    @PostMapping
    public String addBook(@RequestBody Book book) throws Exception {
        bookService.addBook(book);
       
        
        return "Book added successfully.";
    }


 	@Duration
    @PutMapping("/{bookId}")
    public String updateBook(
            @PathVariable int bookId,
            @RequestBody Book bookRequest) throws Exception {

        Book existingBook = bookService.getBookById(bookId);
        if (existingBook == null) {
        	throw new NoBookException("Book not found");
        }
        existingBook.setTitle(bookRequest.getTitle());
        existingBook.setAuthor(bookRequest.getAuthor());
        existingBook.setCategory(bookRequest.getCategory());
        existingBook.setStatus(bookRequest.getStatus());
        existingBook.setAvailability(bookRequest.getAvailability());
        bookService.updateBook(existingBook);
        return "Book updated successfully.";
    }



 	@Duration
    @PutMapping("/{id}/availability")
    public String updateBookAvailability(
            @PathVariable("id") int bookId,
            @RequestParam("availability") char availability) throws Exception {
        bookService.updateBookAvailability(bookId, availability);
        return "Book availability updated successfully.";
    }

 	 @Duration
    @GetMapping
    public List<Book> getAllBooks() throws Exception {
        return bookService.getAllBooks();
    }

   
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") int bookId) throws Exception {
        return bookService.getBookById(bookId);
     
          
        
    }
}
