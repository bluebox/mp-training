package com.LibraryManagement.DAO;

import java.util.List;
import com.LibraryManagement.models.Book;

public interface BookDAO {

	 public void addBook(Book book); 
	 public void updateBook(Book book);
	 public void updateAvailability(Book book);
	 public List<Book>getAllBooks();
	 
}
