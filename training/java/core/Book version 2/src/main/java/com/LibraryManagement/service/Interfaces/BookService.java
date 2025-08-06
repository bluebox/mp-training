package com.LibraryManagement.service.Interfaces;

import java.util.List;

import com.LibraryManagement.models.Book;

public interface BookService {

	public int addBook(Book book);

	public void updateBook(Book book);

	public void updateAvailability(Book book);

	public List<Book> getAllBooks();

	public Book getBookById(int bookId) throws Exception;
}
