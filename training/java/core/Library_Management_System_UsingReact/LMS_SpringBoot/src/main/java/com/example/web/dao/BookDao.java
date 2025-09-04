package com.example.web.dao;


import java.util.List;
import com.example.web.model.Book;

public interface BookDao {
	
	int addBook(Book newBook);
	List<Book> getAllBooks();
	void updateBook(Book book);
	void updateBookAvailability(int bookId,boolean isavalable);
	public void addBookLogs(Book book);
	Book getBookById(int bookId);
}
