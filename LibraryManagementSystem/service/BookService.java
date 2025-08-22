package com.library.service.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.library.exception.BookNotFoundException;
import com.library.exception.DuplicateBookException;
import com.library.model.Book;

public interface BookService {
	
	//crud
	public void addBook(Book book) throws DuplicateBookException, SQLException;
	public List<Book> getAllBooks();
	public Book getBookById(int id) throws BookNotFoundException, Exception;
	public void updateBookDetails(Book book) throws BookNotFoundException, Exception;
	public void updateAvailability(int id,char availability) throws BookNotFoundException, SQLException, Exception;

	
}
