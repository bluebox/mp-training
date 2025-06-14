package com.libraryManagement.services.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.libraryManagement.dao.impl.BookDaoImpl;
import com.libraryManagement.services.BookService;
import com.libraryManagement.utility.DBConnection;
import com.libraryManagement.utility.pojos.Book;

public class BookServiceImpl implements BookService {
	private BookDaoImpl bookDaoImpl = new BookDaoImpl();

	@Override
	public boolean addBookService(Book book) throws ClassNotFoundException, IOException, SQLException {
		if (book == null || book.getTitle() == null || book.getTitle().isEmpty() || book.getAuthor() == null
				|| book.getAuthor().isEmpty()) {
			return false;
		}
		if (bookDaoImpl.verifyBook(book)) {
			return bookDaoImpl.addBook(book);
		}
		return false;
	}

	@Override
	public boolean updateBookService(Book book) throws ClassNotFoundException, IOException, SQLException {
		Connection conn = DBConnection.getConnection();
		try {
			conn.setAutoCommit(false);
			if (!bookDaoImpl.addBookLog(book))
				return false;
			if (!bookDaoImpl.updateBook(book))
				return false;
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			conn.setAutoCommit(true);
		}
		return true;
	}

	@Override
	public boolean updateBookAvailabilityService(Book book, char ch)
			throws ClassNotFoundException, IOException, SQLException {
		Connection conn = DBConnection.getConnection();
		try {
			conn.setAutoCommit(false);
			if (!bookDaoImpl.addBookLog(book))
				return false;
			if (!bookDaoImpl.updateBookAvailability(book, ch))
				return false;
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			conn.setAutoCommit(true);
		}
		return false;
	}

	public List<Book> viewAllBooks() {
		return bookDaoImpl.getAllBooks();
	}
}
