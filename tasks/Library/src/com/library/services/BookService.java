package com.library.services;

import com.library.domain.Book;
import com.library.serviceInterface.BookServiceInterface;
import com.library.dao.BookDAO;
import com.library.dao.IssueRecordDAO;
import com.library.util.DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService implements BookServiceInterface {
	private BookDAO bookDAO = new BookDAO();

	@Override
	public boolean addBook(Book book) {
		Connection conn = DB.getConnection();
		return bookDAO.addBook(conn,book);
	}

	@Override
	public boolean updateBookAvailability(Book book, char status) throws IOException {
		Connection conn = DB.getConnection();
		try  {
			bookDAO.updateAvailability(conn, book.getBookId(), status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookDAO.addBook(conn,book);
	}

	@Override
	public List<Book> getAllBooks() {
		Connection conn = DB.getConnection();
		List<Book> books = new ArrayList<>();
		books = bookDAO.getAllBooks(conn);
		return books;
	}



	@Override
	public void updateBook(Book book) {
		// pavan

	}

	@Override
	public void returnBook(int bookId) throws Exception {
		// TODO Auto-generated method stub
		
	}

}