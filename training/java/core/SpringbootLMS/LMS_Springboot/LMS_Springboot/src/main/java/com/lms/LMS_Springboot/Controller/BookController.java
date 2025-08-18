package com.lms.LMS_Springboot.Controller;

import com.lms.LMS_Springboot.Model.Book;
import com.lms.LMS_Springboot.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:3001") 
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public String addBook(@RequestBody Book book) {
        int rows = bookService.addBook(book);
        return rows > 0 ? "Book added successfully" : "Failed to add book";
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable int id, @RequestBody Book book) {
        int rows = bookService.updateBook(id, book);
        return rows > 0 ? "Book updated successfully" : "Failed to update book";
    }
}
