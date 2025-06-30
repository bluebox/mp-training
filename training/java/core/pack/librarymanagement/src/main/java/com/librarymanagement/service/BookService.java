package com.librarymanagement.service;
import com.librarymanagement.model.*;
import java.util.*;

import org.springframework.stereotype.Component;
@Component
public interface BookService {
    void addBook(Book book) throws Exception;
    void updateBook(Book book) throws Exception;
    void updateBookAvailability(int bookId, char availability) throws Exception;
    List<Book> getAllBooks() throws Exception;
    Book getBookById(int bookId) throws Exception;
}
