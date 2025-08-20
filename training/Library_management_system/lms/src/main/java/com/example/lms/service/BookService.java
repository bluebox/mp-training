package com.example.lms.service;

import com.example.lms.model.Book;
import java.util.List;

public interface BookService {

    int addBook(Book book);

    int updateBook(Long id, Book book);

    int deleteBook(Long id);

    Book getBookById(Long id);

    List<Book> getAllBooks();
}
