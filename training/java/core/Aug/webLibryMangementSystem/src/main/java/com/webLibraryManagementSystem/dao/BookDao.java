package com.webLibraryManagementSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import com.webLibraryManagementSystem.domain.Book;
import com.webLibraryManagementSystem.domain.CustomActiveIssuedBooks;
import com.webLibraryManagementSystem.utilities.BookAvailability;
import com.webLibraryManagementSystemexceptions.InvalidException;

public interface BookDao {

	void addBook(Book book) throws InvalidException;

	void updateBook(Book book, Book oldBook) throws InvalidException;

	void updateBookAvalability(Book book, BookAvailability avalability) throws InvalidException;

	boolean getBookByTitleAndAuthor(String title, String author) throws InvalidException;

	List<Book> getAllBooks() throws InvalidException;

	void deleteBook(Book book) throws InvalidException;

	void bookLog(Book book, Connection con, PreparedStatement stmt) throws InvalidException;

	Book getBookById(int id) throws InvalidException;

	Map<String, Integer> getBookCountByCategory() throws InvalidException;

	List<CustomActiveIssuedBooks> getActiveIssuedBooks() throws InvalidException;

	List<CustomActiveIssuedBooks> getOverDueBooks() throws InvalidException;

}
