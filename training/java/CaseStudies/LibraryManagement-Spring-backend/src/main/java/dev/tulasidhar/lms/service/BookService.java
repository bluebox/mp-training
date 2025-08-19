package dev.tulasidhar.lms.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import dev.tulasidhar.lms.Exceptions.NoRowsEffectedException;
import dev.tulasidhar.lms.Exceptions.ValidationException;
import dev.tulasidhar.lms.model.Book;

public interface BookService {
	public int addNewBook(Book newBook) throws ValidationException, NoRowsEffectedException;
	
	public List<Book> getAllBooks();
	
	public Book getBookById(int id) throws DataAccessException;
	
	public int updateBook(Book book) throws ValidationException,DataAccessException;
	
	int updateBookAvailability(int bookId, boolean isAvailable) throws DataAccessException;
	
	public Map<String, Long> getBooksByCategory() throws DataAccessException;
}
