package com.libraryManagementSystem.services.impl;

import java.util.List;

import com.libraryManagementSystem.dao.BookDao;
import com.libraryManagementSystem.dao.impl.BookDaoImpl;
import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.domain.CustomActiveIssuedBooks;
import com.libraryManagementSystem.domain.CustomCategoryCount;
import com.libraryManagementSystem.domain.CustomOverDueBooks;
import com.libraryManagementSystem.exceptions.InvalidException;
import com.libraryManagementSystem.services.BookServices;
import com.libraryManagementSystem.utilities.BookAvailability;
import com.libraryManagementSystem.utilities.Validations;

public class BookServicesImpl implements BookServices {

	private final BookDao bookDao = new BookDaoImpl();

	@Override
	public void addBook(Book book) throws InvalidException {

		if (!Validations.isValidString(book.getTitle())) {
			throw new InvalidException("Enter Valid Title");
		}

		if (!Validations.isValidString(book.getAuthor())) {
			throw new InvalidException("Enter valid Author Name");
		}

		if (book.getCategory() == null) {
			throw new InvalidException("Please Select Category Field!");
		}

		if (book.getStatus() == null) {
			throw new InvalidException("Please Select Status Field!");
		}

		if (book.getAvailability() == null) {
			throw new InvalidException("Please Select Availability Field!");
		}

		if (bookDao.getBookByTitleAndAuthor(book.getTitle(), book.getAuthor())) {

			throw new InvalidException("Book Already Exit");
		}

		bookDao.addBook(book);

	}

	@Override
	public List<Book> getBooks() throws InvalidException {

		return bookDao.getAllBooks();

	}

	@Override
	public void deleteBook(Book book) throws InvalidException {

		bookDao.deleteBook(book);
	}

	@Override
	public void updateBook(Book book, Book oldBook) throws InvalidException {

		if (!Validations.isValidString(book.getTitle())) {
			throw new InvalidException("Enter Valid Title");
		}

		if (!Validations.isValidString(book.getAuthor())) {
			throw new InvalidException("Enter valid Author Name");
		}

		if (book.getCategory() == null) {
			throw new InvalidException("Please Select Category Field!");
		}

		if (book.getStatus() == null) {
			throw new InvalidException("Please Select Status Field!");
		}

		if (book.getAvailability() == null) {
			throw new InvalidException("Please Select Availability Field!");
		}

		bookDao.updateBook(book, oldBook);
	}

	@Override
	public void updateBookAvailability(Book book, BookAvailability avail) throws InvalidException {

		bookDao.updateBookAvalability(book, avail);

	}

	@Override
	public List<CustomCategoryCount> getBookCountByCategory() throws InvalidException {

		return bookDao.getBookCountByCategory();
	}

	@Override
	public List<CustomActiveIssuedBooks> getActiveIssuedBooks() throws InvalidException {

		return bookDao.getActiveIssuedBooks();
	}

	@Override
	public List<CustomOverDueBooks> getOverDueBooks() throws InvalidException {

		return bookDao.getOverDueBooks();
	}

}
