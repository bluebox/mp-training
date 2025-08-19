package com.example.Backend.service;

import java.util.List;

import com.example.Backend.domain.Book;

public interface BookService {
	int addBook(Book book);

	void updateBook(int id, Book book);

	void deleteBook(int bookId);

	Book getBookById(int id);

	List<Book> getAllBooks();

}
