package com.libraryManagementSystem.services;

import java.util.List;

import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.exceptions.BookNotFoundException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.DuplicateBookException;
import com.libraryManagementSystem.exceptions.InvalidBookDataException;
import com.libraryManagementSystem.utilities.BookAvailability;

public interface BookServices {

	void addBook(Book book) throws InvalidBookDataException, DuplicateBookException, DatabaseOperationException;

	List<Book> getBooks() throws DatabaseOperationException;

	void deleteBook(Book book) throws BookNotFoundException, DatabaseOperationException, InvalidBookDataException;

	void updateBook(Book newBook, Book oldBook)
			throws BookNotFoundException, InvalidBookDataException, DatabaseOperationException, DuplicateBookException;

	void updateBookAvailability(Book book, BookAvailability avail)
			throws BookNotFoundException, DatabaseOperationException;

}
