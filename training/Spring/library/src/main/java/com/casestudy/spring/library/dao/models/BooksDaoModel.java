package com.casestudy.spring.library.dao.models;

import java.sql.SQLException;
import java.util.List;

import com.casestudy.spring.library.beans.Book;

public interface BooksDaoModel {
	
    void createBook(Book book) throws SQLException;

    void updateBookAvailability(int bookId);

    boolean updateBook(Book book);

    List<Book> viewAllBooks();

    boolean CanBeIssued(int bookId);

    Book searchBook(int tempId);

    boolean findBook(int tempId);
	
}
