package com.LMS.LibMS.service.interfaces;

import java.util.List;

import com.LMS.LibMS.model.Book;

public interface BookService {

	void addBook(Book book) throws Exception;

	List<Book> getAllBooks();

	Book findBookById(Integer bookId);

	boolean updateBook(Book book) throws Exception;

	boolean updateAvailabilitiesById(List<Integer> bookIds) throws Exception;

	boolean deleteBooksById(List<Integer> bookIds) throws Exception;

	boolean makeBookInactiveById(Integer bookId) throws Exception;

}