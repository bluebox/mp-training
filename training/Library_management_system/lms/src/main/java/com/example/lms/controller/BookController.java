package com.example.lms.controller;

import com.example.lms.model.Book;
import com.example.lms.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public String addBook(@RequestBody Book book) {
      int rows=  bookService.addBook(book);
        return rows>0? "Book added successfully!":"Failed to add Book";
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable Long id, @RequestBody Book book) {
       int rows= bookService.updateBook(id, book);
        return rows>0?"Book updated successfully!":"Failed to update Book";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
      int rows=  bookService.deleteBook(id);
        return rows>0?" Book deleted successfully!":"Failed to delete Book";
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}
