package com.library_management.services;

import java.util.List;

import com.library_management.domain.Book;
import com.library_management.domain.BookAvailability;
import com.library_management.domain.BookCategory;
import com.library_management.domain.BookStatus;
import com.library_management.exceptions.DatabaseException;
import com.library_management.exceptions.InvalidDetailsException;

public interface BookServiceInterface {
	void validateAddBook (String title, String author, BookCategory category) throws InvalidDetailsException, DatabaseException;
	
	void validateUpdateBookDetails(int id, String title, String author, BookCategory category, BookStatus status) throws InvalidDetailsException, DatabaseException;
	
	List<Book> validateViewAllBooks() throws DatabaseException;
}
