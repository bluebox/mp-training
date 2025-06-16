package com.LibraryManagement.services;

import java.util.ArrayList;

import com.LibraryManagement.utilites.pojos.Book;

public interface BookService {
	public boolean addBookService(Book book) throws Exception;
	public boolean updateBookService(int bookId, Character availability) throws Exception;
	public ArrayList<Book> viewAllBooksService() throws Exception; 
	public ArrayList<Book> viewAllBooksLogService() throws Exception; 
}

