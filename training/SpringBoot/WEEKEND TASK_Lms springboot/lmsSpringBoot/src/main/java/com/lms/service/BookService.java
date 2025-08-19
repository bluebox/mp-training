package com.lms.service;

import com.lms.model.Book;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks() throws Exception;
    Book getBookById(String bookId) throws Exception;
    void addBook(Book book) throws Exception;
    void updateBook(Book book) throws Exception;
}
