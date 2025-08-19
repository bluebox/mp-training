package com.example.Backend.dao;

import java.util.List;

import com.example.Backend.domain.Book;

public interface BookDao {
	int addBook(Book book);

	int updateBook(Book book);

	int updateAvailability(int bookId, String availability);

	int deleteBook(int id);

	List<Book> findAllBooks();

	Book findById(int id);

	int booklog(int bookId);

}
