package com.webLibraryManagementSystem.services;

import java.util.List;

import com.webLibraryManagementSystem.domain.Book;
import com.webLibraryManagementSystem.exceptions.BookNotFoundException;
import com.webLibraryManagementSystem.exceptions.DatabaseOperationException;
import com.webLibraryManagementSystem.exceptions.DuplicateBookException;
import com.webLibraryManagementSystem.exceptions.InvalidBookDataException;
import com.webLibraryManagementSystem.utilities.BookAvailability;

public interface BookServices {

	void addBook(Book book) throws InvalidBookDataException, DuplicateBookException, DatabaseOperationException;

	List<Book> getBooks() throws DatabaseOperationException;

	void deleteBook(Book book) throws BookNotFoundException, DatabaseOperationException, InvalidBookDataException;

	void updateBook(Book newBook, Book oldBook)
			throws BookNotFoundException, InvalidBookDataException, DatabaseOperationException, DuplicateBookException;

	void updateBookAvailability(Book book, BookAvailability avail)
			throws BookNotFoundException, DatabaseOperationException;

	Book getBookById(int bookId) throws DatabaseOperationException, BookNotFoundException;

}
