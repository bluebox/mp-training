package com.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.dao.BookDAO;
import com.library.dao.queries.BookSQLQueries;
import com.library.domain.Book;

public class BookDAOImpl extends BookSQLQueries implements BookDAO {
	public boolean isBookExists(int bookId, Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(isBookExists);) {

			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isBookAvailable(int bookId, Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(checkBookAvailability);) {
			ps.setInt(1, bookId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return "A".equals(rs.getString("status")) && "A".equals(rs.getString("availability"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateDetails(Connection conn, Book book) {
		try {
			PreparedStatement updateStmt = conn.prepareStatement(updateBook);
			updateStmt.setString(1, book.getTitle());
			updateStmt.setString(2, book.getAuthor());
			updateStmt.setString(3, book.getCategory());
			updateStmt.setString(4, book.getStatus());
			updateStmt.setInt(5, book.getBookId());
			updateStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean isValid(Book book) {
		return ("A".equals(book.getStatus()) || "I".equals(book.getStatus()))
				&& ("A".equals(book.getAvailability()) || "I".equals(book.getAvailability()));
	}

	public boolean changeAvailability(int bookId, Connection conn) {

		try (PreparedStatement ps = conn.prepareStatement(changeBookAvaliability)) {

			ps.setInt(1, bookId);
			int rowsUpdated = ps.executeUpdate();

			return rowsUpdated > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean insertBook(Connection conn, Book book) {
		if (!isValid(book))
			return false;

		try (
				PreparedStatement stmt = conn.prepareStatement(insertBookQuery)) {

			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, book.getCategory());
			stmt.setString(4, book.getStatus());
			stmt.setString(5, book.getAvailability());
			return stmt.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Book> getAllBooks(Connection conn) {
		List<Book> books = new ArrayList<>();

		try (PreparedStatement stmt = conn.prepareStatement(selectAllBooks); ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
						rs.getString("category"), rs.getString("status"), rs.getString("availability")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return books;
	}

	@Override
	public void updateBookAvailability(Connection conn, int bookId) {
		try (PreparedStatement ps = conn.prepareStatement(updateBookAvailability)) {
			ps.setInt(1, bookId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
