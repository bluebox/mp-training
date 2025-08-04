package com.webLibraryManagementSystem.services;

import java.util.List;
import java.util.Map;

import com.webLibraryManagementSystem.domain.Book;
import com.webLibraryManagementSystem.domain.CustomActiveIssuedBooks;
import com.webLibraryManagementSystem.utilities.BookAvailability;
import com.webLibraryManagementSystemexceptions.InvalidException;

public interface BookServices {

	void addBook(Book book) throws InvalidException;

	List<Book> getBooks() throws InvalidException;

	void deleteBook(Book book) throws InvalidException;

	void updateBook(Book newBook, Book oldBook) throws InvalidException;

	void updateBookAvailability(Book book, BookAvailability avail) throws InvalidException;

	Book getBookById(int bookId) throws InvalidException;

	Map<String, Integer> getBookCountByCategory() throws InvalidException;

	List<CustomActiveIssuedBooks> getActiveIssuedBooks() throws InvalidException;

	List<CustomActiveIssuedBooks> getOverDueBooks() throws InvalidException;

}
