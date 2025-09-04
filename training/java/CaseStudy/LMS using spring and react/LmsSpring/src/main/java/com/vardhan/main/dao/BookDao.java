package com.vardhan.main.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

import com.vardhan.main.model.Book;

public interface BookDao {
    
    Book save(Book book) throws DataAccessException;
    
    Optional<Book> findByExactTitle(String title) throws DataAccessException;
    Optional<Book> findById(Integer id) throws DataAccessException;
    Optional<Book> findByBookId(String bookId) throws DataAccessException;
    List<Book> findAll() throws DataAccessException;
    List<Book> findByCategory(String category) throws DataAccessException;
    List<Book> findByAuthor(String author) throws DataAccessException;
    List<Book> findByTitle(String title) throws DataAccessException;
    List<Book> findAvailableBooks() throws DataAccessException;
    List<Book> findAvailableBooksByCategory(String category) throws DataAccessException;
    
    Book update(Book book) throws DataAccessException;
    boolean updateAvailability(String bookId, String availability) throws DataAccessException;
    boolean updateStatus(String bookId, String status) throws DataAccessException;
    
    boolean deleteByBookId(String bookId) throws DataAccessException;
    
    boolean existsByTitle(String title) throws DataAccessException;
    boolean existsByBookId(String bookId) throws DataAccessException;
    long count() throws DataAccessException;
    long countByCategory(String category) throws DataAccessException;
}
