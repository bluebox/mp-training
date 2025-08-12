package com.libraryManagementSystem.services.impl;

import java.util.List;

import com.libraryManagementSystem.dao.BookDao;
import com.libraryManagementSystem.dao.impl.BookDaoImpl;
import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.exceptions.BookNotFoundException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.DuplicateBookException;
import com.libraryManagementSystem.exceptions.InvalidBookDataException;
import com.libraryManagementSystem.services.BookServices;
import com.libraryManagementSystem.utilities.BookAvailability;
import com.libraryManagementSystem.utilities.Validations;

public class BookServicesImpl implements BookServices {

	private final BookDao bookDao = new BookDaoImpl();

	@Override
	public void addBook(Book book) throws InvalidBookDataException, DuplicateBookException, DatabaseOperationException {

		validateBookData(book, null);

		try {
			bookDao.addBook(book);
		} catch (DatabaseOperationException e) {
			throw new DatabaseOperationException(e.getMessage());
		}
	}

	@Override
	public List<Book> getBooks() throws DatabaseOperationException {

		try {
			return bookDao.getAllBooks();
		} catch (DatabaseOperationException e) {
			throw new DatabaseOperationException(e.getMessage());
		}

	}

	@Override
	public void deleteBook(Book book)
			throws BookNotFoundException, DatabaseOperationException, InvalidBookDataException {
		try {
			if (book.getAvailability().equals(BookAvailability.ISSUED)) {
				throw new InvalidBookDataException("Book Availability is Issued.Please colluct Book");
			}
			bookDao.deleteBook(book);

		} catch (DatabaseOperationException | BookNotFoundException e) {
			throw new DatabaseOperationException(e.getMessage());
		}

	}

	@Override
	public void updateBook(Book book, Book oldBook)
			throws BookNotFoundException, InvalidBookDataException, DatabaseOperationException, DuplicateBookException {

		validateBookData(book, oldBook);

		if (book.getAvailability() == null) {
			throw new InvalidBookDataException("Please select availability.");
		}

		if (bookDao.getBookById(book.getBookId()) == null) {
			throw new BookNotFoundException("No book found with ID " + book.getBookId());
		}

		if (book.equals(oldBook)) {
			throw new InvalidBookDataException("Please edit at least one field");
		}

		if (bookDao.existsByTitleAndAuthorExceptId(book.getTitle(), book.getAuthor(), book.getBookId())) {
			throw new DuplicateBookException(
					"Book with title '" + book.getTitle() + "' and author '" + book.getAuthor() + "' already exists.");
		}

		try {

			bookDao.updateBook(book, oldBook);

		} catch (DatabaseOperationException | BookNotFoundException e) {
			throw new DatabaseOperationException(e.getMessage());
		}

	}

	@Override
	public void updateBookAvailability(Book book, BookAvailability avail)
			throws BookNotFoundException, DatabaseOperationException {
		try {

			bookDao.updateBookAvailability(book, avail);

		} catch (DatabaseOperationException | BookNotFoundException e) {
			throw new DatabaseOperationException(e.getMessage());
		}

	}

	private void validateBookData(Book book, Book oldbook)
			throws InvalidBookDataException, DatabaseOperationException, DuplicateBookException {

		if (!Validations.isValidTitle(book.getTitle()) || book.getTitle().trim().length() < 3) {
			throw new InvalidBookDataException("minimum 3 letters in the title.");
		}
		if (!Validations.isValidName(book.getAuthor()) || book.getAuthor().trim().length() < 3) {
			throw new InvalidBookDataException("3 letters for the author's name.");
		}
		if (book.getCategory() == null) {
			throw new InvalidBookDataException("Please select a category.");
		}
		if (oldbook != null) {
			if (!(oldbook.getTitle().equals(book.getTitle()) || oldbook.getAuthor().equals(book.getAuthor()))) {
				throw new DuplicateBookException("This book already exists.");
			}
		} else if (bookDao.existsByTitleAndAuthor(book.getTitle(), book.getAuthor())) {
			throw new DuplicateBookException("This book already exists.");
		}
	}
}
