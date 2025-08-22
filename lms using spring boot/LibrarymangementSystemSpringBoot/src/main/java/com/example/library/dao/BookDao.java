package com.example.library.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.library.domain.Book;

public interface BookDao {

	List<Book> getBooksByMember(int memberId) throws DataAccessException;

	Book findById(int id);

	List<Book> findAllBooks();

	int deleteBook(int id);

	int updateAvailability(int bookId, String availability);

	int updateBook(Book book);

	int addBook(Book book);

	int booklog(int bookId);

}
