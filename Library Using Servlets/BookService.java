package com.library.service;

import java.util.List;
import com.library.model.Book;

public interface BookService {
	int addBook(Book book);
	void updateBook(Book book);
	void updateAvailability(Book book);
	List<Book> getAllBooks();
	Book getBookById(int bookId) throws Exception;
}
