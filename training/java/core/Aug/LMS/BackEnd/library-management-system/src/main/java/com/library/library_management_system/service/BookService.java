package com.library.library_management_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.library_management_system.domain.Book;
import com.library.library_management_system.exception.DatabaseOperationException;
import com.library.library_management_system.exception.DuplicateBookException;
import com.library.library_management_system.exception.InvalidDataException;
import com.library.library_management_system.repository.BookRepository;
import com.library.library_management_system.utils.BookAvailability;

import jakarta.transaction.Transactional;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public int addBook(Book book) {

		if (book == null) {
			throw new InvalidDataException("Book details are wrong");
		}

		if (bookRepository.existsByTitleAndAuthor(book.getTitle(), book.getAuthor())) {
			throw new DuplicateBookException("Book already exists.");
		}

		int rowsInserted = bookRepository.addBook(book);

		if (rowsInserted <= 0) {
			throw new DatabaseOperationException("Book Not Added Please try again");
		}
		return rowsInserted;
	}

	@Transactional
	public Book updateBook(Book book, Book oldBook) {

		if (book == null || oldBook == null) {
			throw new InvalidDataException("Book details are wrong");
		}

		if (bookRepository.existsByTitleAndAuthorExceptId(book.getTitle(), book.getAuthor(), oldBook.getId())) {
			throw new DuplicateBookException("Book already exists.");
		}

		if (book.equals(oldBook)) {
			throw new InvalidDataException("Please edit at least one field");
		}

		int rowsUpdated = bookRepository.updateBook(book);

		if (rowsUpdated <= 0) {
			throw new DatabaseOperationException("Book Not Updated Please try again");
		}

		bookRepository.bookLog(oldBook);

		return book;

	}

	@Transactional
	public Book updateAvailability(Book book, BookAvailability availability) {

		if (book == null || book.getAvailability().equals(availability)) {
			throw new InvalidDataException("Book details are wrong");
		}

		int rowsUpdated = bookRepository.updateBookAvailability(book, availability);
		if (rowsUpdated <= 0) {
			throw new DatabaseOperationException("Book Not Updated Please try again");
		}

		bookRepository.bookLog(book);
		book.setAvailability(availability);

		return book;

	}

	public Book getBookById(int id) {

		return bookRepository.getBookById(id);

	}

	public List<Book> getBooks() {

		return bookRepository.findAll();
	}

	public int deleteBook(Book book) {

		if (book == null) {
			throw new InvalidDataException("Book details are wrong");
		}

		if (book.getAvailability().equals(BookAvailability.ISSUED)) {
			throw new InvalidDataException("Book Availability is Issued.Please collect Book");
		}

		int rowsEffected = bookRepository.deleteBook(book);

		if (rowsEffected <= 0) {
			throw new DatabaseOperationException("Book Not Deleted Please try again");
		}

		bookRepository.bookLog(book);

		return 1;
	}
}
