package com.lms.springbootlms.service;

import java.util.List;

import com.lms.springbootlms.exception.InvalidInputException;
import com.lms.springbootlms.model.Book;

public interface BookService {
    Book getBookById(String bookId) throws InvalidInputException;

    List<Book> getAllBooks() throws InvalidInputException;

    void addBook(Book newBook) throws InvalidInputException;

    Boolean updateBook(Book updateBook) throws InvalidInputException;

	List<Book> getAvailableBooks() throws InvalidInputException;
}
