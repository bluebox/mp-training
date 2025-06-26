package com.library.service;
import com.library.model.Book;
import java.util.*;

public interface BookService {
    void addBook(Book book) throws Exception;
    void updateBook(Book book) throws Exception;
    void updateBookAvailability(int bookId, char availability) throws Exception;
    List<Book> getAllBooks() throws Exception;
    Book getBookById(int bookId) throws Exception;
}
