package com.LMS.LibMS.repository.interfaces;

import java.util.List;

import com.LMS.LibMS.model.Book;

public interface BookRepository {
	
	void addBook(Book book);

	List<Book> getAllBooks();

	List<Book> findBookById(List<Integer> bookIds);

	int updateBook(Book book);

	int updateAvailabilitiesById(List<Integer> bookIds, String updatedBy);

	int deleteBooksById(List<Integer> bookIds);

	int makeBookInactiveById(Integer bookId);

	boolean logBook(List<Book> books);

}