package com.example.library.dao;

import java.util.List;

import com.example.library.exception.DatabaseException;
import com.example.library.model.Book;

public interface BookDAO {
    Book getBookById(int bookId) throws DatabaseException;
    void addBook(Book book) throws DatabaseException;
    List<Book> getAllBooks() throws Exception;
    void updateBook(Book book) throws Exception;
    void updateBookAvailability(int bookId, Character availability) throws Exception;
}
