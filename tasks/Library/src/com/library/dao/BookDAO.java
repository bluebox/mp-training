package com.library.dao;
import com.library.domain.Book;
import com.library.util.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("BookId"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Category"),
                        rs.getString("Status").charAt(0),
                        rs.getString("Availability").charAt(0)
                );
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public boolean addBook(Book book) {
        String sql = "INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setString(4, String.valueOf(book.getStatus()));
            stmt.setString(5, String.valueOf(book.getAvailability()));

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean isAvailable(Connection conn, int bookId) throws Exception {
        String query = "SELECT Availability FROM books WHERE BookId = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String availability = rs.getString("Availability");
                return "A".equalsIgnoreCase(availability);
            }
           
            throw new Exception("book doesn't exist");
        }
    }
    

    public void updateAvailability(Connection conn, int bookId, char status) throws SQLException {
        if (status != 'A' && status != 'I') {
            throw new IllegalArgumentException("Availability status must be 'A' (Available) or 'I' (Issued).");
        }

        String fetchSQL = "SELECT * FROM books WHERE BookId = ?";
        try (PreparedStatement fetchStmt = conn.prepareStatement(fetchSQL)) {
            fetchStmt.setInt(1, bookId);
            ResultSet rs = fetchStmt.executeQuery();

            if (rs.next()) {
                String logSQL = "INSERT INTO books_log (book_id, title, author, category, status, availablity) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement logStmt = conn.prepareStatement(logSQL)) {
                    logStmt.setInt(1, rs.getInt("BookId"));
                    logStmt.setString(2, rs.getString("Title"));
                    logStmt.setString(3, rs.getString("Author"));
                    logStmt.setString(4, rs.getString("Category"));
                    logStmt.setString(5, rs.getString("Status"));
                    logStmt.setString(6, rs.getString("Availability"));
                    logStmt.executeUpdate();
                }

             
                String updateSQL = "UPDATE books SET Availability = ? WHERE BookId = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSQL)) {
                    updateStmt.setString(1, String.valueOf(status));
                    updateStmt.setInt(2, bookId);
                    updateStmt.executeUpdate();
                }

            } else {
                throw new SQLException("Book with ID " + bookId + " not found.");
            }
        }
    }

    }
