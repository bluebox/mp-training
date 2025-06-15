package com.library.service;

import com.library.dao.BookDAO;
import com.library.domain.Book;

import java.util.List;

public class IssuedBookService {

    private final BookDAO bookDAO;

    public IssuedBookService() {
        this.bookDAO = new BookDAO();
    }

    public List<Book> getAllIssuedBooks() {
        return bookDAO.getBooksByAvailability("A");
    }
}
