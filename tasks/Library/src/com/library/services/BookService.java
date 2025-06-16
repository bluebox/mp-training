package com.library.services;

import com.library.domain.Book;
import com.library.dao.BookDAO;
import com.library.util.DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private BookDAO bookDAO = new BookDAO();

    
    public boolean addBook(Book book) {

        return bookDAO.addBook(book);
    }
    
    
    
    public boolean updateBookAvailability(Book book,char status) throws IOException {
    	
    	try (Connection conn = DB.getConnection()){
			bookDAO.updateAvailability(conn, book.getBookId(), status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return bookDAO.addBook(book);
    }

    
    
    
    public List<Book> getAllBooks() {

        List<Book> books = new ArrayList<>();
        books= bookDAO.getAllBooks();
        return books;
    }


}