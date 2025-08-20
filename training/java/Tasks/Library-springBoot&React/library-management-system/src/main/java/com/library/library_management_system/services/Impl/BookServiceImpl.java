package com.library.library_management_system.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.library_management_system.dao.BookDaoInterface;
import com.library.library_management_system.domain.Book;
import com.library.library_management_system.exceptions.ApiException;
import com.library.library_management_system.services.BookServiceInterface;

@Service
public class BookServiceImpl implements BookServiceInterface {

	@Autowired
	private BookDaoInterface bookDao;

	@Override
	public Book addBook(Book book) {
		try {
			Book newBook = bookDao.addBook(book);
			if (newBook == null) {
				throw new ApiException("Error adding book: ");
			}
			return newBook;
		} catch (Exception e) {
			throw new ApiException("Error adding member: " + e.getMessage());
		}

	}

	@Override
	public Book updateBook(int id, Book book) {
		Book existingBook = bookDao.getBookById(id);
		if (existingBook == null) {
			throw new ApiException("Book not found with ID: " + id);
		}
		checkAtLeastOneFieldChanged(existingBook, book);
		try {
//			return bookDao.updateBook(book);
			book.setBookId(id);
			Book updatedBook = bookDao.updateBook(book);
			if (updatedBook == null) {
				throw new ApiException("Failed to update book");
			}
			return updatedBook;
		} catch (Exception e) {
			throw new ApiException("Error updating book: " + e.getMessage());
		}
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> books = bookDao.getAllBooks();
		if (books == null || books.isEmpty()) {
			throw new ApiException("No books found in the library");
		}
		return books;
	}

	@Override
	public Book getBookById(int id) {
		Book book = bookDao.getBookById(id);
		if (book == null) {
			throw new ApiException("Book not found with ID: " + id);
		}
		return book;
	}

	@Override
	public void updateBookAvailability(int bookId, String availability) {
		Book book = bookDao.getBookById(bookId);
		if (book == null) {
			throw new ApiException("Book not found with ID: " + bookId);
		}
		try {
			int rows = bookDao.updateBookAvailability(bookId, availability);
			if (rows == 0) {
				throw new ApiException("Failed to update book availability");
			}
		} catch (Exception e) {
			throw new ApiException("Error updating book availability: " + e.getMessage());
		}
	}

	@Override
	public void deleteBook(int bookId) {
		Book book = bookDao.getBookById(bookId);
		if (book == null) {
			throw new ApiException("Book not found with ID: " + bookId);
		}
		try {
			int rows = bookDao.deleteBook(bookId);
			if (rows == 0) {
				throw new ApiException("Failed to delete book");
			}
		} catch (Exception e) {
			throw new ApiException("Error deleting book: " + e.getMessage());
		}
	}

	private void checkAtLeastOneFieldChanged(Book existing, Book updated) {
		if (existing.getTitle().equals(updated.getTitle()) && existing.getAuthor().equals(updated.getAuthor())
				&& existing.getCategory().equals(updated.getCategory())) {
			throw new ApiException("At least one field must be updated");
		}
	}
}
