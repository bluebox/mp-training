package com.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.library.model.Book;

public interface BookDAO {

	 public int addBook(Book book); 
	 public void updateBook(Book book);
	 public void updateAvailability(Book book);
	 public List<Book>getAllBooks();
	public Book getBookById(int bookId) throws Exception;
	 
}
