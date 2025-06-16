package com.library.dao;

import com.library.domain.Book;
import com.library.utilities.ConnectionMaker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private static final String TABLE_NAME = "book";

    private boolean isValid(Book book) {
        return ("A".equals(book.getStatus()) || "I".equals(book.getStatus())) &&
               ("A".equals(book.getAvailability()) || "I".equals(book.getAvailability()));
    }
    public boolean changeAvailability(int bookId,String query) {
    	
        try (Connection conn = ConnectionMaker.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, bookId);
            int rowsUpdated = ps.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean insertBook(Book book) {
        if (!isValid(book)) return false;

        String sql = "INSERT INTO " + TABLE_NAME + " (title, author, category, status, availability) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionMaker.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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

    public List<Book> getAllBooks(String query) {
        List<Book> books = new ArrayList<>();

        try (Connection conn = ConnectionMaker.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
        	
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getString("status"),
                        rs.getString("availability")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }

}
