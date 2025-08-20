package com.example.lms.service.impl;

import com.example.lms.model.Book;
import com.example.lms.repository.BookRepository;
import com.example.lms.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public int addBook(Book book) {
       return bookRepository.save(book);
    }

    @Override
    public int updateBook(Long id, Book book) {
        Book existing = bookRepository.findById(id);
        if (existing == null) {
            throw new RuntimeException("Book not found with id " + id);
        }
        book.setBookId(id);  
      return  bookRepository.update(book);
    }

    @Override
    public int deleteBook(Long id) {
       return bookRepository.deleteById(id);
    }
    


    @Override
    public Book getBookById(Long id) {
        Book book = bookRepository.findById(id);
        if (book == null) {
            throw new RuntimeException("Book not found with id " + id);
        }
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
