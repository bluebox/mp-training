package com.example.library.service;

import java.util.List;

import com.example.library.domain.Book;

public interface BookService {
	int addBook(Book book);

	void updateBook(int id, Book book);

	void updateAvailability(int bookId, String availability);

	void deleteBook(int bookId);

	Book getBookById(int id);

	List<Book> getAllBooks();

	List<Book> getBooksByMember(int memberId);

}
