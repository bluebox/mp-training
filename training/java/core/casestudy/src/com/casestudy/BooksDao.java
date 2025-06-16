package com.casestudy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BooksDao {
	private Connection conn;
	private PreparedStatement ps;

	public void createBook(Book book) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			String query = "INSERT INTO Books (title, author, category, status, availability) VALUES (?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getCategory());
			ps.setString(4, book.getStatus().getCode());
			ps.setString(5, book.getAvailable().getCode());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException ignored) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException ignored) {
			}
		}
	}

	public void updateBookAvailability(int bookId) {
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			String query = "SELECT availability FROM Books WHERE bookId = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, bookId);
			rs = ps.executeQuery();

			String availability = null;
			if (rs.next()) {
				availability = rs.getString("availability");
			}

			if (availability == null) {
				System.out.println("Book not found.");
				return;
			}

			availability = availability.equals("A") ? "I" : "A";

			// Update Books table
			ps.close();
			query = "UPDATE Books SET availability = ? WHERE bookId = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, availability);
			ps.setInt(2, bookId);
			int rowsUpdated = ps.executeUpdate();

			// Update LogBooks
			ps.close();
			if (rowsUpdated > 0) {
				conn.commit();
				System.out.println("Book availability updated successfully.");
			} else {
				conn.rollback();
				System.out.println("Book availability update failed.");
			}

		} catch (SQLException e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ignored) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException ignored) {
			}
			try {
				if (conn != null)
					conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException ignored) {
			}
		}
	}

	public void updateBook(Book book) {
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			String query = "SELECT bookId, title, author, category, status, availability FROM Books WHERE bookId = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, book.getBookId());
			rs = ps.executeQuery();

			if (rs.next()) {
				// Log current state
				String logQuery = "INSERT INTO LogBooks (bookId, title, author, category, status, availability) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement logStmt = conn.prepareStatement(logQuery);
				logStmt.setInt(1, rs.getInt("bookId"));
				logStmt.setString(2, rs.getString("title"));
				logStmt.setString(3, rs.getString("author"));
				logStmt.setString(4, rs.getString("category"));
				logStmt.setString(5, rs.getString("status"));
				logStmt.setString(6, rs.getString("availability"));
				logStmt.executeUpdate();
				logStmt.close();
			}

			// Update book
			ps.close();
			query = "UPDATE Books SET title = ?, author = ?, category = ?, status = ? WHERE bookId = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getCategory());
			ps.setString(4, book.getStatus().getCode());
			ps.setInt(5, book.getBookId());

			int updated = ps.executeUpdate();
			if (updated > 0) {
				conn.commit();
				System.out.println("Book updated successfully.");
			} else {
				conn.rollback();
				System.out.println("Book update failed.");
			}

		} catch (SQLException e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ignored) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException ignored) {
			}
			try {
				if (conn != null)
					conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException ignored) {
			}
		}
	}

	public List<Book> viewAllBooks() {
		ResultSet rs = null;
		List<Book> books = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT bookId, title, author, category, status, availability FROM Books";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				int bookId = rs.getInt("bookId");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String category = rs.getString("category");
				String status = rs.getString("status");
				String availability = rs.getString("availability");

				books.add(new Book(bookId, title, author, category, Status.fromCode(status),
						Availability.fromCode(availability)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ignored) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException ignored) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException ignored) {
			}
		}

		return books;
	}

	public boolean CanBeIssued(int bookId) {
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn
						.prepareStatement("SELECT status, availability FROM Books WHERE bookId = ?")) {

			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String status = rs.getString("status");
				String availability = rs.getString("availability");

				return "A".equals(status) && "A".equals(availability);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
