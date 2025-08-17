package com.lms.springbootlms.dao;

import com.lms.springbootlms.exception.DaoException;
import com.lms.springbootlms.model.Book;
import com.lms.springbootlms.model.BookCategory;

import java.util.ArrayList;
import java.util.List;

public interface BookDao {
    boolean isTitleExists(String title) throws DaoException;
    Book getBookByTitle(String title) throws DaoException;
    ArrayList<Book> getBooks() throws DaoException;
    Book getBookById(String bookId) throws DaoException;
    List<Book> getAvailableBooksByCategory(BookCategory category) throws DaoException;
    void updateBookAvailability(String bookId, char availability) throws DaoException;
    Book addBook(Book newBook) throws DaoException;
    Boolean updateBook(Book updateBook) throws DaoException;
    void deleteBookById(String bookId) throws DaoException;
    List<Book> getAvailableBooks() throws DaoException;
}
