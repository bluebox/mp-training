package com.library.dao;

import java.sql.Connection;

import com.library.domain.Book;

public interface BookDAO{
	public boolean isBookExists(int bookId,Connection conn);
	public boolean isBookAvailable(int bookId,Connection conn);
	public void updateBookAvailability(Connection conn, int bookId);
	public boolean updateDetails(Connection conn, Book book);
	
}