package com.lms.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.lms.daoImpl.BookDao;
import com.lms.exceptions.InvalidInputException;
import com.lms.model.Book;
import com.lms.service.BookService;
import com.lms.util.Validator;
public class BookServiceImpl implements BookService {
	public Book getBookById(String bookId) throws InvalidInputException {
		Validator.serviceValidateBookId(bookId);
		Book currentBook;
		try {
			currentBook = BookDao.getBookById(bookId);
		} catch (SQLException e) {
			throw new InvalidInputException("Error connecting to server.");
		}
		if (currentBook == null) {
			throw new InvalidInputException("Book not found with ID: " + bookId);
		}
		return currentBook;
	}
	
	public List<Book> getAllBooks() throws SQLException {
		return BookDao.getBooks();
	}

	
	public void addBook(Book newBook) throws InvalidInputException {
	    Validator.serviceValidateBook(newBook);


	    Book existing=null;
		try {
			existing = BookDao.getBookByTitle(newBook.getBookTitle());
		} catch (SQLException e) {
			throw new InvalidInputException("Error connecting server");
		}
	    if (existing != null) {
	        throw new InvalidInputException("A book with this title already exists.");
	    }

	    try {
	        BookDao.addBook(newBook);
	    } catch (SQLException e) {
	        throw new InvalidInputException("Our server is currently down. Please try again later.");
	    }
	}

	
	public Boolean updateBook(Book updateBook) throws InvalidInputException {
	    Validator.serviceValidateBook(updateBook);

	    Book existing = getBookById(updateBook.getBookId());

	    if (!existing.getBookTitle().equalsIgnoreCase(updateBook.getBookTitle())) {
	        Book duplicateBook;
			try {
				duplicateBook = BookDao.getBookByTitle(updateBook.getBookTitle());
			} catch (SQLException e) {
				throw new InvalidInputException("Error connecting server");
			}

	        if (duplicateBook != null && !duplicateBook.getBookId().equals(updateBook.getBookId())) {
	            throw new InvalidInputException("Another book with this title already exists.");
	        }
	    }

	    try {
	        return BookDao.updateBook(updateBook);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new InvalidInputException("Our server is currently down. Please try again later.");
	    }
	

	}


	
    
}