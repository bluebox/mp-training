package com.library.service.impl;

import com.library.dao.impl.BookDaoImplementation;
import com.library.exception.BookAlreadyExistsException;
import com.library.exception.BookNotFoundException;
import com.library.model.Book;
import com.library.service.interfaces.BookService;
import com.library.util.DBConnection;

import java.sql.Connection;
import java.util.List;

public class BookServiceImplementation implements BookService {

    private final BookDaoImplementation dao = new BookDaoImplementation();

    @Override
    public void addBook(Book book) throws Exception {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            
            Book existing = dao.getBookByTitleAuthorCategory(book.getTitle(), book.getAuthor(), book.getCategory());
            if (existing != null) {
                throw new BookAlreadyExistsException("Book already exists with same title, author, and category.");
            }

            dao.addBook(conn, book); // ✅ Add only if not duplicate

            conn.commit();
        } catch (Exception e) {
            if (conn != null) conn.rollback(); 
            throw new Exception("Failed to add book: " + e.getMessage(), e);
        } finally {
            if (conn != null) conn.setAutoCommit(true); 
        }
    }


    @Override
    public List<Book> getAllBooks() {
        return dao.getAllBooks();
    }

    @Override
    public Book getBookById(int id) throws Exception {
        return dao.getBookById(id);
    }

    @Override
    public void updateBookDetails(Book updatedBook) throws Exception {
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
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) conn.setAutoCommit(true); 
        }
    }

    @Override
    public void updateAvailability(int bookId, char availability) throws Exception {
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
            if (conn != null) conn.rollback();
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
