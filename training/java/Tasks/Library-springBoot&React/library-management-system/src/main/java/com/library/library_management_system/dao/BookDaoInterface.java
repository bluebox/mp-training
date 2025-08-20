package com.library.library_management_system.dao;

import java.util.List;

import com.library.library_management_system.domain.Book;

public interface BookDaoInterface {
	
	Book addBook(Book book);

    Book updateBook(Book book);

    int updateBookAvailability(int bookId, String availability);

    int deleteBook(int bookId);

    List<Book> getAllBooks();

    Book getBookById(int id);

    List<Book> getBooksByMember(int memberId);
}
