package com.library.services;


import com.library.domain.Book;
import com.library.dao.BookDAO;
import java.util.List;

public class BookService {
    private BookDAO bookDAO = new BookDAO();

    public boolean addBook(Book book) {

        return bookDAO.addBook(book);
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }


}