package com.library.service;

import com.library.dao.BookDAO;
import com.library.domain.Book;

import java.util.List;

public class BookService {
    private final BookDAO bookDAO = new BookDAO();

    public List<Book> fetchAllBooks() {
        return bookDAO.getAllBooks();
    }

    public boolean addBook(Book book) {
        return bookDAO.insertBook(book);
    }
}