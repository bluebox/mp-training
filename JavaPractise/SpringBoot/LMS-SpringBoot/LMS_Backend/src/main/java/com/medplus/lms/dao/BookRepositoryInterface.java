package com.medplus.lms.dao;

import java.util.List;


import com.medplus.lms.domain.Book;

public interface BookRepositoryInterface {
	public void addBook(Book book);
	public List<Book> getAllBooks();
	public Book findBookById(int bookId);
	public Book findBookByTitleAndAuthorIgnoreCase(String title, String author);
	public void updateBook(Book book);
	public void updateBookAvailability(Book book);
	public boolean isBookIssued(int bookId);
	public void updateBookStatus(Book book);

}






