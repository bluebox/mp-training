package PustakaLokam.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import PustakaLokam.library.enums.AvailabilityStatus;
import PustakaLokam.library.enums.BookCondition;
import PustakaLokam.library.exceptionhandler.BookNotFoundException;
import PustakaLokam.library.exceptionhandler.DatabaseOperationException;
import PustakaLokam.library.model.Book;
import PustakaLokam.library.utilities.DBConnectivityUtility;

public class BookDAO {

    public boolean insertBook(Book book) throws SQLException {
        String sqlQuery = "INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectivityUtility.getConnection();
             PreparedStatement statement = conn.prepareStatement(sqlQuery)) {

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getCategory());
            statement.setString(4, book.getCondition().getCode());
            statement.setString(5, book.getAvailability().name().equals("AVAILABLE") ? "Available" : "Issued");

            return statement.executeUpdate() > 0;
        }
    }

    public boolean updateBookDetails(Book book) {
        String query = "UPDATE books SET Title = ?, Author = ?, Category = ?, Status = ?, Availability = ? WHERE BookID = ?";

        try (Connection conn = DBConnectivityUtility.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getCategory());
            statement.setString(4, book.getCondition().getCode());
            statement.setString(5, book.getAvailability().name().equals("AVAILABLE") ? "Available" : "Issued");
            statement.setInt(6, book.getBookID());

            int updatedRows = statement.executeUpdate();
            
            // if none of the rows got updated, it indicates the book with given ID does not exist in the table.
            if (updatedRows == 0) {
                throw new BookNotFoundException("Book with ID " + book.getBookID() + " not found for update.");
            }
            
            // logging the successful update to books table into books_log table
            insertBookLog(book);
            return true;

        } catch (SQLException SQLE) {
            throw new DatabaseOperationException("Failed to update book with ID " + book.getBookID(), SQLE);
        }
    }


    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        String sqlQuery = "SELECT BookID, Title, Author, Category, Status, Avalilability FROM books";
        try (Connection conn = DBConnectivityUtility.getConnection();
             PreparedStatement statement = conn.prepareStatement(sqlQuery);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Book book = new Book();
                book.setBookID(rs.getInt("BookID"));
                book.setAuthor(rs.getString("Author"));
                book.setTitle(rs.getString("Title"));
                book.setCategory(rs.getString("Category"));

                String status = rs.getString("Status"); // "A" or "I"
                book.setCondition("A".equalsIgnoreCase(status) ? BookCondition.ACTIVE : BookCondition.INACTIVE);

                String availability = rs.getString("Availability"); // "Available" or "Issued"
                book.setAvailability("Available".equalsIgnoreCase(availability)
                        ? AvailabilityStatus.AVAILABLE
                        : AvailabilityStatus.ISSUED);

                books.add(book);
            }
        } catch (SQLException SQLE) {
            SQLE.printStackTrace();
        }
        return books;
    }

    public Book getBookByID(int bookID) throws SQLException {
        String sqlQuery = "SELECT * FROM books WHERE BookID = ?";
        try (Connection conn = DBConnectivityUtility.getConnection();
             PreparedStatement statement = conn.prepareStatement(sqlQuery)) {

            statement.setInt(1, bookID);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setBookID(rs.getInt("BookID"));
                    book.setTitle(rs.getString("Title"));
                    book.setAuthor(rs.getString("Author"));
                    book.setCategory(rs.getString("Category"));

                    String status = rs.getString("Status");
                    book.setCondition("A".equalsIgnoreCase(status) ? BookCondition.ACTIVE : BookCondition.INACTIVE);

                    String availability = rs.getString("Availability");
                    book.setAvailability("Available".equalsIgnoreCase(availability)
                            ? AvailabilityStatus.AVAILABLE
                            : AvailabilityStatus.ISSUED);

                    return book;
                }
            }
        }
        return null;
    }

    public boolean removeBook(int bookID) throws SQLException {
        String sqlQuery = "DELETE FROM books WHERE BookID = ?";
        try (Connection conn = DBConnectivityUtility.getConnection();
             PreparedStatement statement = conn.prepareStatement(sqlQuery)) {
            statement.setInt(1, bookID);
            return statement.executeUpdate() > 0;
        }
    }

    public boolean insertBatchOfBooks(List<Book> books) {
        String sqlQuery = "INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = DBConnectivityUtility.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement statement = conn.prepareStatement(sqlQuery)) {
                for (Book book : books) {
                    statement.setString(1, book.getTitle());
                    statement.setString(2, book.getAuthor());
                    statement.setString(3, book.getCategory());
                    statement.setString(4, book.getCondition().getCode());
                    statement.setString(5, book.getAvailability().name().equals("AVAILABLE") ? "Available" : "Issued");
                    statement.addBatch();
                }

                int[] acknowledgements = statement.executeBatch();
                conn.commit();

                for (int ack : acknowledgements) {
                    if (ack == Statement.EXECUTE_FAILED) {
                        return false;
                    }
                }
                return true;
            }

        } catch (SQLException SQLE) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            SQLE.printStackTrace();
            return false;
        }
    }
    public void insertBookLog(Book book) throws SQLException {
        String query = "INSERT INTO books_log (BookID, Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnectivityUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, book.getBookID());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4, book.getCategory());
            stmt.setString(5, book.getCondition().getCode());
            stmt.setString(6, book.getAvailability().name().equals("AVAILABLE") ? "Available" : "Issued");

            stmt.executeUpdate();
        }
    }

    public void updateBookAvailability(int bookID, AvailabilityStatus status, Connection conn) throws SQLException {
        String sql = "UPDATE books SET Availability = ? WHERE BookID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            String availability = (status == AvailabilityStatus.AVAILABLE) ? "Available" : "Issued";
            ps.setString(1, availability);
            ps.setInt(2, bookID);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new SQLException("No book found with ID " + bookID + " to update availability.");
            }
        }
    }
}
