package com.libraryManagementSystem.services;

import java.util.List;

import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.domain.CustomActiveIssuedBooks;
import com.libraryManagementSystem.domain.CustomCategoryCount;
import com.libraryManagementSystem.domain.CustomOverDueBooks;
import com.libraryManagementSystem.exceptions.InvalidException;
import com.libraryManagementSystem.utilities.BookAvailability;

public interface BookServices {

	void addBook(Book book) throws InvalidException;

	List<Book> getBooks() throws InvalidException;

	void deleteBook(Book book) throws InvalidException;

	void updateBook(Book newBook, Book oldBook) throws InvalidException;

	void updateBookAvailability(Book book, BookAvailability avail) throws InvalidException;

	List<CustomCategoryCount> getBookCountByCategory() throws InvalidException;

	List<CustomActiveIssuedBooks> getActiveIssuedBooks() throws InvalidException;

	List<CustomOverDueBooks> getOverDueBooks() throws InvalidException;

}
