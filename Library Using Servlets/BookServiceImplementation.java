package com.library.service;

import java.util.List;

import com.library.Exception.BookDAOException;
import com.library.dao.BookDAO;
import com.library.dao.BookDAOImplementation;
import com.library.model.Book;

public class BookServiceImplementation implements BookService {

    private final BookDAO bookDAO;

    public BookServiceImplementation(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public int addBook(Book book) {
        if (book == null || book.getTitle() == null || book.getAuthor() == null || book.getCategory() == null) {
            throw new BookDAOException("Invalid book data: Title, Author, and Category must not be null.");
        }
        return bookDAO.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        if (book == null || book.getBookId() <= 0) {
            throw new BookDAOException("Invalid book ID for update.");
        }
        bookDAO.updateBook(book);
    }

    @Override
    public void updateAvailability(Book book) {
        if (book == null || book.getBookId() <= 0) {
            throw new BookDAOException("Invalid book ID for availability update.");
        }
        bookDAO.updateAvailability(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    public Book getBookById(int bookId) throws Exception {
        return bookDAO.getBookById(bookId);
    }
}
