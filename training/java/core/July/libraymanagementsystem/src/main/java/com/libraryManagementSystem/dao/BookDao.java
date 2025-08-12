package com.libraryManagementSystem.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.exceptions.BookNotFoundException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.utilities.BookAvailability;

public interface BookDao {

	void addBook(Book book) throws DatabaseOperationException;

	void updateBook(Book book, Book oldBook) throws BookNotFoundException, DatabaseOperationException;

	void updateBookAvailability(Book book, BookAvailability availability)
			throws BookNotFoundException, DatabaseOperationException;

	boolean existsByTitleAndAuthorExceptId(String title, String author, int excludeId)
			throws DatabaseOperationException;

	boolean existsByTitleAndAuthor(String title, String author) throws DatabaseOperationException;

	List<Book> getAllBooks() throws DatabaseOperationException;

	Book getBookById(int id) throws BookNotFoundException, DatabaseOperationException;

	void deleteBook(Book book) throws BookNotFoundException, DatabaseOperationException;

	void bookLog(Book book, Connection conn) throws SQLException;

}
