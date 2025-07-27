package com.library.dao.interfaces;

import java.util.List;

import com.library.model.Book;

public interface BookDao {
	public void addBook(Book book) throws Exception;
	public List<Book> getAllBooks();
	public void updateBookDetails(Book book) throws Exception;
    public void updateBookAvailability(int bookId, char availability) throws Exception;
    public List<Book> getBooks(String columnName, String value) throws Exception;
    public Book getBookById(int bookId) throws Exception;
}
