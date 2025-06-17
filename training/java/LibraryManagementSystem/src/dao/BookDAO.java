package dao;


import model.Book;
import Exception.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
	
	 public Book getBookById(int bookId) throws DatabaseException {
	        String query = "SELECT * FROM books WHERE bookId = ?";

	        try (Connection conn = JDBCConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, bookId);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                return new Book(
	                    rs.getInt("bookId"),
	                    rs.getString("title"),
	                    rs.getString("author"),
	                    rs.getString("category"),
	                    rs.getString("status").charAt(0),
	                    rs.getString("availability").charAt(0)
	                );
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
	    String query = "SELECT * FROM books";
	    try (Connection conn = JDBCConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {
	        while (rs.next()) {
	            books.add(new Book(
	                rs.getInt("bookId"),
	                rs.getString("title"),
	                rs.getString("author"),
	                rs.getString("category"),
	                rs.getString("status").charAt(0),
	                rs.getString("availability").charAt(0)
	            ));
	        }
	    }
	    return books;
	}

	public void updateBook(Book book) throws Exception {
	    String update = "UPDATE books SET title=?, author=?, category=?, status=?, availability=? WHERE BookId=?";
	    String insertLog = "INSERT INTO books_log (bookId, title, author, category, status, availability) VALUES (?, ?, ?, ?, ?, ?)";
	    try (Connection conn = JDBCConnection.getConnection()) {
	        conn.setAutoCommit(false);
	        Book newBook = getBookById(book.getBookId());
	        PreparedStatement logStmt = conn.prepareStatement(insertLog);
	        logStmt.setInt(1, newBook.getBookId());
	        logStmt.setString(2, newBook.getTitle());
	        logStmt.setString(3, newBook.getAuthor());
	        logStmt.setString(4, newBook.getCategory());
	        logStmt.setString(5, String.valueOf(newBook.getStatus()));
	        logStmt.setString(6, String.valueOf(newBook.getAvailability()));
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
	
	public void updateBookAvailability(int bookId,Character availability) throws Exception{
		String update = "UPDATE books SET availability=? WHERE bookId=?";
	    String insertLog = "INSERT INTO books_log (bookId, title, author, category, status, availability) VALUES (?, ?, ?, ?, ?, ?)";
	    Connection conn = JDBCConnection.getConnection();
	    try {
	        conn.setAutoCommit(false);
	        try (PreparedStatement logStmt = conn.prepareStatement(insertLog)) {
	        	Book newBook = getBookById(bookId);
	        	if (newBook.getAvailability()==availability) {
            		throw new Exception("No change in availability");
            	}
	        	logStmt.setInt(1, newBook.getBookId());
                logStmt.setString(2, newBook.getTitle());
                logStmt.setString(3, newBook.getAuthor());
                logStmt.setString(4, newBook.getCategory());
                logStmt.setString(5, String.valueOf(newBook.getStatus()));
                logStmt.setString(6, String.valueOf(newBook.getAvailability()));
                logStmt.executeUpdate();
	            try (PreparedStatement stmt = conn.prepareStatement(update)) {
	            	stmt.setString(1, String.valueOf(availability));
	    	        stmt.setInt(2, bookId);
	    	        int rowsAffected = stmt.executeUpdate();
	    	        if (rowsAffected < 0) {
	    	        	throw new Exception("Error in update query");
	    	        }
	    	        
	    	        else {
	    	        	conn.commit();
	    	        }
	            }catch(Exception e) {
	            	conn.rollback();
	            	System.err.println(e.getMessage());
	            }
	        }
	    } catch(Exception e) {
	    	System.out.println(e.getMessage());
	    }finally {
	    	conn.close();
	    }
	}

}

