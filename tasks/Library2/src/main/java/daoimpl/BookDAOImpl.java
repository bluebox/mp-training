package daoimpl;

import model.Book;
import model.BookCategoryCount;
import Exception.DatabaseException;
import dao.BookDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookDAOImpl implements BookDAO{

	public Book getBookById(int bookId) throws DatabaseException {
		String query = "SELECT bookId, title, author, category, status, availability FROM books WHERE bookId = ?";

		try (Connection conn = JDBCConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, bookId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Book(rs.getInt("bookId"), rs.getString("title"), rs.getString("author"),
						rs.getString("category"), rs.getString("status").charAt(0),
						rs.getString("availability").charAt(0));
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new DatabaseException("Error fetching book by ID", e);
		}
	}

	public void addBook(Book book) throws DatabaseException {
		String insert = "INSERT INTO books (title, author, category, status, availability) VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;
		try {
			conn = JDBCConnection.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement stmt = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, book.getCategory());
			stmt.setString(4, String.valueOf(book.getStatus()));
			stmt.setString(5, String.valueOf(book.getAvailability()));
			stmt.executeUpdate();

			conn.commit();
		} catch (Exception e) {
			throw new DatabaseException("Error adding book", e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ignore) {
					System.err.println(ignore.getMessage());
				}
			}
		}
	}

	public List<Book> getAllBooks() throws Exception {
		List<Book> books = new ArrayList<>();
		String query = "SELECT bookId, title, author, category, status, availability FROM books";
		try (Connection conn = JDBCConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				books.add(new Book(rs.getInt("bookId"), rs.getString("title"), rs.getString("author"),
						rs.getString("category"), rs.getString("status").charAt(0),
						rs.getString("availability").charAt(0)));
			}
		}
		return books;
	}

	public void updateBook(Book book) throws Exception {
		String update = "UPDATE books SET title=?, author=?, category=?, status=?, availability=? WHERE BookId=?";
		String insertLog = "INSERT INTO books_log (bookId, title, author, category, status, availability) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = JDBCConnection.getConnection()) {
			conn.setAutoCommit(false);
			Book existingBook = getBookById(book.getBookId());
			PreparedStatement logStmt = conn.prepareStatement(insertLog);
			logStmt.setInt(1, existingBook.getBookId());
			logStmt.setString(2, existingBook.getTitle());
			logStmt.setString(3, existingBook.getAuthor());
			logStmt.setString(4, existingBook.getCategory());
			logStmt.setString(5, String.valueOf(existingBook.getStatus()));
			logStmt.setString(6, String.valueOf(existingBook.getAvailability()));
			logStmt.executeUpdate();
			PreparedStatement stmt = conn.prepareStatement(update);
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, book.getCategory());
			stmt.setString(4, String.valueOf(book.getStatus()));
			stmt.setString(5, String.valueOf(book.getAvailability()));
			stmt.setInt(6, book.getBookId());
			stmt.executeUpdate();

			conn.commit();
		}
	}

	public void updateBookAvailability(int bookId, Character availability) throws Exception {
		String update = "UPDATE books SET availability=? WHERE bookId=?";
		String insertLog = "INSERT INTO books_log (bookId, title, author, category, status, availability) VALUES (?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement logStmt = null;
		PreparedStatement stmt = null;
		Book existingBook = null;

		try {
			conn = JDBCConnection.getConnection();
			conn.setAutoCommit(false);

			existingBook = getBookById(bookId);
			if (existingBook.getAvailability().equals(availability)) {
				throw new Exception("No change in availability");
			}

			logStmt = conn.prepareStatement(insertLog);
			logStmt.setInt(1, existingBook.getBookId());
			logStmt.setString(2, existingBook.getTitle());
			logStmt.setString(3, existingBook.getAuthor());
			logStmt.setString(4, existingBook.getCategory());
			logStmt.setString(5, String.valueOf(existingBook.getStatus()));
			logStmt.setString(6, String.valueOf(existingBook.getAvailability()));
			logStmt.executeUpdate();

			stmt = conn.prepareStatement(update);
			stmt.setString(1, String.valueOf(availability));
			stmt.setInt(2, bookId);
			int rowsAffected = stmt.executeUpdate();

			if (rowsAffected < 0) {
				throw new Exception("Error in update query");
			}

			conn.commit();
		} catch (Exception e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (Exception rollbackEx) {
					throw new Exception("Error during rollback: " + rollbackEx.getMessage(), rollbackEx);
				}
			}
			throw new Exception("Error updating book availability: " + e.getMessage(), e);
		} finally {
			try {
				if (logStmt != null)
					logStmt.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				throw new Exception("Error closing resources: " + e.getMessage(), e);
			}
		}
	}
}
