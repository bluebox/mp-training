package com.lms.daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.util.DBUtil;

public class BookDao {
    private static BookDao instance = new BookDao();

    private BookDao() {}

    public static BookDao getInstance() {
        return instance;
    }

    public Book addBook(String title, String author, BookCategory category, char status, char availability) {
        String query = "INSERT INTO books (title, author, category, status, availability) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, category.name());
            stmt.setString(4, String.valueOf(status));
            stmt.setString(5, String.valueOf(availability));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    PreparedStatement getStmt = conn.prepareStatement("SELECT book_id FROM books WHERE id = ?");
                    getStmt.setInt(1, id);
                    ResultSet bookRs = getStmt.executeQuery();
                    if (bookRs.next()) {
                        String bookId = bookRs.getString("book_id");
                        return new Book(bookId, title, author, category, status, availability);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateBook(String bookId, String title, String author, BookCategory category, char status, char availability) {
        String query = "UPDATE books SET title = ?, author = ?, category = ?, status = ?, availability = ? WHERE book_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, category.name());
            stmt.setString(4, String.valueOf(status));
            stmt.setString(5, String.valueOf(availability));
            stmt.setString(6, bookId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT book_id, title, author, category, status, availability FROM books";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                books.add(new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        BookCategory.valueOf(rs.getString("category")),
                        rs.getString("status").charAt(0),
                        rs.getString("availability").charAt(0)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book getBookById(String bookId) {
        String query = "SELECT * FROM books WHERE book_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, bookId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        BookCategory.valueOf(rs.getString("category")),
                        rs.getString("status").charAt(0),
                        rs.getString("availability").charAt(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ NEW METHOD: Get available books by category
    public List<Book> getAvailableBooksByCategory(BookCategory category) {
        List<Book> availableBooks = new ArrayList<>();
        String query = "SELECT book_id, title, author, category, status, availability FROM books WHERE category = ? AND availability = 'A'";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, category.name());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                availableBooks.add(new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        BookCategory.valueOf(rs.getString("category")),
                        rs.getString("status").charAt(0),
                        rs.getString("availability").charAt(0)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableBooks;
    }
}
