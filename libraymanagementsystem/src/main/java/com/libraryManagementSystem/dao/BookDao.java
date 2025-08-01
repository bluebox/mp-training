package com.libraryManagementSystem.dao;

import java.util.List;

import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.domain.CustomActiveIssuedBooks;
import com.libraryManagementSystem.domain.CustomCategoryCount;
import com.libraryManagementSystem.domain.CustomOverDueBooks;
import com.libraryManagementSystem.exceptions.InvalidException;
import com.libraryManagementSystem.utilities.BookAvailability;

public interface BookDao {

	void addBook(Book book) throws InvalidException;

	void updateBook(Book book, Book oldBook) throws InvalidException;

	void updateBookAvalability(Book book, BookAvailability avalability) throws InvalidException;

	boolean getBookByTitleAndAuthor(String title, String author) throws InvalidException;

	List<Book> getAllBooks() throws InvalidException;

	void deleteBook(Book book) throws InvalidException;

	void bookLog(Book book) throws InvalidException;

	Book getBookById(int id) throws InvalidException;

	List<CustomCategoryCount> getBookCountByCategory() throws InvalidException;

	List<CustomActiveIssuedBooks> getActiveIssuedBooks() throws InvalidException;

	List<CustomOverDueBooks> getOverDueBooks() throws InvalidException;

}
