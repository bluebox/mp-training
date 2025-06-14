package com.libraryManagement.services;

import java.io.IOException;
import java.sql.SQLException;

import com.libraryManagement.utility.pojos.Book;

public interface BookService {

	boolean addBookService(Book book) throws ClassNotFoundException, IOException, SQLException;

	boolean updateBookService(Book book) throws ClassNotFoundException, IOException, SQLException;

	boolean updateBookAvailabilityService(Book book, char ch) throws ClassNotFoundException, IOException, SQLException;

}
