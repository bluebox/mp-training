package com.library.Library.service;

import com.library.Library.model.Book;
import com.library.Library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public Book addBook(Book book) {
        
        book.setStatus(Character.toUpperCase(book.getStatus()));
        book.setAvailability(Character.toUpperCase(book.getAvailability()));
        return bookRepo.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Book updateBook(Integer id, Book updatedBook) {
        return bookRepo.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setCategory(updatedBook.getCategory());
            book.setStatus(Character.toUpperCase(updatedBook.getStatus()));
            book.setAvailability(Character.toUpperCase(updatedBook.getAvailability()));
            return bookRepo.save(book);
        }).orElse(null);
    }

    public Book updateAvailability(Integer id, char availability) {
        return bookRepo.findById(id).map(book -> {
            book.setAvailability(Character.toUpperCase(availability));
            return bookRepo.save(book);
        }).orElse(null);
    }
    
    public Map<String, Long> getBooksPerCategory() {
        List<Object[]> results = bookRepo.countBooksByCategory();
        Map<String, Long> map = new HashMap<>();
        for (Object[] row : results) {
            map.put((String) row[0], (Long) row[1]);
        }
        return map;
    }
}
