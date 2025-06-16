package dao;

import model.Book;
import util.DBConnection;
import exception.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public void addBook(Book book) throws DatabaseException {
        String sql = "INSERT INTO books (title, author, category, status, availability) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setString(4, String.valueOf(book.getStatus()));
            stmt.setString(5, String.valueOf(book.getAvailability()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error adding book: " + e.getMessage(), e);
        }
    }

    public List<Book> getAllBooks() throws DatabaseException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Book book = new Book(
                    rs.getInt("bookId"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("category"),
                    rs.getString("status").charAt(0),
                    rs.getString("availability").charAt(0)
                );
                books.add(book);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching books: " + e.getMessage(), e);
        }

        return books;
    }

    // Update Book Details (except availability)
    public void updateBookDetails(Book book) throws DatabaseException {
        String sql = "UPDATE books SET title=?, author=?, category=?, status=? WHERE bookId=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setString(4, String.valueOf(book.getStatus()));
            stmt.setInt(5, book.getBookId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error updating book details: " + e.getMessage(), e);
        }
    }

    public void updateAvailability(int bookId, char availability) throws DatabaseException {
        String sql = "UPDATE books SET availability=? WHERE bookId=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, String.valueOf(availability));
            stmt.setInt(2, bookId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error updating book availability: " + e.getMessage(), e);
        }
    }

    public boolean isBookAvailable(int bookId) throws DatabaseException {
        String sql = "SELECT availability FROM books WHERE bookId=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("availability").charAt(0) == 'A';
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error checking book availability: " + e.getMessage(), e);
        }
    }
 // Get Book by ID
    public Book getBookById(int bookId) throws DatabaseException {
        String sql = "SELECT * FROM books WHERE bookId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
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
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving book by ID: " + e.getMessage(), e);
        }
    }

}
