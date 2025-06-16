package service;

import java.sql.SQLException;
import java.util.List;
import DAO.BookDAO;
import pojo.Book;
import pojo.Book.Availability;
import service.exceptions.DatabaseException;
import service.exceptions.InvalidInputException;

public class BookService {
    private BookDAO bookDAO= new BookDAO();

    public void addBook(Book book) throws InvalidInputException, DatabaseException {
        if (book == null || book.getTitle() == null || book.getAuthor() == null ||
            book.getCategory() == null || book.getStatus() == null || book.getAvailability() == null) {
            throw new InvalidInputException("Book details cannot be null or empty.");
        }
        try {
            bookDAO.addBook(book);
        } catch (SQLException e) {
            throw new DatabaseException("Error adding book to the database.", e);
        }
    }

    public void updateBookDetails(Book book) throws InvalidInputException, DatabaseException {
        if (book == null || book.getTitle() == null || book.getAuthor() == null ||
        	book.getCategory() == null || book.getStatus() == null) {
            throw new InvalidInputException("Book details for update cannot be null or empty.");
        }
        try {
            bookDAO.updateBookDetails(book);
        } catch (SQLException e) {
            throw new DatabaseException("Error updating book details in the database.", e);
        }
    }

    public void updateBookAvailability(int bookId, Availability availability) throws InvalidInputException, DatabaseException {
        if (bookId <= 0 || availability == null) {
            throw new InvalidInputException("Invalid book ID or availability status.");
        }
        try {
            bookDAO.updateBookAvailability(bookId, availability);
        } catch (SQLException e) {
            throw new DatabaseException("Error updating book availability in the database.", e);
        }
    }

    public List<Book> getAllBooks() throws DatabaseException {
        try {
            return bookDAO.getAllBooks();
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving all books from the database.", e);
        }
    }

    public Book getBookById(int bookId) throws InvalidInputException, DatabaseException {
        if (bookId <= 0) {
            throw new InvalidInputException("Invalid book ID.");
        }
        try {
            return bookDAO.getBookById(bookId);
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving book by ID from the database.", e);
        }
    }
}