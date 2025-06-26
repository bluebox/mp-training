package com.casestudy.dao.models;

import java.sql.SQLException;
import java.util.List;

import com.casestudy.domain.Book;

public interface BooksDaoModel {
	
	public abstract void createBook(Book book) throws SQLException;
	
	public abstract void updateBookAvailability(int bookId);
	
	public abstract boolean updateBook(Book book);
	
	public abstract List<Book> viewAllBooks();
	
	public abstract boolean CanBeIssued(int bookId) ;
	
	public abstract Book searchBook(int bookId);
	
}
