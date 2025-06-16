package com.library.service;

import com.library.dao.BookDAO;
import com.library.domain.Book;
import java.util.List;

public class BookService {
    private final BookDAO bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
    }

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    
    private boolean isValidStatusAndAvailability(Book book) {
        return ("A".equals(book.getStatus()) || "I".equals(book.getStatus())) &&
               ("A".equals(book.getAvailability()) || "I".equals(book.getAvailability()));
    }

    protected BookDAO getBookDAO() {
        return new BookDAO();
    }

    public List<Book> fetchAllBooks() {
        return getBookDAO().getAllBooks();
    }

    public boolean addBook(Book book) {
        if (!isValidStatusAndAvailability(book)) {
            return false;
        }
        return getBookDAO().insertBook(book);
    }
    
    public boolean canIssueBook(Book book) {
        return "A".equals(book.getStatus()) && "A".equals(book.getAvailability());
    }
}