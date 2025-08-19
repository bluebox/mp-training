package com.lms.lms_backend.service;

import java.util.List;

import com.lms.lms_backend.model.Book;

public interface BookService {

    void addBook(Book book);

    List<Book> getAllBooks();

    void updateBookDetails(int id, Book newBook);

    void deleteBook(int id);

    List<String> getAllCategories();
}
