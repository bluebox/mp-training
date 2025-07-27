package com.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.library.exception.BookNotFoundException;
import com.library.model.Book;
import com.library.util.*;

public class BookDaoImplementation {
	public void addBook(Book book) throws Exception {
		String sql = "INSERT INTO books (Title,Author,Category,Status,Availability) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, book.getTitle());
                stmt.setString(2, book.getAuthor());
                stmt.setString(3, book.getCategory());
                
                char c='A';
                stmt.setString(4, Character.toString(c));
                stmt.setString(5, Character.toString(c));
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int bookId = rs.getInt(1);
                    logMemberAction(conn, bookId, "ADD");
                }
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void logMemberAction(Connection conn, int bookId, String title,String author,String category,String status,String availability, String actionType) throws SQLException {
        String sql = "INSERT INTO books_log (book_id, title, author, category,status,availability,action_type) VALUES (?, ?, ?, ?, ?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            ps.setString(2, title);
            ps.setString(3, author);
            ps.setString(4, category);
            ps.setString(5, status);
            ps.setString(6, availability);
            ps.setString(7, actionType);
            ps.executeUpdate();
        }
    }

    private void logMemberAction(Connection conn, int bookId, String actionType) throws Exception {
        String selectSql = "SELECT Title,Author,Category,Status,Availability FROM books WHERE  BookId= ?";
        try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
            selectStmt.setInt(1, bookId);
            ResultSet rs = selectStmt.executeQuery();
            if (!rs.next()) throw new Exception("book not found for logging.");
            logMemberAction(conn, bookId, rs.getString("Title"),rs.getString("Author"),rs.getString("Category"),rs.getString("Status"),rs.getString("Availability"),actionType);
        }
    }

	public List<Book> getAllBooks() {
		  String sql = "SELECT * FROM books";
		  List<Book> li=new ArrayList<>();
		try (Connection conn = DBConnection.getConnection()){
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			 while (rs.next()) {
	               Book b1=new Book(rs.getInt("BookId"),rs.getString("Title"),rs.getString("Author"),rs.getString("Category"),rs.getString("Status").charAt(0),rs.getString("Availability").charAt(0));
//	            		   public Book(int bookId, String title, String author, String category, char status, char availability)
	               li.add(b1);
//	                System.out.println("ID: " + id + ", Title: " + title);
	            }
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return li;
	}
	
	
	public void updateBookDetails(Book book) throws Exception{
		String checkSql = "SELECT * FROM books WHERE BookId = ?";
        Book existingBook = null;
        try(Connection conn = DBConnection.getConnection(); PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
        	
            checkStmt.setInt(1, book.getBookId());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                existingBook = new Book();
                existingBook.setBookId(rs.getInt("BookId"));
                existingBook.setTitle(rs.getString("Title"));
                existingBook.setAuthor(rs.getString("Author"));
                existingBook.setCategory(rs.getString("Category"));
                existingBook.setStatus(rs.getString("Status").charAt(0));
                existingBook.setAvailability(rs.getString("Availability").charAt(0));
            } else {
//                throw new BookNotFoundException("Book with ID " + book.getBookId() + " not found.");
            }
        }

        String updateSql = "UPDATE books SET Title = ?, Author = ?, Category = ?,Status=? WHERE BookId = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {
            ps.setString(1, book.getTitle() != null ? book.getTitle() : existingBook.getTitle());
            ps.setString(2, book.getAuthor() != null ? book.getAuthor() : existingBook.getAuthor());
            ps.setString(3, book.getCategory() != null ? book.getCategory() : existingBook.getCategory());
            ps.setString(4, book.getStatus() != 0 ? String.valueOf(book.getStatus()) : String.valueOf(existingBook.getStatus()));
            ps.setInt(5, book.getBookId());

            ps.executeUpdate();
        }
        
	}
	


	public Book getBookById(int bookId) throws Exception {
	    String sql = "SELECT * FROM books WHERE BookId = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, bookId);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	           Book book = new Book();
	            book.setBookId(rs.getInt("BookId"));
	            book.setTitle(rs.getString("Title"));
	            book.setAuthor(rs.getString("Author"));
	            book.setCategory(rs.getString("Category"));
	            book.setStatus(rs.getString("Status").charAt(0));
	            book.setAvailability(rs.getString("Availability").charAt(0));
	            return book;
	        } else {
	           return null; 
	        }
	    }
	}

	
	public void updateBookAvailability(int bookId, char availability) throws Exception {
	    
	    String checkSql = "SELECT 1 FROM books WHERE BookId = ?";
	    String updateSql = "UPDATE books SET Availability = ? WHERE BookId = ?";

	    try (Connection conn = DBConnection.getConnection()) {
	        
	        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
	            checkStmt.setInt(1, bookId);
	            ResultSet rs = checkStmt.executeQuery();
	            if (!rs.next()) {
	                throw new BookNotFoundException("Book with ID " + bookId + " not found.");
	            }
	        }

	        
	        try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
	            updateStmt.setString(1, String.valueOf(availability));
	            updateStmt.setInt(2, bookId);
	            updateStmt.executeUpdate();
	        }
	    }
	}


	public List<Book> getBooks(String columnName, String value) throws Exception {
	    List<String> allowedColumns = Arrays.asList("Title", "Author", "Category", "Status", "Availability");
	    if (!allowedColumns.contains(columnName)) {
	        throw new IllegalArgumentException("Invalid column name: " + columnName);
	    }

	    List<Book> books = new ArrayList<>();
	    String sql = "SELECT * FROM books WHERE LOWER(" + columnName + ") = ?";

	    try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, value.toLowerCase());

	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Book book = new Book();
	            book.setBookId(rs.getInt("BookId"));
	            book.setTitle(rs.getString("Title"));
	            book.setAuthor(rs.getString("Author"));
	            book.setCategory(rs.getString("Category"));
	            book.setStatus(rs.getString("Status").charAt(0));
	            book.setAvailability(rs.getString("Availability").charAt(0));
	            books.add(book);
	        }
	    }
	    return books;
	}
}
