package com.library.library_management_system.repository;

import java.util.List;

import com.library.library_management_system.domain.Book;
import com.library.library_management_system.utils.BookAvailability;

public interface BookRepository {

	public int addBook(Book book);

	public boolean existsByTitleAndAuthor(String title, String author);

	public int updateBook(Book book);

	public boolean existsByTitleAndAuthorExceptId(String title, String author, int excludeId);

	public Book getBookById(int id);

	public int updateBookAvailability(Book book, BookAvailability availability);

	public List<Book> findAll();

	public int deleteBook(Book book);

	public int bookLog(Book book);
}
