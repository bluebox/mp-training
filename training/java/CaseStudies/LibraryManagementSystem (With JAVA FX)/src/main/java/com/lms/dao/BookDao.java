package com.lms.dao;


import java.util.List;
import com.lms.model.Book;

public interface BookDao {
	
	Book addBook(Book newBook);
	List<Book> getAllBooks();
	void updateBook(Book book);
	void updateBookAvailability(int bookId,boolean isavalable);
	public void addBookLogs(Book book);
	Book getBookById(int bookId);
}
