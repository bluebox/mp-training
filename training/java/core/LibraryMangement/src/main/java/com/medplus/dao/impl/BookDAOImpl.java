package com.medplus.dao.impl;

import com.medplus.dao.BookDAO;
import com.medplus.model.Book;
import com.medplus.util.DBConnection;
import java.sql.*;
import java.util.*;

public class BookDAOImpl implements BookDAO {

    @Override
    public void addBook(Book book) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, book.getTitle());
        stmt.setString(2, book.getAuthor());
        stmt.setString(3, book.getCategory());
        stmt.setString(4, String.valueOf(book.getStatus()));
        stmt.setString(5, String.valueOf(book.getAvailability()));
        stmt.executeUpdate();
    }

    @Override
    public void updateBook(Book book) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE books SET Title = ?, Author = ?, Category = ?, Status = ? WHERE BookId = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, book.getTitle());
        stmt.setString(2, book.getAuthor());
        stmt.setString(3, book.getCategory());
        stmt.setString(4, String.valueOf(book.getStatus()));
        stmt.setInt(5, book.getBookId());
        stmt.executeUpdate();
    }

    @Override
    public void updateAvailability(int bookId, char availability) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE books SET Availability = ? WHERE BookId = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, String.valueOf(availability));
        stmt.setInt(2, bookId);
        stmt.executeUpdate();
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM books");
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            books.add(new Book(
                rs.getInt("BookId"),
                rs.getString("Title"),
                rs.getString("Author"),
                rs.getString("Category"),
                rs.getString("Status").charAt(0),
                rs.getString("Availability").charAt(0)
            ));
        }
        return books;
    }

    @Override
    public Book getBookById(int bookId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM books WHERE BookId = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
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
        }
        return null;
    }
	
}