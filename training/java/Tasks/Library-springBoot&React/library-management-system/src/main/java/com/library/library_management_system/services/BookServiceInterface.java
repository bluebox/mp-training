package com.library.library_management_system.services;

import java.util.List;

import com.library.library_management_system.domain.Book;

public interface BookServiceInterface {

	Book addBook(Book book);

    Book updateBook(int id, Book book);

    List<Book> getAllBooks();

    Book getBookById(int id);

    void updateBookAvailability(int bookId, String availability);

    void deleteBook(int bookId);
}
