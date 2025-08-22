package com.library.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.library.Exception.BookDAOException;
import com.library.model.Book;
import com.library.util.DatabaseConnection;
import com.library.dao.*;

public class BookDAOImplementation implements BookDAO {
	

	@Override
	public int addBook(Book book) {

		String insertBook = "INSERT INTO books(title, author,category,status,availability) VALUES (?,?,?,?,?)";
		final String DEFAULT_STATUS = "A";

		try (Connection conn = DatabaseConnection.getConnection()) {
			conn.setAutoCommit(false);

			try {
				PreparedStatement insertStatement = conn.prepareStatement(insertBook, Statement.RETURN_GENERATED_KEYS);
				insertStatement.setString(1, book.getTitle());
				insertStatement.setString(2, book.getAuthor());
				insertStatement.setString(3, book.getCategory());
				insertStatement.setString(4, DEFAULT_STATUS);
				insertStatement.setString(5, DEFAULT_STATUS);

				int insertionSuccess = insertStatement.executeUpdate();
				if (insertionSuccess > 0) {
					try (ResultSet generatedId = insertStatement.getGeneratedKeys()) {
						if (generatedId.next()) {
							return generatedId.getInt(1);
						}
					}
				}
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
		return -1;
	}

	@Override
	public void updateBook(Book book) {
	    String updateBook = "UPDATE books SET title=?, author=?, category=?, status=? WHERE BookId=?";
	    String updateBookLog = "INSERT INTO books_log(BookId, Title, Author, Category, Status, Availability, Operation_Type) VALUES (?,?,?,?,?,?,?)";

	    try (Connection conn = DatabaseConnection.getConnection()) {
	        conn.setAutoCommit(false);
	        try (PreparedStatement logStmt = conn.prepareStatement(updateBookLog);
	             PreparedStatement updateStmt = conn.prepareStatement(updateBook)) {
	            logStmt.setInt(1, book.getBookId());
	            logStmt.setString(2, book.getTitle());
	            logStmt.setString(3, book.getAuthor());
	            logStmt.setString(4, book.getCategory());
	            logStmt.setString(5, book.getStatus());
	            logStmt.setString(6, book.getAvailability());
	            logStmt.setString(7, "Update");
	            logStmt.executeUpdate();
	            
	            updateStmt.setString(1, book.getTitle());
	            updateStmt.setString(2, book.getAuthor());
	            updateStmt.setString(3, book.getCategory());
	            updateStmt.setString(4, book.getStatus());
	            updateStmt.setInt(5, book.getBookId());
	            updateStmt.executeUpdate();

	            conn.commit();
	        } catch (SQLException e) {
	            conn.rollback();
	            throw new BookDAOException("Failed to update book", e);
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
	    System.out.println(">> DAO: getAllBooks called");
	    List<Book> books = new ArrayList<>();
	    String query = "SELECT * FROM books";

	    try (Connection conn = DatabaseConnection.getConnection()) {
	        System.out.println(">> Got connection: " + conn);
	        PreparedStatement ps = conn.prepareStatement(query);
	        System.out.println(">> Prepared statement created");
	        ResultSet rs = ps.executeQuery();
	        System.out.println(">> Executed query");

	        while (rs.next()) {
	            int id = rs.getInt("bookId");
	            String title = rs.getString("title");
	            String author = rs.getString("author");
	            String category = rs.getString("category");
	            char status = rs.getString("status").charAt(0);
	            char availability = rs.getString("availability").charAt(0);

	            System.out.println(">> Found: " + id + " " + title);

	            books.add(new Book(id, title, author, category, String.valueOf(status), String.valueOf(availability)));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    System.out.println(">> Total books fetched: " + books.size());
	    return books;
	}


	public Book getBookById(int bookId) throws  Exception {
		String sql = "SELECT * FROM books WHERE BookId = ?";
		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("BookId"));
				book.setTitle(rs.getString("Title"));
				book.setAuthor(rs.getString("Author"));
				book.setCategory(rs.getString("Category"));
				book.setStatus(rs.getString("Status"));
				book.setAvailability(rs.getString("Availability"));
				return book;
			} else {
				return null;
			}
		}
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
