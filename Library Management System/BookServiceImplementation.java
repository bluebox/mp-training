package com.LibraryManagement.service.implementation;

import java.util.List;

import com.LibraryManagement.DAO.Implementation.BookDAOImplementation;
import com.LibraryManagement.exception.BookDAOException;
import com.LibraryManagement.models.Book;
import com.LibraryManagement.service.interfaces.BookService;

public class BookServiceImplementation implements BookService {

	BookDAOImplementation bookServices = new BookDAOImplementation();

	@Override
	public void addBook(Book book) {
		if (book == null || book.getTitle() == null || book.getAuthor() == null || book.getCategory() == null) {
			throw new BookDAOException("Invalid book data: Title, Author, and Category must not be null.");
		}
		bookServices.addBook(book);
	}

	@Override
	public void updateBook(Book book) {
		if (book == null || book.getBookId() <= 0) {
			throw new BookDAOException("Invalid book ID for update.");
		}
		bookServices.updateBook(book);
	}

	@Override
	public void updateAvailability(Book book) {
		if (book == null || book.getBookId() <= 0) {
			throw new BookDAOException("Invalid book ID for availability update.");
		}
		bookServices.updateAvailability(book);
	}

	@Override
	public List<Book> getAllBooks() {
		
		return bookServices.getAllBooks();
	}

}
