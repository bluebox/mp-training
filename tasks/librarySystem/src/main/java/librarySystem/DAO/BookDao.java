package librarySystem.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import librarySystem.Utils.DbConnection;
import model.BookPojo;

public class BookDao {

    public List<BookPojo> getBooks() {
        List<BookPojo> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BookPojo book = new BookPojo();
                book.setBookId(rs.getInt("BookId"));
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setCategory(rs.getString("Category"));
                book.setStatus(rs.getString("Status").charAt(0));
                book.setAvailability(rs.getString("Availablity").charAt(0));
                books.add(book);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching books: " + e.getMessage());
        }

        return books;
    }
    
    //overloaded isPresent for using already established connection
    public static boolean isPresent(BookPojo book, Connection conn) {
        String sql = "SELECT COUNT(*) FROM books WHERE Title = ? AND Author = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            ResultSet rs = stmt.executeQuery();

            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.err.println("Error checking if book exists: " + e.getMessage());
        }
        return false;
    }

    
    
    public static boolean isPresent(BookPojo book) {
        try (Connection conn = DbConnection.getConnection()) {
            return isPresent(book, conn); // reuse logic
        } catch (SQLException e) {
            System.err.println("Error checking if book exists: " + e.getMessage());
            return false;
        }
    }


    public static BookPojo getBook(BookPojo book) {
        String sql = "SELECT * FROM books WHERE Title = ? AND Author = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                BookPojo copyBook = new BookPojo();
                copyBook.setBookId(rs.getInt("BookId"));
                copyBook.setTitle(rs.getString("Title"));
                copyBook.setAuthor(rs.getString("Author"));
                copyBook.setCategory(rs.getString("Category"));
                copyBook.setStatus(rs.getString("Status").charAt(0));
                copyBook.setAvailability(rs.getString("Availablity").charAt(0));
                return copyBook;
            }

        } catch (SQLException e) {
            System.err.println("Error fetching book by title and author: " + e.getMessage());
        }

        return null;
    }

    public boolean insertBook(BookPojo book) {
        String bookSql = "INSERT INTO books (Title, Author, Category, Status, Availablity) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = DbConnection.getConnection();
            conn.setAutoCommit(false);

            // Use connection-safe isPresent
            if (isPresent(book, conn)) {
                System.out.println("Book already Present");
                conn.rollback();  // rollback since you started a transaction
                return false;
            }

            try (PreparedStatement stmt = conn.prepareStatement(bookSql)) {
                stmt.setString(1, book.getTitle());
                stmt.setString(2, book.getAuthor());
                stmt.setString(3, book.getCategory());
                stmt.setString(4, String.valueOf(book.getStatus()));
                stmt.setString(5, String.valueOf(book.getAvailability()));
                stmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Book inserted successfully.");
            return true;

        } catch (SQLException e) {
            System.err.println("Error inserting book: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Rolled back transaction.");
                } catch (SQLException rollbackEx) {
                    System.err.println("Rollback failed: " + rollbackEx.getMessage());
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    System.err.println("Failed to close connection: " + closeEx.getMessage());
                }
            }
        }

        return false;
    }


    public boolean updateBookDetails(BookPojo oldBook, BookPojo newBook) {
        String updateSql = "UPDATE books SET Title = ?, Author = ?, Category = ?, Status = ? WHERE BookId = ?";
        String logSql = "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availablity) VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = null;

        try {
            conn = DbConnection.getConnection();
            conn.setAutoCommit(false);

            BookPojo currentBook = getBook(oldBook);
            if (currentBook == null) {
                return false;
            }

            int bookId = currentBook.getBookId();

            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                 PreparedStatement logStmt = conn.prepareStatement(logSql)) {

                updateStmt.setString(1, newBook.getTitle());
                updateStmt.setString(2, newBook.getAuthor());
                updateStmt.setString(3, newBook.getCategory());
                updateStmt.setString(4, String.valueOf(newBook.getStatus()));
                updateStmt.setInt(5, bookId);
                updateStmt.addBatch();

                logStmt.setInt(1, bookId);
                logStmt.setString(2, newBook.getTitle());
                logStmt.setString(3, newBook.getAuthor());
                logStmt.setString(4, newBook.getCategory());
                logStmt.setString(5, String.valueOf(newBook.getStatus()));
                logStmt.setString(6, String.valueOf(currentBook.getAvailability()));
                logStmt.addBatch();

                updateStmt.executeBatch();
                logStmt.executeBatch();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("Error updating book details: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Rolled back transaction.");
                } catch (SQLException ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    System.err.println("Failed to close connection: " + closeEx.getMessage());
                }
            }
        }

        return false;
    }

    public boolean updateBookAvailability(int bookId, char availability) {
        String updateSql = "UPDATE books SET Availablity = ? WHERE BookId = ?";
        String logSql = "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availablity) VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = null;

        try {
            conn = DbConnection.getConnection();
            conn.setAutoCommit(false);

            BookPojo book = getBookById(bookId);
            if (book == null) return false;

            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                 PreparedStatement logStmt = conn.prepareStatement(logSql)) {

                updateStmt.setString(1, String.valueOf(availability));
                updateStmt.setInt(2, bookId);
                updateStmt.addBatch();

                logStmt.setInt(1, bookId);
                logStmt.setString(2, book.getTitle());
                logStmt.setString(3, book.getAuthor());
                logStmt.setString(4, book.getCategory());
                logStmt.setString(5, String.valueOf(book.getStatus()));
                logStmt.setString(6, String.valueOf(availability));
                logStmt.addBatch();

                updateStmt.executeBatch();
                logStmt.executeBatch();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("Error updating book availability: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Rolled back transaction.");
                } catch (SQLException ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    System.err.println("Failed to close connection: " + closeEx.getMessage());
                }
            }
        }

        return false;
    }

    public BookPojo getBookById(int bookId) {
        String sql = "SELECT * FROM books WHERE BookId = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    BookPojo book = new BookPojo();
                    book.setBookId(rs.getInt("BookId"));
                    book.setTitle(rs.getString("Title"));
                    book.setAuthor(rs.getString("Author"));
                    book.setCategory(rs.getString("Category"));
                    book.setStatus(rs.getString("Status").charAt(0));
                    book.setAvailability(rs.getString("Availablity").charAt(0));
                    return book;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching book by ID: " + e.getMessage());
        }

        return null;
    }
}
