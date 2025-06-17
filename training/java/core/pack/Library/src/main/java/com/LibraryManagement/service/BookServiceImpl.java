package Library.src.main.java.com.LibraryManagement.service;

import java.util.List;

//import com.sun.jdi.connect.spi.Connection;
import java.sql.*;
import Library.src.main.java.com.LibraryManagement.dao.BookDAO;
import Library.src.main.java.com.LibraryManagement.dao.BookDAOImpl;
import Library.src.main.java.com.LibraryManagement.model.*;
import Library.src.main.java.com.LibraryManagement.util.DBConnection;

public class BookServiceImpl implements BookService {

	private final BookDAO bookDAO;   

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
        return bookDAO.getBookById(bookId);
    }
}