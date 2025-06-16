package com.LibraryManagement.service;

import java.util.List;
import com.LibraryManagement.dao.BookDAO;
import com.LibraryManagement.model.Book;

public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public void addBook(Book book) throws Exception {
        if (book == null || book.getTitle() == null || book.getAuthor() == null) {
            throw new IllegalArgumentException("Book title and author cannot be null");
        }
        bookDAO.addBook(book);
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