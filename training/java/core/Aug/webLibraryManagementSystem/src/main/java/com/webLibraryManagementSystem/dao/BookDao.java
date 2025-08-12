package com.webLibraryManagementSystem.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.webLibraryManagementSystem.domain.Book;
import com.webLibraryManagementSystem.exceptions.BookNotFoundException;
import com.webLibraryManagementSystem.exceptions.DatabaseOperationException;
import com.webLibraryManagementSystem.utilities.BookAvailability;

public interface BookDao {

	void addBook(Book book) throws DatabaseOperationException;

	void updateBook(Book book, Book oldBook) throws BookNotFoundException, DatabaseOperationException;

	void updateBookAvailability(Book book, BookAvailability availability, Connection con)
			throws BookNotFoundException, DatabaseOperationException;

	boolean existsByTitleAndAuthorExceptId(String title, String author, int excludeId)
			throws DatabaseOperationException;

	boolean existsByTitleAndAuthor(String title, String author) throws DatabaseOperationException;

	void bookLog(Book book, Connection con) throws SQLException;

	List<Book> getAllBooks() throws DatabaseOperationException;

	Book getBookById(int id) throws BookNotFoundException, DatabaseOperationException;

	void deleteBook(Book book) throws BookNotFoundException, DatabaseOperationException;

}
