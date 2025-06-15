package dao;

import model.Book;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public boolean insertBookWithTransaction(Book book) throws Exception {
        Connection con = null;
        PreparedStatement ps1 = null, ps2 = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            ps1 = con.prepareStatement(
                    "INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, book.getTitle());
            ps1.setString(2, book.getAuthor());
            ps1.setString(3, book.getCategory());
            ps1.setString(4, book.getStatus());
            ps1.setString(5, book.getAvailability());
            ps1.executeUpdate();

            // Get generated book ID
            rs = ps1.getGeneratedKeys();
            int bookId = 0;
            if (rs.next()) {
                bookId = rs.getInt(1);
            }

            // Insert into books_log
            ps2 = con.prepareStatement("INSERT INTO books_log (book_id, action, performed_by) VALUES (?, ?, ?)");
            ps2.setInt(1, bookId);
            ps2.setString(2, "INSERT");
            ps2.setString(3, "admin"); // You can make this dynamic if needed
            ps2.executeUpdate();

            con.commit();
            return true;
        } catch (Exception e) {
            if (con != null)
                con.rollback();
            throw e;
        } finally {
            if (ps1 != null)
                ps1.close();
            if (ps2 != null)
                ps2.close();
            if (con != null)
                con.setAutoCommit(true);
            if (con != null)
                con.close();
        }
    }

    // updation code
    public boolean updateBookDetails(Book book) throws Exception {
        String sql = "UPDATE books SET Title = ?, Author = ?, Category = ?, Status = ? WHERE BookId = ?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setString(4, book.getStatus());
            ps.setInt(5, book.getBookId());

            int rows = ps.executeUpdate();

            // Optional: insert log
            if (rows > 0) {
                insertLog(book.getBookId(), "Updated Book Details", "Admin");
            }

            return rows > 0;
        }
    }

    private void insertLog(int bookId, String action, String performedBy) throws Exception {
        String sql = "INSERT INTO books_log (book_id, action, performed_by) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            ps.setString(2, action);
            ps.setString(3, performedBy);
            ps.executeUpdate();
        }
    }

    // update availability
    public boolean updateAvailability(int bookId, String availability) throws Exception {
        String sql = "UPDATE books SET Availability = ? WHERE BookId = ?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, availability);
            ps.setInt(2, bookId);

            int rows = ps.executeUpdate();
//log
            if (rows > 0) {
                insertLog(bookId, "Updated Availability to " + availability, "Admin");
            }

            return rows > 0;
        }
    }

    // get all books
    public List<Book> getAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("BookId"));
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setCategory(rs.getString("Category"));
                book.setStatus(rs.getString("Status"));
                book.setAvailability(rs.getString("Availability"));
                books.add(book);
            }
        }
        return books;
    }

    // delete book
    public boolean deleteBook(int bookId) throws Exception {
        String sql = "DELETE FROM books WHERE BookId = ?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);
            int rows = ps.executeUpdate();

            // Optional: insert log
            if (rows > 0) {
                insertLog(bookId, "Deleted Book", "Admin");
            }

            return rows > 0;
        }
    }

}
