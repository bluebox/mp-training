package Library.src.main.java.com.LibraryManagement.dao;
import java.sql.*;
import java.util.*;
import Library.src.main.java.com.LibraryManagement.model.*;


public class BookDAOImpl implements BookDAO {

    private final Connection connection;

    public BookDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setString(4, String.valueOf(book.getStatus()));
            ps.setString(5, String.valueOf(book.getAvailability()));
            ps.executeUpdate();
        }
    }
    
    @Override
    public void updateBook(Book book) throws SQLException {
        String sqlUpdate = "UPDATE books SET Title=?, Author=?, Category=?, Status=? WHERE BookId=?";
        String sqlInsertLog = "INSERT INTO book_logs (BookId, Title, Author, Category, Status, Time) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlSelectOld = "SELECT * FROM books WHERE BookId=?";

        // Start transaction
        try {
            connection.setAutoCommit(false); // Begin transaction

            // 1. Get the old book data before update
            Book oldBook = null;
            try (PreparedStatement ps = connection.prepareStatement(sqlSelectOld)) {
                ps.setInt(1, book.getBookId());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        oldBook = new Book();
                        oldBook.setBookId(rs.getInt("BookId"));
                        oldBook.setTitle(rs.getString("Title"));
                        oldBook.setAuthor(rs.getString("Author"));
                        oldBook.setCategory(rs.getString("Category"));
                        oldBook.setStatus(rs.getString("Status").charAt(0)); // Assuming 'Status' is CHAR(1)
                    }
                }
            }

            // 2. Insert the old data into book_logs
            if (oldBook != null) {
                try (PreparedStatement ps = connection.prepareStatement(sqlInsertLog)) {
                    ps.setInt(1, oldBook.getBookId());
                    ps.setString(2, oldBook.getTitle());
                    ps.setString(3, oldBook.getAuthor());
                    ps.setString(4, oldBook.getCategory());
                    ps.setString(5, String.valueOf(oldBook.getStatus()));
                    ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
                    ps.executeUpdate();
                }
            }

            // 3. Now update the books table with the new data
            try (PreparedStatement ps = connection.prepareStatement(sqlUpdate)) {
                ps.setString(1, book.getTitle());
                ps.setString(2, book.getAuthor());
                ps.setString(3, book.getCategory());
                ps.setString(4, String.valueOf(book.getStatus()));
                ps.setInt(5, book.getBookId());
                ps.executeUpdate();
            }

            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            connection.rollback(); // Rollback if anything fails
            throw e;
        } finally {
            connection.setAutoCommit(true); // Restore auto-commit mode
        }
    }


        
    
    @Override
    public void updateBookAvailability(int bookId, char newAvailability) throws SQLException {
        String getOldSql = "SELECT Availability FROM books WHERE BookId=?";
        String updateSql = "UPDATE books SET Availability=? WHERE BookId=?";
        String logSql = "INSERT INTO bookUpdate_logs (BookId, Availability, Time) VALUES (?, ?, ?)";

        try {
            connection.setAutoCommit(false); // Begin transaction

            char oldAvailability;

            // Step 1: Fetch previous availability
            try (PreparedStatement ps = connection.prepareStatement(getOldSql)) {
                ps.setInt(1, bookId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        oldAvailability = rs.getString("Availability").charAt(0);
                    } else {
                        throw new SQLException("Book not found with ID: " + bookId);
                    }
                }
            }

            // Step 2: Insert log of previous value
            try (PreparedStatement ps = connection.prepareStatement(logSql)) {
                ps.setInt(1, bookId);
                ps.setString(2, String.valueOf(oldAvailability));
                ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                ps.executeUpdate();
            }

            // Step 3: Update availability
            try (PreparedStatement ps = connection.prepareStatement(updateSql)) {
                ps.setString(1, String.valueOf(newAvailability));
                ps.setInt(2, bookId);
                ps.executeUpdate();
            }

            connection.commit(); // All successful
        } catch (SQLException e) {
            connection.rollback(); // Roll back if any part fails
            throw e;
        } finally {
            connection.setAutoCommit(true); // Restore default
        }
    }


    @Override
    public Book getBookById(int bookId) throws SQLException {
        String sql = "SELECT BookId,Title,Author,Category,Status,Availability FROM books WHERE BookId=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book b = new Book();
                    b.setBookId(rs.getInt("BookId"));
                    b.setTitle(rs.getString("Title"));
                    b.setAuthor(rs.getString("Author"));
                    b.setCategory(rs.getString("Category"));
                    b.setStatus(rs.getString("Status").charAt(0));
                    b.setAvailability(rs.getString("Availability").charAt(0));
                    return b;
                }
            }
        }
        return null;
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();

        String sql="Select BookId,Title,Author,Category,Status,Availability from books";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Book b = new Book();
                b.setBookId(rs.getInt("BookId"));
                b.setTitle(rs.getString("Title"));
                b.setAuthor(rs.getString("Author"));
                b.setCategory(rs.getString("Category"));
                b.setStatus(rs.getString("Status").charAt(0));
                b.setAvailability(rs.getString("Availability").charAt(0));
                books.add(b);
            }
        }
        return books;
    }
    
    public Map<String,Integer> getCategoryCount() throws SQLException {
    	Map<String,Integer>map=new HashMap<>();
    	String sql="select Category,count(*) from books group by Category";
    	try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
               while (rs.next()) {
                   String category=rs.getString(0);
                   int count=rs.getInt(1);
                   map.put(category, count);
               }
           }
    	return map;
    }
}



