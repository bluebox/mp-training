package com.SpringBoot_LMS.SpringBoot_LMS.repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.SpringBoot_LMS.SpringBoot_LMS.model.Book;
import com.SpringBoot_LMS.SpringBoot_LMS.model.BookAvailability;


public interface BookRepositoryInterface {
	 List<Book> getBooks() throws IOException, SQLException;
	  int AddBook(Book book) throws SQLException;
	  Book updateBookDetails(Book book) throws SQLException;
	  Book getBookbyId(int BookId) throws SQLException;
	  Book updateAvailability(int id, BookAvailability availability) throws SQLException;
}
