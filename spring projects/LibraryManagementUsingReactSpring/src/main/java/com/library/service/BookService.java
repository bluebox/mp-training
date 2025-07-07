package com.library.service;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.library.domain.Book;

public interface BookService {
    boolean createBook(Book book);
    boolean updateBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(int bookId);
    void toggleAvailability(int bookId);
    public Map<String, Long> getBooksCountPerCategory();
}
