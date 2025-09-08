package com.medplus.lms.service;

import java.util.List;

import com.medplus.lms.domain.Book;


public interface BookServiceInterface {
	public void addBook(Book book);
	public List<Book> getAllBooks();
	public void updateBook(Book book);
	public void updateBookAvailability(Book book);
	public void deleteBook(int bookId);
	public Book getBookById(int bookId);
}

