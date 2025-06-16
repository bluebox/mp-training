package com.Dao;

import com.Models.Book;
import com.utilities.DbUtil;

import java.sql.*;
import java.util.*;

public class BookDao {
    public void addBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (Title, Author, Category, Status, Availablity) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setString(4, book.getStatus());
            ps.setString(5, book.getAvailability());
            ps.executeUpdate();
            try(ResultSet rs=ps.getGeneratedKeys()){
            	if(rs.next()) {
            		book.setBookId(rs.getInt(1));
            	}
            }
        }
        String sql2 = "INSERT INTO booklogs (bookId,Title, Author, Category, Status, Availablity) VALUES (?,?, ?, ?, ?, ?)";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql2)) {
        	ps.setInt(1, book.getBookId());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getCategory());
            ps.setString(5, book.getStatus());
            ps.setString(6, book.getAvailability());
            ps.executeUpdate();
        }
    }

    public void updateBook(Book book) throws SQLException {
        String sql = "UPDATE books SET Title=?, Author=?, Category=?, Status=? WHERE BookId=?";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
           // ps.setString(4, book.getStatus());
            ps.setString(4, book.getStatus());
            ps.setInt(5, book.getBookId());
            ps.executeUpdate();
            try(ResultSet rs=ps.getGeneratedKeys()){
            	if(rs.next()) {
            		book.setBookId(rs.getInt(1));
            	}
            }
        }
        String sql2 = "INSERT INTO booklogs (bookId,Title, Author, Category, Status, Availablity) VALUES (?,?, ?, ?, ?, ?)";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql2)) {
        	ps.setInt(1, book.getBookId());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getCategory());
            ps.setString(5, book.getStatus());
            ps.setString(6, book.getAvailability());
            ps.executeUpdate();
        }
    }

    public void updateAvailability(int bookId, char availability) throws SQLException {
        String sql = "UPDATE books SET Availablity=? WHERE BookId=?";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, String.valueOf(availability));
            ps.setInt(2, bookId);
            ps.executeUpdate();
        }
        String sql2 = "INSERT INTO booklogs (bookId,Availablity) VALUES (?,?)";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql2)) {
        	ps.setInt(1, bookId);
            
            ps.setString(2,String.valueOf(availability));
            ps.executeUpdate();
        }
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = DbUtil.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("BookId"));
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setCategory(rs.getString("Category"));
                book.setStatus(rs.getString("Status"));
                book.setAvailability(rs.getString("Availablity"));
                list.add(book);
            }
        }
        return list;
    }

	public HashMap<String,Integer> countBooksPerCategor() throws SQLException {
		// TODO Auto-generated method stub
		HashMap<String,Integer> hm=new HashMap();
		String sql = "SELECT * FROM books";
        try (Connection conn = DbUtil.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
            	hm.put(rs.getString("Category"), hm.getOrDefault(rs.getString("Category"), 0)+1);
            }
		
        }
        return hm;
	}
}
