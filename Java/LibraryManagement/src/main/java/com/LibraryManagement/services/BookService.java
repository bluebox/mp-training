package com.LibraryManagement.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.LibraryManagement.utilites.pojos.Book;

public interface BookService {
	public boolean addBookService(Book book) throws Exception;
	public boolean updateBookService(int bookId, Character availability) throws ClassNotFoundException, IOException, SQLException, Exception;
	public ArrayList<Book> viewAllBooksService() throws Exception; 
	public ArrayList<Book> viewAllBooksLogService() throws Exception; 
}