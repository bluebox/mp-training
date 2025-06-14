package com.libraryManagement.dao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.libraryManagement.utility.pojos.Book;

public interface BookDao {
    public boolean addBook(Book book);
    public boolean updateBook(Book book);
    public boolean verifyBook(Book book) throws IOException, SQLException, ClassNotFoundException;
    public boolean addBookLog(Book book);
	public boolean updateBookAvailability(Book book, char ch);
	public List<Book> getAllBooks();
}
