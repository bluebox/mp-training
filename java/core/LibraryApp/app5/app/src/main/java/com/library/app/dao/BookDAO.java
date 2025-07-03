package com.library.app.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.library.app.model.Availability;
import com.library.app.model.Book;
import com.library.app.model.Status;
import com.library.app.utilities.DBUtil;


public class BookDAO {
	private Connection conn;

	public BookDAO() throws SQLException {
		this.conn = DBUtil.getConnection();
	}

	public void addBook(Book book) throws SQLException {
		if(conn==null||conn.isClosed()) {
			conn=DBUtil.getConnection();
		}
		try (PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)");
				PreparedStatement ps2 = conn.prepareStatement(
						"INSERT INTO book_log (Title, Author, Category, Status, Availability,OperationType) VALUES (?, ?, ?, ?, ?, ?)");) {
			ps2.setString(1, book.getTitle());
			ps2.setString(2, book.getAuthor());
			ps2.setString(3, book.getCategory());
			ps2.setString(4, book.getStatus().name().substring(0,1));
			ps2.setString(5, book.getAvailability().name().substring(0,1));
			ps2.setString(6, "Insert");
			ps2.executeUpdate();
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getCategory());
			ps.setString(4, book.getStatus().name().substring(0,1));
			ps.setString(5, book.getAvailability().name().substring(0,1));
			ps.executeUpdate();

			conn.commit();
			System.out.println("Book Added Successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
	}

	public void updateBookDetails(Book book) throws SQLException {
		if(conn==null||conn.isClosed()) {
			conn=DBUtil.getConnection();
		}
		try (PreparedStatement ps = conn
				.prepareStatement("UPDATE books SET Title=?, Author=?, Category=?, Status=? WHERE BookId=?");
				PreparedStatement ps2 = conn.prepareStatement(
						"INSERT INTO book_log (BookId,Title, Author, Category, Status, Availability,OperationType) VALUES (?, ?, ?, ?, ?, ?, ?)");) {
			ps2.setInt(1,book.getBookId());
			ps2.setString(2, book.getTitle());
			ps2.setString(3, book.getAuthor());
			ps2.setString(4, book.getCategory());
			ps2.setString(5, book.getStatus().name().substring(0,1));
			ps2.setString(6, book.getAvailability().name().substring(0,1));
			ps2.setString(7, "Update");
			ps2.executeUpdate();

			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getCategory());
			ps.setString(4, book.getStatus().name().substring(0,1));
			ps.setInt(5, book.getBookId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
	}

	public void updateAvailability(int bookId, char availability) throws SQLException {
		if(conn==null||conn.isClosed()) {
			conn=DBUtil.getConnection();
		}
		try (PreparedStatement ps = conn.prepareStatement("UPDATE books SET Availability=? WHERE BookId=?")) {
			ps.setString(1, String.valueOf(availability));
			ps.setInt(2, bookId);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
	}

	public List<Book> getAllBooks() throws SQLException {
		if(conn==null||conn.isClosed()) {
			conn=DBUtil.getConnection();
		}
		List<Book> books = new ArrayList<>();
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {
			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("BookId"));
				book.setTitle(rs.getString("Title"));
				book.setAuthor(rs.getString("Author"));
				book.setCategory(rs.getString("Category"));
				String statusChar = rs.getString("Status");
				if ("A".equalsIgnoreCase(statusChar)) {
				    book.setStatus(Status.ACTIVE);
				} else {
				    book.setStatus(Status.INACTIVE);
				}
				String availabilityChar = rs.getString("Availability");
				if ("A".equalsIgnoreCase(availabilityChar)) {
				    book.setAvailability(Availability.AVAILABLE);
				} else {
				    book.setAvailability(Availability.ISSUED);
				}
				books.add(book);
//	            System.out.println(rs.getInt("BookId")+rs.getString("Title")+rs.getString("Author"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	public Book getBookById(int bookId) throws SQLException {
		if(conn==null||conn.isClosed()) {
			conn=DBUtil.getConnection();
		}
		Book book = null;
		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM books WHERE BookId=?")) {
			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				book = new Book();
				book.setBookId(rs.getInt("BookId"));
				book.setTitle(rs.getString("Title"));
				book.setAuthor(rs.getString("Author"));
				book.setCategory(rs.getString("Category"));
				String statusChar = rs.getString("Status");
				if ("A".equalsIgnoreCase(statusChar)) {
				    book.setStatus(Status.ACTIVE);
				} else {
				    book.setStatus(Status.INACTIVE);
				}
				String availabilityChar = rs.getString("Availability");
				if ("A".equalsIgnoreCase(availabilityChar)) {
				    book.setAvailability(Availability.AVAILABLE);
				} else {
				    book.setAvailability(Availability.ISSUED);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

}

