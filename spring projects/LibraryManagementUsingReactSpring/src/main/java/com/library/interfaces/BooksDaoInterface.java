package com.library.interfaces;

import java.util.List;

import com.library.domain.Book;

public interface BooksDaoInterface {

    public boolean createBook(Book book);

    public boolean updateBook(Book book);

    public List<Book> getAllBooks();

    public Book getBookById(int bookId);

    public void toggleAvailability(int bookId);
}
