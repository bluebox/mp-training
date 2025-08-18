package com.lms.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lms.model.Book;

@Service
public interface BookService {

	public Book addBook(Book book);

	public Book updateBook(Book book);

	public Book updateAvailability(Book book);

	public List<Book> getAllBooks();

	public Optional<Book> getBookById(int bookId);
}
