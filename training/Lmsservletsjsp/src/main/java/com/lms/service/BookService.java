package com.lms.service;



import java.sql.SQLException;
import java.util.List;

import com.lms.exceptions.InvalidInputException;
import com.lms.model.Book;

public interface BookService {
	public Book getBookById(String bookId) throws InvalidInputException;
	
	public List<Book> getAllBooks() throws SQLException;

	
	public void addBook(Book newBook) throws InvalidInputException ;
	
	public Boolean updateBook(Book updateBook) throws InvalidInputException ;
}