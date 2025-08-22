package com.LibraryManagement.DAO.Implementation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LibraryManagement.DAO.BookDAO;
import com.LibraryManagement.exception.BookDAOException;
import com.LibraryManagement.models.Book;
import util.DatabaseConnection;

public class BookDAOImplementation implements BookDAO {

	@Override
	public void addBook(Book book) {

		String insertBook = "INSERT INTO books(title, author,category,status,availability) VALUES (?,?,?,?,?)";
		final String DEFAULT_STATUS = "A";

		try (Connection conn = DatabaseConnection.getConnection()) {
			conn.setAutoCommit(false);

			try {
				PreparedStatement insertStatement = conn.prepareStatement(insertBook);
				insertStatement.setString(1, book.getTitle());
				insertStatement.setString(2, book.getAuthor());
				insertStatement.setString(3, book.getCategory());
				insertStatement.setString(4, DEFAULT_STATUS);
				insertStatement.setString(5, DEFAULT_STATUS);

				insertStatement.execute();
				conn.commit();
			} catch (SQLException e) {
				conn.rollback();
				throw new BookDAOException("Failed to insert book", e);
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (SQLException | IOException e) {
			throw new BookDAOException("Failed to insert book", e);
		}

	}

	@Override
	public void updateBook(Book book) {

		String updateBook = "UPDATE books SET title=?, author=?, category=? WHERE BookId=?";
		String updateBook_log = "INSERT INTO books_log(bookId,title,author,category,status,availability,operation_type) VALUES (?,?,?,?,?,?,?)";

		try (Connection conn = DatabaseConnection.getConnection()) {
			conn.setAutoCommit(false);
			try {
				PreparedStatement updateStatementLog = conn.prepareStatement(updateBook_log);
				updateStatementLog.setInt(1, book.getBookId());
				updateStatementLog.setString(2, book.getTitle());
				updateStatementLog.setString(3, book.getAuthor());
				updateStatementLog.setString(4, book.getCategory());
				updateStatementLog.setString(5, String.valueOf(book.getStatus()));
				updateStatementLog.setString(6, String.valueOf(book.getAvailability()));
				updateStatementLog.setString(7, "Update");

				updateStatementLog.execute();

				PreparedStatement updateStatement = conn.prepareStatement(updateBook);
				updateStatement.setString(1, book.getTitle());
				updateStatement.setString(2, book.getAuthor());
				updateStatement.setString(3, book.getCategory());
				updateStatement.setInt(4, book.getBookId());

				updateStatement.execute();
				conn.commit();
			} catch (SQLException e) {
				conn.rollback();
				throw new BookDAOException("Failed to update book", e);
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (SQLException | IOException e) {
			throw new BookDAOException("Failed to update book", e);
		}
	}

	@Override
	public void updateAvailability(Book book) {

		String updateBookAvailability = "UPDATE books SET Availability=? WHERE BookId=?";
		String updateBook_log = "INSERT INTO books_log(bookId,title,author,category,status,availability,operation_type) VALUES ( ?, ?,?,?,?,?,?)";

		try (Connection conn = DatabaseConnection.getConnection()) {
			conn.setAutoCommit(false);
			try {
				PreparedStatement updateBookAvailabilityStatement = conn.prepareStatement(updateBookAvailability);
				updateBookAvailabilityStatement.setString(1, String.valueOf(book.getAvailability()));
				updateBookAvailabilityStatement.setInt(2, book.getBookId());
				updateBookAvailabilityStatement.execute();

				PreparedStatement updateBook_logStatement = conn.prepareStatement(updateBook_log);
				updateBook_logStatement.setInt(1, book.getBookId());
				updateBook_logStatement.setString(2, book.getTitle());
				updateBook_logStatement.setString(3, book.getAuthor());
				updateBook_logStatement.setString(4, book.getCategory());
				updateBook_logStatement.setString(5, String.valueOf(book.getStatus()));
				updateBook_logStatement.setString(6, String.valueOf(book.getAvailability()));
				updateBook_logStatement.setString(7, "Updated_availability");
				updateBook_logStatement.execute();
				conn.commit();
			} catch (SQLException e) {
				conn.rollback();
				throw new BookDAOException("Failed to update availability", e);
			}
		} catch (SQLException | IOException e) {
			throw new BookDAOException("Failed to update availability", e);
		}
	}

	@Override
	public List<Book> getAllBooks() {

		List<Book> books = new ArrayList<>();
		String querey = "SELECT * FROM books";

		try (Connection conn = DatabaseConnection.getConnection()) {
			conn.setAutoCommit(false);
			try {
				PreparedStatement ps = conn.prepareStatement(querey);
				ResultSet rs = ps.executeQuery();
				conn.commit();
				while (rs.next()) {
					int id = rs.getInt("bookId");
					String title = rs.getString("title");
					String author = rs.getString("author");
					String categoryy = rs.getString("category");
					char statuss = rs.getString("status").charAt(0);
					char availability = rs.getString("availability").charAt(0);
					books.add(new Book(id, title, author, categoryy, String.valueOf(statuss),
							String.valueOf(availability)));
				}
			} catch (SQLException e) {
				conn.rollback();
				throw new BookDAOException("Failed ", e);
			}

		} catch (SQLException | IOException e) {
			throw new BookDAOException("Failed ", e);
		}

		return books;
	}

	public void updateStatus(Book book) {
		String updateBookAvailability = "UPDATE books SET status=? WHERE BookId=?";
		String updateBook_log = "INSERT INTO books_log(bookId,title,author,category,status,availability,operation_type) VALUES ( ?, ?,?,?,?,?,?)";

		try (Connection conn = DatabaseConnection.getConnection()) {
			conn.setAutoCommit(false);
			try {
				PreparedStatement updateBookStatusStatement = conn.prepareStatement(updateBookAvailability);
				updateBookStatusStatement.setString(1, String.valueOf(book.getAvailability()));
				updateBookStatusStatement.setInt(2, book.getBookId());
				updateBookStatusStatement.execute();

				PreparedStatement updateBook_logStatement = conn.prepareStatement(updateBook_log);
				updateBook_logStatement.setInt(1, book.getBookId());
				updateBook_logStatement.setString(2, book.getTitle());
				updateBook_logStatement.setString(3, book.getAuthor());
				updateBook_logStatement.setString(4, book.getCategory());
				updateBook_logStatement.setString(5, String.valueOf(book.getStatus()));
				updateBook_logStatement.setString(6, String.valueOf(book.getAvailability()));
				updateBook_logStatement.setString(7, "Updated_Status");
				updateBook_logStatement.execute();
				conn.commit();
			} catch (SQLException e) {
				conn.rollback();
				throw new BookDAOException("Failed to update status", e);
			}
		} catch (SQLException | IOException e) {
			throw new BookDAOException("Failed to update status", e);
		}
	}

}
