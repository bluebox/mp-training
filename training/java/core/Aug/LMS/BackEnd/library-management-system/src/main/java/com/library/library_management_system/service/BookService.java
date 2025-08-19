package com.library.library_management_system.service;

import java.util.List;

import com.library.library_management_system.domain.Book;
import com.library.library_management_system.utils.BookAvailability;

import jakarta.transaction.Transactional;

public interface BookService {

	public int addBook(Book book);

	@Transactional
	public Book updateBook(Book book, Book oldBook);

	@Transactional
	public Book updateAvailability(Book book, BookAvailability availability);

	public Book getBookById(int id);

	public List<Book> getBooks();

	public int deleteBook(Book book);
}
