package com.lms.lms_backend.dao;

import java.util.List;

import com.lms.lms_backend.model.Book;

public interface BookDao {

    int insertBookLog(Book book);

    int addBook(Book book);

    int updateBookDetails(Book oldBook, Book newBook);

    int updateBookAvailability(Book oldBook, String availability);

    int deleteBook(Book oldBook);

    List<Book> selectAllBooks();

    Book selectBookById(int id);

    List<Book> selectAllMemberBooks(int memberId);

    List<String> getAllCategories();
}
