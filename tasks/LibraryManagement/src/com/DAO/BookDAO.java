package com.DAO;

import com.models.Book;
import java.sql.*;

public class BookDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "pavan";
    private static final String PASSWORD = "Pavan@02";

    public boolean save(Book book) {
    	
        String sql = "INSERT INTO Book (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)";
//        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setString(4, String.valueOf(book.getStatus()));
            stmt.setString(5, String.valueOf(book.getAvailability()));

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
            
        }
    }
    public boolean update(Book book) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setAutoCommit(false);

            
            String sqlSelect = "SELECT * FROM Book WHERE BookId = ?";
            Book existingBook = null;

            try (PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect)) {
                stmtSelect.setInt(1, book.getId());

                try (ResultSet rs = stmtSelect.executeQuery()) {
                    if (rs.next()) {
                        existingBook = new Book(
                            rs.getInt("BookId"),
                            rs.getString("Title"),
                            rs.getString("Author"),
                            rs.getString("Category"),
                            rs.getString("Status").charAt(0),
                            rs.getString("Availability").charAt(0)
                        );
                    } else {
                        System.out.println("No book found with ID: " + book.getId());
                        return false;
                    }
                }
            }

            
            String sqlLog = "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmtLog = conn.prepareStatement(sqlLog)) {
                stmtLog.setInt(1, existingBook.getId());
                stmtLog.setString(2, existingBook.getTitle());
                stmtLog.setString(3, existingBook.getAuthor());
                stmtLog.setString(4, existingBook.getCategory());
                stmtLog.setString(5, String.valueOf(existingBook.getStatus()));
                stmtLog.setString(6, String.valueOf(existingBook.getAvailability()));
                stmtLog.executeUpdate();
            }

            
            String sqlUpdate = "UPDATE Book SET Title = ?, Author = ?, Category = ?, Status = ?, Availability = ? WHERE BookId = ?";
            try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
                stmtUpdate.setString(1, book.getTitle());
                stmtUpdate.setString(2, book.getAuthor());
                stmtUpdate.setString(3, book.getCategory());
                stmtUpdate.setString(4, String.valueOf(book.getStatus()));
                stmtUpdate.setString(5, String.valueOf(book.getAvailability()));
                stmtUpdate.setInt(6, book.getId());

                int rowsUpdated = stmtUpdate.executeUpdate();
                conn.commit();
                return rowsUpdated > 0;
            }

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean returnBook(int bookId) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE Book SET availability = 'A' WHERE BookId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookId);

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