//
//package com.LibraryManagement.dao;
//
//import com.LibraryManagement.model.Book;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BookDAOImpl implements BookDAO {
//
//    private final Connection connection;
//
//    public BookDAOImpl(Connection connection) {
//        this.connection = connection;
//    }
//
//    @Override
//    public void addBook(Book book) throws SQLException {
//        String insertSql = "INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)";
//        String logSql = "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availability, Action, LogTime) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
//
//        try {
//            connection.setAutoCommit(false);
//
//            try (PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
//                ps.setString(1, book.getTitle());
//                ps.setString(2, book.getAuthor());
//                ps.setString(3, book.getCategory());
//                ps.setString(4, String.valueOf(book.getStatus()));
//                ps.setString(5, String.valueOf(book.getAvailability()));
//                ps.executeUpdate();
//
//                ResultSet rs = ps.getGeneratedKeys();
//                if (rs.next()) {
//                    int bookId = rs.getInt(1);
//                    book.setBookId(bookId);
//
//                    try (PreparedStatement logPs = connection.prepareStatement(logSql)) {
//                        logPs.setInt(1, bookId);
//                        logPs.setString(2, book.getTitle());
//                        logPs.setString(3, book.getAuthor());
//                        logPs.setString(4, book.getCategory());
//                        logPs.setString(5, String.valueOf(book.getStatus()));
//                        logPs.setString(6, String.valueOf(book.getAvailability()));
//                        logPs.setString(7, "INSERT");
//                        logPs.executeUpdate();
//                    }
//                }
//            }
//
//            connection.commit();
//        } catch (SQLException e) {
//            connection.rollback();
//            throw e;
//        } finally {
//            connection.setAutoCommit(true);
//        }
//    }
//
//    @Override
//    public void updateBook(Book book) throws SQLException {
//        String updateSql = "UPDATE books SET Title=?, Author=?, Category=?, Status=?, Availability=? WHERE BookId=?";
//        String logSql = "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availability, Action, LogTime) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
//
//        try {
//            connection.setAutoCommit(false);
//
//            try (PreparedStatement ps = connection.prepareStatement(updateSql)) {
//                ps.setString(1, book.getTitle());
//                ps.setString(2, book.getAuthor());
//                ps.setString(3, book.getCategory());
//                ps.setString(4, String.valueOf(book.getStatus()));
//                ps.setString(5, String.valueOf(book.getAvailability()));
//                ps.setInt(6, book.getBookId());
//                ps.executeUpdate();
//            }
//
//            try (PreparedStatement logPs = connection.prepareStatement(logSql)) {
//                logPs.setInt(1, book.getBookId());
//                logPs.setString(2, book.getTitle());
//                logPs.setString(3, book.getAuthor());
//                logPs.setString(4, book.getCategory());
//                logPs.setString(5, String.valueOf(book.getStatus()));
//                logPs.setString(6, String.valueOf(book.getAvailability()));
//                logPs.setString(7, "UPDATE");
//                logPs.executeUpdate();
//            }
//
//            connection.commit();
//        } catch (SQLException e) {
//            connection.rollback();
//            throw e;
//        } finally {
//            connection.setAutoCommit(true);
//        }
//    }
//
//    @Override
//    public List<Book> getAllBooks() throws SQLException {
//        List<Book> books = new ArrayList<>();
//        String sql = "SELECT * FROM books";
//        try (PreparedStatement ps = connection.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//            while (rs.next()) {
//                Book book = new Book();
//                book.setBookId(rs.getInt("BookId"));
//                book.setTitle(rs.getString("Title"));
//                book.setAuthor(rs.getString("Author"));
//                book.setCategory(rs.getString("Category"));
//                book.setStatus(rs.getString("Status").charAt(0));
//                book.setAvailability(rs.getString("Availability").charAt(0));
//                books.add(book);
//            }
//        }
//        return books;
//    }
//}
//
//
//
//
