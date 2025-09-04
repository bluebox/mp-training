package com.vardhan.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

import com.vardhan.main.model.Book;

public interface BookService {
    
    Book saveBook(Book book) throws DataAccessException;
    
    Optional<Book> findBookById(Integer id) throws DataAccessException;
    Optional<Book> findBookByBookId(String bookId) throws DataAccessException;
    List<Book> getAllBooks() throws DataAccessException;
    List<Book> findBooksByCategory(String category) throws DataAccessException;
    List<Book> findBooksByAuthor(String author) throws DataAccessException;
    List<Book> searchBooksByTitle(String title) throws DataAccessException;
    List<Book> getAvailableBooks() throws DataAccessException;
    List<Book> getAvailableBooksByCategory(String category) throws DataAccessException;
    
    Book updateBook(Book book) throws DataAccessException;
    boolean markBookAsIssued(String bookId) throws DataAccessException;
    boolean markBookAsAvailable(String bookId) throws DataAccessException;
    
    boolean deleteBook(String bookId) throws DataAccessException;
    
    boolean isBookExists(String bookId) throws DataAccessException;
    boolean isTitleExists(String title) throws DataAccessException;
    
    long getTotalBooksCount() throws DataAccessException;
    long getBookCountByCategory(String category) throws DataAccessException;
}
