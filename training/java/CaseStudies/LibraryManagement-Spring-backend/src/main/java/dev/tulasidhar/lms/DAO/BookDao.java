package dev.tulasidhar.lms.DAO;

import java.util.List;

import org.springframework.dao.DataAccessException;

import dev.tulasidhar.lms.model.Book;

public interface BookDao {
	
	int addBook(Book newBook) throws DataAccessException;
	List<Book> getAllBooks() throws DataAccessException;
	
	int updateBook(Book book) throws DataAccessException;
	int updateBookAvailability(int bookId,boolean isavalable) throws DataAccessException;
	public int addBookLogs(Book book) throws DataAccessException;
	Book getBookById(int bookId) throws DataAccessException;
}
