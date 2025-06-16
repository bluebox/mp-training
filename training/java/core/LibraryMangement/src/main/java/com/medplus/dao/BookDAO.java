package com.medplus.dao;
import com.medplus.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO {
    void addBook(Book book) throws Exception;
    void updateBook(Book book) throws Exception;
    void updateAvailability(int bookId, char availability) throws Exception;
    List<Book> getAllBooks() throws Exception;
    Book getBookById(int bookId) throws SQLException;
}