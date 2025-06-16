package com.library.dao;

import com.library.domain.Book;
import com.library.utilities.ConnectionMaker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private boolean isTestMode = false;

    public void setTestMode(boolean testMode) {
        this.isTestMode = testMode;
    }

    private String getTableName() {
        return isTestMode ? "books_test" : "book";
    }

    private boolean isValid(Book book) {
        return ("A".equals(book.getStatus()) || "I".equals(book.getStatus())) &&
               ("A".equals(book.getAvailability()) || "I".equals(book.getAvailability()));
    }

    public boolean insertBook(Book book) {
        if (!isValid(book)) return false;

        String sql = "INSERT INTO " + getTableName() + " (title, author, category, status, availability) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
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

        try (Connection conn = getConnection();
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

    public List<Book> getBooksByAvailability(String availability) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM " + getTableName() + " WHERE availability = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, availability);
            ResultSet rs = pstmt.executeQuery();

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public void updateDetails(Connection conn, Book book) {
        try {
            String table = getTableName();
            PreparedStatement logStmt = conn.prepareStatement(
                "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availability) " +
                "SELECT BookId, Title, Author, Category, Status, Availability FROM " + table + " WHERE BookId = ?"
            );
            logStmt.setInt(1, book.getBookId());
            logStmt.executeUpdate();

            PreparedStatement updateStmt = conn.prepareStatement(
                "UPDATE " + table + " SET Title = ?, Author = ?, Category = ?, Status = ? WHERE BookId = ?"
            );
            updateStmt.setString(1, book.getTitle());
            updateStmt.setString(2, book.getAuthor());
            updateStmt.setString(3, book.getCategory());
            updateStmt.setString(4, book.getStatus());
            updateStmt.setInt(5, book.getBookId());
            updateStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        return ConnectionMaker.getConnection();
    }
}