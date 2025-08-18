package com.lms.service.interfaces.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.model.Book;
import com.lms.repositories.BookRepository;
import com.lms.service.interfaces.BookService;


@Service
public class BookServiceImplementation implements BookService{
	
	private final BookRepository bookRepo;
	
	@Autowired
	public BookServiceImplementation(BookRepository bookRepo) {
		this.bookRepo=bookRepo;
	}
	
	@Override
	public Book addBook(Book book) {
		if (book == null || book.getTitle() == null || book.getAuthor() == null || book.getCategory() == null) {
			throw new RuntimeException("Invalid book data: Title, Author, and Category must not be null.");
		}
		return bookRepo.addBook(book);
	}

	@Override
	public Book updateBook(Book book) {
		if (book == null || book.getBookId() <= 0) {
			throw new RuntimeException("Invalid book ID for update.");
		}
		return bookRepo.updateBook(book);
	}

	@Override
	public Book updateAvailability(Book book) {
		if (book == null || book.getBookId() <= 0) {
			throw new RuntimeException("Invalid book ID for availability update.");
		}
		return bookRepo.updatAvailability(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepo.getAllBooks();
	}

	@Override
	public Optional<Book> getBookById(int bookId) {
		return bookRepo.getBookById(bookId);
	}

}
