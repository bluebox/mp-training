package com.LibraryManagement.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.LibraryManagement.exceptions.DataBaseException;
import com.LibraryManagement.utilites.pojos.Book;

public interface BookDao {
	public boolean addBook(Book book) throws SQLException, DataBaseException;
	public boolean verifyBook(int bookId, Character availability) throws Exception;
	public boolean verifyBook(Book book) throws Exception;
	public boolean updateBook(int bookId, Character availability) throws Exception;
	public ArrayList<Book> viewAllBooks() throws Exception;
	public ArrayList<Book> viewAllBooksLogs() throws Exception;
}
