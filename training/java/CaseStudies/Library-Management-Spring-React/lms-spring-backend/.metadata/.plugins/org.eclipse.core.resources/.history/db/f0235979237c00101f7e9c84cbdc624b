package dev.tulasidhar.lms.service;

import java.util.List;
import java.util.Map;

import dev.tulasidhar.lms.Exceptions.DBConstrainsException;
import dev.tulasidhar.lms.model.Book;

public interface BookService {
	public void addNewBook(Book newBook) throws DBConstrainsException;
	public List<Book> getAllBooks();
	public Book getBookById(int id);
	public void updateBook(Book book) throws DBConstrainsException;
	void updateBookAvailability(int bookId, boolean isAvailable);
	
	public Map<String, Long> getBooksByCategory();
}
