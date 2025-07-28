package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Book;
import domain.BookStatus;
import exceptions.DatabaseException;
import domain.AvailabilityStatus;
import util.DBUtil;

public class bookDao {
	
	
	 public void addBook(Book book) throws DatabaseException {
		 String sql = "insert into books(Title,Author,Category,Status,Availability) values (?, ?, ?, ?, ?)";
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1,book.getTitle());
	            ps.setString(2,book.getAuthor());
	            ps.setString(3,book.getCategory());
	            ps.setString(4,book.getStatus().name());
	            ps.setString(5,book.getAvailability().name());
	            ps.executeUpdate();
	            System.out.println("Book added.");
	        } 
	        catch (Exception e) {
	            System.out.println("Error adding Book ");
	            throw new DatabaseException("Error adding book to database", e);
	        }
		
	 }

	

	 public void updateBookDetails(int id, String title, String author, String category, BookStatus status) throws DatabaseException {
		    String logSql = "INSERT INTO books_log SELECT * FROM books WHERE BookId=?";
		    
		    Connection conn = null;
		    try {
		        conn = DBUtil.getConnection();
		        conn.setAutoCommit(false); 
		        try (PreparedStatement logPs = conn.prepareStatement(logSql)) {
		            logPs.setInt(1, id);
		            logPs.executeUpdate();
		        }
		        StringBuilder updateSql = new StringBuilder("UPDATE books SET ");
		        List<Object> params = new ArrayList<>();

		        if (title != null) {
		            updateSql.append("Title=?, ");
		            params.add(title);
		        }
		        if (author != null) {
		            updateSql.append("Author=?, ");
		            params.add(author);
		        }
		        if (category != null) {
		            updateSql.append("Category=?, ");
		            params.add(category);
		        }
		        if (status != null) {
		            updateSql.append("Status=?, ");
		            params.add(status.name());
		        }

		        if (params.isEmpty()) {
		            throw new DatabaseException("No fields provided to update.", null);
		        }
		        updateSql.setLength(updateSql.length() - 2);
		        updateSql.append(" WHERE BookId=?");
		        params.add(id);

		        try (PreparedStatement updatePs = conn.prepareStatement(updateSql.toString())) {
		            for (int i = 0; i < params.size(); i++) {
		                updatePs.setObject(i + 1, params.get(i));
		            }
		            updatePs.executeUpdate();
		        }
		        conn.commit();
		        System.out.println("Book updated.");
		    } catch (Exception e) {
		        if (conn != null) {
		            try {
		                conn.rollback();
		            } catch (Exception rollbackEx) {
		                System.err.println("Rollback failed: " + rollbackEx.getMessage());
		            }
		        }
		        throw new DatabaseException("Failed to update book", e);
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


	 public void updateBookAvailability(int id, AvailabilityStatus avail) throws DatabaseException {
		    String logSql = "INSERT INTO books_log SELECT * FROM books WHERE BookId=?";
		    String updateSql = "UPDATE books SET Availability=? WHERE BookId=?";

		    Connection conn = null;
		    try {
		        conn = DBUtil.getConnection();
		        conn.setAutoCommit(false); 
		        try (PreparedStatement logPs = conn.prepareStatement(logSql)) {
		            logPs.setInt(1, id);
		            logPs.executeUpdate();
		        }
		        try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
		            updatePs.setString(1, avail.name());
		            updatePs.setInt(2, id);
		            updatePs.executeUpdate();
		        }

		        conn.commit();
		        System.out.println("Book availability updated.");
		    } catch (Exception e) {
		        if (conn != null) {
		            try {
		                conn.rollback();
		            } catch (Exception rollbackEx) {
		                System.err.println("Rollback failed: " + rollbackEx.getMessage());
		            }
		        }
		        throw new DatabaseException("Failed to update availability", e);
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

	 public List<Book> getAllBooks() throws DatabaseException {
		 List<Book> list = new ArrayList<>();
	        String sql = "select * from books";
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                list.add(
	                		new Book(
	                				rs.getInt(1),
	                				rs.getString(2),
	                				rs.getString(3), 
	                				rs.getString(4),
	                				BookStatus.valueOf(rs.getString(5).trim().toUpperCase()),
	                				AvailabilityStatus.valueOf(rs.getString(6).trim().toUpperCase())));
	            }
	        } 
	        catch (Exception e) {
	            System.out.println("Error fetching members");
	            throw new DatabaseException("Failed to update availability", e);
	        }
	        return list;
	    }
	 
	 public boolean bookExists(int id) throws DatabaseException {
		    String sql = "SELECT * FROM books WHERE BookId = ?";
		    try (Connection conn = DBUtil.getConnection();
		         PreparedStatement ps = conn.prepareStatement(sql)) {
		        ps.setInt(1, id);
		        try (ResultSet rs = ps.executeQuery()) {
		            return rs.next();
		        }
		    } catch (Exception e) {
		        throw new DatabaseException("Error checking if book exists", e);
		    }
		}
}
