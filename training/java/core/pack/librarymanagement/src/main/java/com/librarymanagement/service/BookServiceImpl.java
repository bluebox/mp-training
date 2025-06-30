package com.librarymanagement.service;
import com.librarymanagement.dao.*;
import com.librarymanagement.exceptions.NoBookException;
import com.librarymanagement.model.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.librarymanagement.util.*;
import java.sql.*;
@Component
public class BookServiceImpl implements BookService {
	private final BookDAO bookDAO;  
	@Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    @Override
    public void addBook(Book book) throws Exception {
        if (book == null || book.getTitle() == null || book.getAuthor() == null || book.getCategory()==null) {
            throw new IllegalArgumentException("Book title  author and category cannot be null");
        }
        Connection conn = null;
        try {
            conn = (Connection) DBConnection.getConnection();
            ((java.sql.Connection) conn).setAutoCommit(false);
            bookDAO.addBook(book);
            ((java.sql.Connection) conn).commit();
        } catch (Exception e) {
            if (conn != null) ((java.sql.Connection) conn).rollback();
            throw e;
        } finally {
            if (conn != null) {
                ((java.sql.Connection) conn).setAutoCommit(true);
                conn.close();
            }
        }
       // bookDAO.addBook(book);
    }

    @Override
    public void updateBook(Book book) throws Exception {
        if (book == null || book.getBookId() <= 0) {
            throw new IllegalArgumentException("Invalid book details for update");
        }
        bookDAO.updateBook(book);
    }

    @Override
    public void updateBookAvailability(int bookId, char availability) throws Exception {
        if (bookId <= 0 || (availability != 'A' && availability != 'I')) {
            throw new IllegalArgumentException("Invalid availability or book ID");
        }
        getBookById(bookId);
        bookDAO.updateBookAvailability(bookId, availability);
    }

    @Override
    public List<Book> getAllBooks() throws Exception {
        return bookDAO.getAllBooks();
    }

    @Override
    public Book getBookById(int bookId) throws Exception {
        if (bookId <= 0) {
            throw new IllegalArgumentException("Invalid book ID");
        }
        Book b1= bookDAO.getBookById(bookId);
        if(b1==null)
        	throw new NoBookException("Book not found");
        return b1;
    }
    
}
