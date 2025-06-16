package dao;


import model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BookDAO {
	
	 public Book getBookById(int bookId) throws Exception {
	        String query = "SELECT * FROM books WHERE BookId = ?";

	        try (Connection conn = JDBCConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, bookId);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                return new Book(
	                    rs.getInt("BookId"),
	                    rs.getString("Title"),
	                    rs.getString("Author"),
	                    rs.getString("Category"),
	                    rs.getString("Status").charAt(0),
	                    rs.getString("Availability").charAt(0)
	                );
	            } else {
	                return null; 
	            }
	        }
	    }

	public void addBook(Book book) throws Exception {
	    String insert = "INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)";
	    String insertLog = "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?, ?)";

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

	        ResultSet rs = stmt.getGeneratedKeys();
	        if (rs.next()) {
	            int bookId = rs.getInt(1);

	            PreparedStatement logStmt = conn.prepareStatement(insertLog);
	            logStmt.setInt(1, bookId);
	            logStmt.setString(2, book.getTitle());
	            logStmt.setString(3, book.getAuthor());
	            logStmt.setString(4, book.getCategory());
	            logStmt.setString(5, String.valueOf(book.getStatus()));
	            logStmt.setString(6, String.valueOf(book.getAvailability()));
	            logStmt.executeUpdate();
	        }

	        conn.commit();
	    } catch (Exception e) {
	        if (conn != null) {
	            try {
	                conn.rollback();
	                System.err.println("Transaction rolled back due to: " + e.getMessage());
	            } catch (Exception rollbackEx) {
	                System.err.println("Rollback failed: " + rollbackEx.getMessage());
	            }
	        }
	        throw e; // rethrow so calling code can handle it
	    } finally {
	        if (conn != null) conn.close();
	    }
	}


}

