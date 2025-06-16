package com.LibraryManagement.dao;
import java.sql.SQLException;
import java.util.List;
import com.LibraryManagement.model.*;

public interface BookDAO {
    void addBook(Book book) throws SQLException;
    void updateBook(Book book) throws SQLException;
    void updateBookAvailability(int bookId, char availability) throws SQLException;
    Book getBookById(int bookId) throws SQLException;
    List<Book> getAllBooks() throws SQLException;
}

