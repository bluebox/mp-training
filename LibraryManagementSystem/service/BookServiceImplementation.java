package com.library.service.impl;

import com.library.dao.impl.BookDaoImplementation;
import com.library.exception.BookNotFoundException;
import com.library.exception.DuplicateBookException;
import com.library.model.Book;
import com.library.service.interfaces.BookService;
import com.library.util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookServiceImplementation implements BookService {

    private final BookDaoImplementation dao = new BookDaoImplementation();

    @Override
    public void addBook(Book book) throws DuplicateBookException, SQLException {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            dao.addBook(conn, book);

            conn.commit();
        } catch (Exception e) {
            if (conn != null)
            	conn.rollback(); 
            throw new DuplicateBookException(e.getMessage());
        } finally {
            if (conn != null) conn.setAutoCommit(true); 
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return dao.getAllBooks();
    }

    @Override
    public Book getBookById(int id) throws BookNotFoundException,Exception {
    	Book book=dao.getBookById(id);
    	if (book == null) {
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
        return book;
        
        
        
    }

    @Override
    public void updateBookDetails(Book updatedBook) throws BookNotFoundException,Exception {
        if (updatedBook == null || updatedBook.getBookId() <= 0) {
            throw new IllegalArgumentException("Invalid Book ID.");
        }

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            Book existing = dao.getBookById(updatedBook.getBookId());
            if (existing == null) throw new BookNotFoundException("Book not found.");

            updatedBook.setTitle(updatedBook.getTitle() != null ? updatedBook.getTitle() : existing.getTitle());
            updatedBook.setAuthor(updatedBook.getAuthor() != null ? updatedBook.getAuthor() : existing.getAuthor());
            updatedBook.setCategory(updatedBook.getCategory() != null ? updatedBook.getCategory() : existing.getCategory());
            updatedBook.setStatus(updatedBook.getStatus() != '\u0000' ? updatedBook.getStatus() : existing.getStatus());
            updatedBook.setAvailability(existing.getAvailability());

            dao.updateBook(conn, updatedBook);
            conn.commit();
        } catch (Exception e) {
            if (conn != null)
				try {
					conn.rollback();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
            throw e;
        } finally {
            if (conn != null)
				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					
					e.printStackTrace();
				} 
        }
    }

    @Override
    public void updateAvailability(int bookId, char availability) throws BookNotFoundException,SQLException,Exception {
        if (availability != 'A' && availability != 'I') {
            throw new IllegalArgumentException("Availability must be 'A' or 'I'.");
        }

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            Book book = dao.getBookById(bookId);
            if (book == null) throw new BookNotFoundException("Book not found.");

            dao.updateAvailability(conn, bookId, availability);
            conn.commit();
        } catch (Exception e) {
            if (conn != null)
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            throw e;
        } finally {
            if (conn != null) conn.setAutoCommit(true); 
        }
    }

   

  
    public boolean doesBookExist(String title, String author, String category) {
        try {
            return getAllBooks().stream().anyMatch(b ->
                b.getTitle().equalsIgnoreCase(title) &&
                b.getAuthor().equalsIgnoreCase(author) &&
                b.getCategory().equalsIgnoreCase(category));
        } catch (Exception e) {
            return false;
        }
    }

  
    public List<Book> getAvailableBooks() throws Exception {
        return dao.getAvailableBooks();
    }
}
