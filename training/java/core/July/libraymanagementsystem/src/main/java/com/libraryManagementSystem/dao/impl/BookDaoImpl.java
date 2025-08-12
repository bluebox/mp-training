package com.libraryManagementSystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.libraryManagementSystem.dao.BookDao;
import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.exceptions.BookNotFoundException;
import com.libraryManagementSystem.exceptions.DatabaseConnectionException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.InvalidEnumValueException;
import com.libraryManagementSystem.exceptions.StatementPreparationException;
import com.libraryManagementSystem.utilities.BookAvailability;
import com.libraryManagementSystem.utilities.BookCategory;
import com.libraryManagementSystem.utilities.BookStatus;
import com.libraryManagementSystem.utilities.DBConnection;
import com.libraryManagementSystem.utilities.PreparedStatementManager;
import com.libraryManagementSystem.utilities.SQLQueries;

public class BookDaoImpl implements BookDao {

	@Override
	public void addBook(Book book) throws DatabaseOperationException {
		try {
			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.BOOK_INSERT);

			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, book.getCategory().getCategory());
			stmt.setString(4, book.getStatus().getDbName());
			stmt.setString(5, book.getAvailability().getDbName());

			int rows = stmt.executeUpdate();
			if (rows == 0) {
				throw new DatabaseOperationException("Book could not be added to the database.");
			}
		} catch (SQLException | DatabaseConnectionException | StatementPreparationException e) {
			throw new DatabaseOperationException("Error while adding book to the database.", e);
		}
	}

	@Override
	public boolean existsByTitleAndAuthor(String title, String author) throws DatabaseOperationException {
		ResultSet rs = null;
		try {
			PreparedStatement stmt = PreparedStatementManager
					.getPreparedStatement(SQLQueries.BOOK_SELECT_BY_TITLE_AUTHOR);
			stmt.setString(1, title.trim().toLowerCase());
			stmt.setString(2, author.trim().toLowerCase());

			rs = stmt.executeQuery();
			return rs.next();
		} catch (SQLException | DatabaseConnectionException | StatementPreparationException e) {
			throw new DatabaseOperationException("Error checking book existence: " + e.getMessage(), e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.err.println("Failed to close ResultSet: " + e.getMessage());
				}
			}
		}
	}

	@Override
	public List<Book> getAllBooks() throws DatabaseOperationException {
		List<Book> books = new ArrayList<>();
		try {
			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.BOOK_SELECT_ALL);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Book book = extractBookFromResultSet(rs);
				books.add(book);
			}

		} catch (SQLException | InvalidEnumValueException | DatabaseConnectionException
				| StatementPreparationException e) {
			throw new DatabaseOperationException("Error retrieving all books: " + e.getMessage(), e);
		}
		return books;
	}

	@Override
	public Book getBookById(int id) throws BookNotFoundException, DatabaseOperationException {
		try {
			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.BOOK_SELECT_BY_ID);
			stmt.setInt(1, id);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {

					return extractBookFromResultSet(rs);

				} else {
					throw new BookNotFoundException("No book found with ID " + id);
				}
			}
		} catch (SQLException e) {
			throw new DatabaseOperationException("SQL error while retrieving book: " + e.getMessage(), e);
		} catch (InvalidEnumValueException | DatabaseConnectionException | StatementPreparationException e) {
			throw new DatabaseOperationException("Invalid enum value in DB: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean existsByTitleAndAuthorExceptId(String title, String author, int excludeId)
			throws DatabaseOperationException {
		try {
			PreparedStatement stmt = PreparedStatementManager
					.getPreparedStatement(SQLQueries.BOOK_EXISTS_BY_TITLE_AUTHOR_EXCEPT_ID);
			stmt.setString(1, title);
			stmt.setString(2, author);
			stmt.setInt(3, excludeId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException | DatabaseConnectionException | StatementPreparationException e) {
			throw new DatabaseOperationException("Error checking for duplicate book (excluding ID): " + e.getMessage(),
					e);
		}
		return false;
	}

	@Override
	public void updateBook(Book book, Book oldBook) throws BookNotFoundException, DatabaseOperationException {
		Connection conn = null;

		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);

			try (PreparedStatement stmt = conn.prepareStatement(SQLQueries.BOOK_UPDATE)) {
				stmt.setString(1, book.getTitle());
				stmt.setString(2, book.getAuthor());
				stmt.setString(3, book.getCategory().getCategory());
				stmt.setString(4, book.getStatus().getDbName());
				stmt.setString(5, book.getAvailability().getDbName());
				stmt.setInt(6, book.getBookId());

				int rowsUpdated = stmt.executeUpdate();

				if (rowsUpdated == 0) {
					conn.rollback();
					throw new BookNotFoundException("No book found with the given ID. Update failed.");
				}
			}

			bookLog(oldBook, conn);

			conn.commit();

		} catch (SQLException | DatabaseConnectionException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException rollbackEx) {
					System.err.println("Rollback failed: " + rollbackEx.getMessage());
				}
			}
			throw new DatabaseOperationException("Failed to update book: " + e.getMessage(), e);
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					System.err.println("Failed to reset auto-commit: " + e.getMessage());
				}
			}
		}
	}

	@Override
	public void updateBookAvailability(Book book, BookAvailability availability)
			throws BookNotFoundException, DatabaseOperationException {

		Connection con = null;

		try {
			con = DBConnection.getConnection();

			try (PreparedStatement stmt = PreparedStatementManager
					.getPreparedStatement(SQLQueries.BOOK_UPDATE_AVAILABILITY)) {
				stmt.setString(1, availability.getDbName());
				stmt.setInt(2, book.getBookId());

				int rowsUpdated = stmt.executeUpdate();
				if (rowsUpdated == 0) {
					con.rollback();
					throw new BookNotFoundException(
							"Book with ID " + book.getBookId() + " not found. Availability not updated.");
				}

				bookLog(book, con);

			} catch (SQLException e) {

				throw e;
			}
		} catch (SQLException | DatabaseConnectionException | StatementPreparationException e) {
			throw new DatabaseOperationException("Error updating book availability: " + e.getMessage(), e);
		}

	}

	@Override
	public void deleteBook(Book book) throws BookNotFoundException, DatabaseOperationException {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);

			try (PreparedStatement stmt = conn.prepareStatement(SQLQueries.BOOK_DELETE)) {
				stmt.setInt(1, book.getBookId());

				int rowsDeleted = stmt.executeUpdate();

				if (rowsDeleted <= 0) {
					conn.rollback();
					throw new BookNotFoundException("No Book found with Title: " + book.getTitle());
				}

				bookLog(book, conn);

				conn.commit();
			}
		} catch (SQLException | DatabaseConnectionException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException rollbackEx) {
					System.err.println("Rollback failed: " + rollbackEx.getMessage());
				}
			}
			throw new DatabaseOperationException("Failed to update book: " + e.getMessage(), e);
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					System.err.println("Failed to reset auto-commit: " + e.getMessage());
				}
			}
		}
	}

	@Override
	public void bookLog(Book book, Connection conn) throws SQLException {
		try {
			PreparedStatement stmt = conn.prepareStatement(SQLQueries.BOOKS_LOG_INSERT);
			stmt.setInt(1, book.getBookId());
			stmt.setString(2, book.getTitle());
			stmt.setString(3, book.getAuthor());
			stmt.setString(4, book.getCategory().getCategory());
			stmt.setString(5, book.getStatus().getDbName());
			stmt.setString(6, book.getAvailability().getDbName());

			int rowsInserted = stmt.executeUpdate();

			if (rowsInserted <= 0) {
				throw new SQLException("Failed to insert book log.");
			}
		} catch (SQLException e) {
			throw new SQLException("Failed to insert book log.");
		}
	}

	private Book extractBookFromResultSet(ResultSet rs) throws SQLException, InvalidEnumValueException {
		int bookId = rs.getInt("book_id");
		String title = rs.getString("title");
		String author = rs.getString("author");
		BookCategory category = BookCategory.fromDisplayName(rs.getString("category"));
		BookStatus status = BookStatus.fromDbName(rs.getString("status"));
		BookAvailability availability = BookAvailability.fromDbName(rs.getString("availability"));

		return new Book(bookId, title, author, category, status, availability);
	}

}
