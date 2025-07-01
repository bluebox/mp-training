package com.library.app.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.library.app.model.*;
import com.library.app.utilities.DBUtil;

public class BookDAO {
    private Connection conn;

    public BookDAO() throws SQLException {
        conn = DBUtil.getConnection();
    }

    public void addBook(Book book) throws SQLException {
        if (conn == null || conn.isClosed()) conn = DBUtil.getConnection();
        String insertSQL = "INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)";
        String logSQL = "INSERT INTO book_log (Title, Author, Category, Status, Availability, OperationType) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(insertSQL);
             PreparedStatement ps2 = conn.prepareStatement(logSQL)) {

            ps2.setString(1, book.getTitle());
            ps2.setString(2, book.getAuthor());
            ps2.setString(3, book.getCategory());
            ps2.setString(4, book.getStatus().getValue());
            ps2.setString(5, book.getAvailability().getValue());
            ps2.setString(6, "Insert");
            ps2.executeUpdate();

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setString(4, book.getStatus().getValue());
            ps.setString(5, book.getAvailability().getValue());
            ps.executeUpdate();
            
            conn.commit();
            System.out.println("Book Added Successfully");
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        }
    }

    public void updateBookDetails(Book book) throws SQLException {
        if (conn == null || conn.isClosed()) conn = DBUtil.getConnection();
        String updateSQL = "UPDATE books SET Title=?, Author=?, Category=?, Status=?, Availability=? WHERE BookId=?";
        String logSQL = "INSERT INTO book_log (BookId, Title, Author, Category, Status, Availability, OperationType) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(updateSQL);
             PreparedStatement ps2 = conn.prepareStatement(logSQL)) {

            ps2.setInt(1, book.getBookId());
            ps2.setString(2, book.getTitle());
            ps2.setString(3, book.getAuthor());
            ps2.setString(4, book.getCategory());
            ps2.setString(5, book.getStatus().getValue());
            ps2.setString(6, book.getAvailability().getValue());
            ps2.setString(7, "Update");
            ps2.executeUpdate();

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setString(4, book.getStatus().getValue());
            ps.setString(5, book.getAvailability().getValue());
            ps.setInt(6, book.getBookId());
            ps.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        }
    }

    public void updateAvailability(int bookId, Availability availability) throws SQLException {
        if (conn == null || conn.isClosed()) conn = DBUtil.getConnection();
        String sql = "UPDATE books SET Availability=? WHERE BookId=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, availability.getValue());
            ps.setInt(2, bookId);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        }
    }

    public List<Book> getAllBooks() throws SQLException {
        if (conn == null || conn.isClosed()) conn = DBUtil.getConnection();
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Book b = new Book();
                b.setBookId(rs.getInt("BookId"));
                b.setTitle(rs.getString("Title"));
                b.setAuthor(rs.getString("Author"));
                b.setCategory(rs.getString("Category"));
//                b.setStatus(Status.valueOf(rs.getString("Status")));
//                b.setAvailability(Availability.valueOf(rs.getString("Availability")));
                b.setStatus(Status.fromValue(rs.getString("Status")));
                b.setAvailability(Availability.fromValue(rs.getString("Availability")));
                books.add(b);
            }
        }
        return books;
    }

    public Book getBookById(int bookId) throws SQLException {
        if (conn == null || conn.isClosed()) conn = DBUtil.getConnection();
        Book b = null;
        String sql = "SELECT * FROM books WHERE BookId=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                b = new Book();
                b.setBookId(rs.getInt("BookId"));
                b.setTitle(rs.getString("Title"));
                b.setAuthor(rs.getString("Author"));
                b.setCategory(rs.getString("Category"));
                b.setStatus(Status.valueOf(rs.getString("Status")));
                b.setAvailability(Availability.valueOf(rs.getString("Availability")));
            }
        }
        return b;
    }
}




