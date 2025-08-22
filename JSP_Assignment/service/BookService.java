package com.library.service.interfaces;

import java.util.List;

import com.library.model.Book;

public interface BookService {
	
	//crud
	public void addBook(Book book) throws Exception;
	public List<Book> getAllBooks();
	public Book getBookById(int id) throws Exception;
	public void updateBookDetails(Book book) throws Exception;
	public void updateAvailability(int id,char availability) throws Exception;

	
}
