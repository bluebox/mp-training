package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Book;
import domain.Status;
import domain.Category;
import domain.AvailabilityStatus;
import exceptions.ManagementException;
import util.DBUtil;

public class BookDao implements BookDaoInterface {

	public void addBook(Book book) throws ManagementException {
		String sql = "insert into books (Title, Author, Category, Status, Availability, AddedBy) values (?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				int i = 1;
				ps.setString(i++, book.getTitle());
				ps.setString(i++, book.getAuthor());
				ps.setString(i++, String.valueOf(book.getCategory().name()));
				ps.setString(i++, String.valueOf(book.getStatus().name().charAt(0)));
				ps.setString(i++, String.valueOf(book.getAvailability().name().charAt(0)));
				ps.setString(i++, book.getAddedBy());

				ps.executeUpdate();
			}
			// Just for testing rollback:
			//if(true) throw new RuntimeException("Test rollback");
			conn.commit();
			System.out.println("Book added successfully and transaction committed.");
		} catch (Exception e) {
			if (conn != null) {
				try {
					conn.rollback();
					System.err.println("Transaction rolled back due to error: " + e.getMessage());
				} catch (Exception rollbackEx) {
					System.err.println("Rollback failed: " + rollbackEx.getMessage());
				}
			}
			throw new ManagementException("Error adding book", e);
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (Exception closeEx) {
					System.err.println("Connection close failed: " + closeEx.getMessage());
				}
			}
		}
	}

	public void updateBook(Book book) throws ManagementException {
		String logSql = "insert into books_log (BookId, Title, Author, Category, Status, Availability, AddedBy) "
				+ "select BookId, Title, Author, Category, Status, Availability, AddedBy "
				+ "from books where BookId=?";
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			try (PreparedStatement logPs = conn.prepareStatement(logSql)) {
				logPs.setInt(1, book.getBookId());
				logPs.executeUpdate();
			}

			StringBuilder updateSql = new StringBuilder("UPDATE books SET ");
			List<Object> params = new ArrayList<>();

			if (book.getTitle() != null && !book.getTitle().trim().isEmpty()) {
				updateSql.append("Title=?, ");
				params.add(book.getTitle());
			}
			if (book.getAuthor() != null && !book.getAuthor().trim().isEmpty()) {
				updateSql.append("Author=?, ");
				params.add(book.getAuthor());
			}
			if (book.getCategory() != null) {
				updateSql.append("Category=?, ");
				params.add(book.getCategory().name());
			}
			if (book.getStatus() != null) {
				updateSql.append("Status=?, ");
				params.add(book.getStatus() == Status.ACTIVE ? "A" : "I");
			}

			if (params.isEmpty()) {
				throw new ManagementException("No fields provided to update.");
			}

			updateSql.setLength(updateSql.length() - 2);
			updateSql.append(" where BookId=?");
			params.add(book.getBookId());

			try (PreparedStatement updatePs = conn.prepareStatement(updateSql.toString())) {
				for (int i = 0; i < params.size(); i++) {
					updatePs.setObject(i + 1, params.get(i));
				}
				updatePs.executeUpdate();
			}

			conn.commit();
		} catch (Exception e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (Exception ignored) {
				}
			}
			throw new ManagementException("Failed to update book", e);
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (Exception ignored) {
				}
			}
		}
	}

	public void updateBookAvailability(Book book) throws ManagementException {
		String logSql = "insert into books_log (BookId, Title, Author, Category, Status, Availability, AddedBy) "
				+ "select BookId, Title, Author, Category, Status, Availability, AddedBy "
				+ "from books where BookId=?";
		String updateSql = "update books set Availability=? where BookId=?";
		Connection conn = null;
		int id = book.getBookId();
		AvailabilityStatus avail = book.getAvailability();

		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			try (PreparedStatement logPs = conn.prepareStatement(logSql)) {
				logPs.setInt(1, id);
				logPs.executeUpdate();
			}

			try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
				updatePs.setString(1, avail == AvailabilityStatus.AVAILABLE ? "A" : "I");
				updatePs.setInt(2, id);
				updatePs.executeUpdate();
			}

			conn.commit();
		} catch (Exception e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (Exception ignored) {
				}
			}
			throw new ManagementException("Failed to update availability", e);
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (Exception ignored) {
				}
			}
		}
	}

	public List<Book> getAllBooks() throws ManagementException {
		List<Book> list = new ArrayList<>();
		String sql = "select BookId, Title, Author, Category, Status, Availability from books";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Book book = new Book();

				book.setBookId(rs.getInt("BookId"));
				book.setTitle(rs.getString("Title"));
				book.setAuthor(rs.getString("Author"));

				String categoryStr = rs.getString("Category");
				book.setCategory(Category.valueOf(categoryStr.toUpperCase()));

				String statusChar = rs.getString("Status");
				Status status = "A".equalsIgnoreCase(statusChar) ? Status.ACTIVE : Status.INACTIVE;
				book.setStatus(status);

				char availChar = rs.getString("Availability").charAt(0);
				AvailabilityStatus availability = (availChar == 'A') ? AvailabilityStatus.AVAILABLE
						: AvailabilityStatus.ISSUED;
				book.setAvailability(availability);
				list.add(book);
			}

		} catch (Exception e) {
			throw new ManagementException("Error fetching books", e);
		}
		return list;
	}

	public boolean bookExists(Book book) throws ManagementException {
		StringBuilder sql = new StringBuilder("select BookId from books where 1=1");
		List<Object> params = new ArrayList<>();

		if (book.getBookId() > 0) {
			sql.append(" and BookId = ?");
			params.add(book.getBookId());
		} else if (book.getTitle() != null && !book.getTitle().trim().isEmpty()) {
			sql.append(" and Title = ?");
			params.add(book.getTitle().trim());
		} else if (book.getAuthor() != null && !book.getAuthor().trim().isEmpty()) {
			sql.append(" and Author = ?");
			params.add(book.getAuthor().trim());
		}

		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		} catch (Exception e) {
			throw new ManagementException("Error checking if book exists", e);
		}
	}

}
