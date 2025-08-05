package com.library.dao.impl;

import com.library.exception.BookNotFoundException;
import com.library.model.Book;
import com.library.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookDaoImplementation {

    // Optional: load driver once if you still want explicit loading
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Log or rethrow as unchecked if you want startup failure
            e.printStackTrace();
        }
    }

    public void addBook(Connection conn, Book book) throws SQLException {
        String sql = "INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setString(4, "A");
            stmt.setString(5, "A");
            stmt.executeUpdate();
        }
    }

    public void updateBook(Connection conn, Book book) throws Exception {
        String updateSql = "UPDATE books SET Title = ?, Author = ?, Category = ?, Status = ? WHERE BookId = ?";

        try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            updateStmt.setString(1, book.getTitle());
            updateStmt.setString(2, book.getAuthor());
            updateStmt.setString(3, book.getCategory());
            updateStmt.setString(4, String.valueOf(book.getStatus()));
            updateStmt.setInt(5, book.getBookId());
            updateStmt.executeUpdate();
        }

        logBookAction(conn, book.getBookId(), "UPDATE");
    }

    public void updateAvailability(Connection conn, int bookId, char availability) throws Exception {
        String checkSql = "SELECT 1 FROM books WHERE BookId = ?";
        String updateSql = "UPDATE books SET Availability = ? WHERE BookId = ?";

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

    public Book getBookById(int bookId) {
        String sql = "SELECT * FROM books WHERE BookId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM books";
        List<Book> books = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    public List<Book> getAvailableBooks() {
        List<Book> list = new ArrayList<>();
        String query = "SELECT * FROM books WHERE Availability = 'A'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
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
                list.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Book> searchBooks(String columnName, String value) {
        List<String> allowedColumns = Arrays.asList("Title", "Author", "Category", "Status", "Availability");
        if (!allowedColumns.contains(columnName)) {
            throw new IllegalArgumentException("Invalid column name: " + columnName);
        }

        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE LOWER(" + columnName + ") = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, value.toLowerCase());
            try (ResultSet rs = ps.executeQuery()) {
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
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    private void logBookAction(Connection conn, int bookId, String actionType) throws Exception {
        String selectSql = "SELECT Title, Author, Category, Status, Availability FROM books WHERE BookId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(selectSql)) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) throw new BookNotFoundException("Book not found for logging.");

            String insertSql = "INSERT INTO books_log (book_id, title, author, category, status, availability, action_type) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setInt(1, bookId);
                ps.setString(2, rs.getString("Title"));
                ps.setString(3, rs.getString("Author"));
                ps.setString(4, rs.getString("Category"));
                ps.setString(5, rs.getString("Status"));
                ps.setString(6, rs.getString("Availability"));
                ps.setString(7, actionType);
                ps.executeUpdate();
            }
        }
    }
}
