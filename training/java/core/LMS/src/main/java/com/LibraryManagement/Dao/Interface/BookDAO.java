package com.LibraryManagement.Dao.Interface;

import java.util.List;
import com.LibraryManagement.Models.Book;

public interface BookDAO {

	 public int addBook(Book book); 
	 public void updateBook(Book book);
	 public void updateAvailability(Book book);
	 public List<Book>getAllBooks();
	 
}
