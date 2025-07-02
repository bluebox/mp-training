package com.librarymanagement.dao;
import com.librarymanagement.model.*;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public interface BookDAO {
    void addBook(Book book) throws SQLException;
    void updateBook(Book book) throws SQLException;
    void updateBookAvailability(int bookId, char availability) throws SQLException;
    Book getBookById(int bookId) throws SQLException;
    List<Book> getAllBooks() throws SQLException;
}

