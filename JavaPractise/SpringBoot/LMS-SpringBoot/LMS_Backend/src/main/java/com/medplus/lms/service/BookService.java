package com.medplus.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medplus.lms.dao.BookRepository;
import com.medplus.lms.dao.BookRepositoryInterface;
import com.medplus.lms.domain.Book;
import com.medplus.lms.domain.Status;
import com.medplus.lms.exceptions.ManagementException;
import com.medplus.lms.util.ValidationUtil;

@Service
public class BookService implements BookServiceInterface{

	private final BookRepositoryInterface bookDao;

	public BookService(BookRepository repo) {
		this.bookDao = repo;
	}

	public void addBook(Book book) throws ManagementException {

		Book existingBook = bookDao.findBookByTitleAndAuthorIgnoreCase(book.getTitle().trim(), book.getAuthor().trim());
		if (existingBook != null && existingBook.getStatus() == Status.INACTIVE) {
			existingBook.setStatus(Status.ACTIVE);
			existingBook.setUpdatedBy("Admin");
			bookDao.updateBookStatus(existingBook);
			return;
		}
		if (existingBook != null) {
			throw new ManagementException("Book with same Title name " + book.getTitle() + " and Author name "
					+ book.getAuthor() + "  already exists");
		}
		ValidationUtil.validateBook(book);
		book.setTitle(book.getTitle().trim());
		book.setAuthor(book.getAuthor().trim());
		book.setCreatedBy(book.getCreatedBy().trim());
		bookDao.addBook(book);
	}

	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}

	public void updateBook(Book book) {
		ValidationUtil.validateBookId(book.getBookId());
		Book existingBook = bookDao.findBookById(book.getBookId());
		if (existingBook == null) {
			throw new ManagementException("Book does not exist with given id : " + book.getBookId());
		}
		
		if (existingBook.equalsForUpdate(book)) {
			throw new ManagementException("Please update at least one field to update.");
		}

		ValidationUtil.validateBook(book);
		book.setUpdatedBy("Admin");
		bookDao.updateBook(book);
	}

	public void updateBookAvailability(Book book) {
		ValidationUtil.validateBookId(book.getBookId());
		Book existingBook = bookDao.findBookById(book.getBookId());
		if (existingBook == null) {
			throw new ManagementException("Book not found with ID: " + book.getBookId());
		}
		ValidationUtil.validateRequired("Availability", book.getAvailability());

		if (existingBook.getAvailability() == book.getAvailability()) {
			throw new ManagementException("Availability is already set to " + book.getAvailability());
		}
		book.setUpdatedBy("Admin");
		
		bookDao.updateBookAvailability(book);
	}

	@Transactional
	public void deleteBook(int bookId) throws ManagementException {
		ValidationUtil.validateBookId(bookId);
		Book book = bookDao.findBookById(bookId);
		if (book == null) {
			throw new ManagementException("Book not found with ID: " + bookId);
		}

		if (bookDao.isBookIssued(bookId)) {
			throw new ManagementException("Book is already issued. It cannot be made inactive.");
		}
		book.setBookId(bookId);
		book.setStatus(Status.INACTIVE);
		book.setUpdatedBy("Admin");
		bookDao.updateBookStatus(book);
	}

	public Book getBookById(int bookId) {
		return bookDao.findBookById(bookId);
	}

}
