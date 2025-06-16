package com.services;

import com.models.Book;
import com.DAO.BookDAO;

public class BookService {
    private final BookDAO dao = new BookDAO();

    public boolean addBook(String title, String author, String category, char status, char availability) {
        Book book = new Book(title, author, category, status, availability);
        return dao.save(book);
    }
    public boolean updateBook(int id, String title, String author, String category, char status, char availability) {
        Book book = new Book(id, title, author, category, status, availability);
        return dao.update(book);
    }
    public boolean returnBook(int bookId) {
        return dao.returnBook(bookId);
    }
}
