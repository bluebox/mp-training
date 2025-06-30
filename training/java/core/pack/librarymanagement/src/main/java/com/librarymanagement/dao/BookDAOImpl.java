package com.librarymanagement.dao;

import com.librarymanagement.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookDAOImpl implements BookDAO {

    private final DataSource dataSource;

    @Autowired
    public BookDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
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
        String fetchOld = "SELECT Title, Author, Category, Status FROM books WHERE BookId=?";
        String insertLog = "INSERT INTO book_logs (BookId, Title, Author, Category, Status, Time) VALUES (?, ?, ?, ?, ?, ?)";
        String updateBook = "UPDATE books SET Title=?, Author=?, Category=?, Status=? WHERE BookId=?";

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement psFetch = connection.prepareStatement(fetchOld)) {
                psFetch.setInt(1, book.getBookId());
                try (ResultSet rs = psFetch.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException("Book with ID " + book.getBookId() + " not found.");
                    }

                    String oldTitle = rs.getString("Title");
                    String oldAuthor = rs.getString("Author");
                    String oldCategory = rs.getString("Category");
                    String oldStatus = rs.getString("Status");

                    try (PreparedStatement psLog = connection.prepareStatement(insertLog)) {
                        psLog.setInt(1, book.getBookId());
                        psLog.setString(2, oldTitle);
                        psLog.setString(3, oldAuthor);
                        psLog.setString(4, oldCategory);
                        psLog.setString(5, oldStatus);
                        psLog.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
                        psLog.executeUpdate();
                    }

                    try (PreparedStatement psUpdate = connection.prepareStatement(updateBook)) {
                        psUpdate.setString(1, book.getTitle());
                        psUpdate.setString(2, book.getAuthor());
                        psUpdate.setString(3, book.getCategory());
                        psUpdate.setString(4, String.valueOf(book.getStatus()));
                        psUpdate.setInt(5, book.getBookId());
                        psUpdate.executeUpdate();
                    }

                    connection.commit();
                }
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    @Override
    public void updateBookAvailability(int bookId, char availability) throws SQLException {
        String fetchOld = "SELECT Availability FROM books WHERE BookId=?";
        String insertLog = "INSERT INTO bookUpdate_logs (BookId, Availability, Time) VALUES (?, ?, ?)";
        String updateBook = "UPDATE books SET Availability=? WHERE BookId=?";

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement psFetch = connection.prepareStatement(fetchOld)) {
                psFetch.setInt(1, bookId);
                try (ResultSet rs = psFetch.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException("Book with ID " + bookId + " not found.");
                    }

                    String oldAvailability = rs.getString("Availability");

                    try (PreparedStatement psLog = connection.prepareStatement(insertLog)) {
                        psLog.setInt(1, bookId);
                        psLog.setString(2, oldAvailability);
                        psLog.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                        psLog.executeUpdate();
                    }

                    try (PreparedStatement psUpdate = connection.prepareStatement(updateBook)) {
                        psUpdate.setString(1, String.valueOf(availability));
                        psUpdate.setInt(2, bookId);
                        psUpdate.executeUpdate();
                    }

                    connection.commit();
                }
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    @Override
    public Book getBookById(int bookId) throws SQLException {
        String sql = "SELECT BookId, Title, Author, Category, Status, Availability FROM books WHERE BookId=?";
        try ( Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToBook(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT BookId, Title, Author, Category, Status, Availability FROM books";

        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                books.add(mapRowToBook(rs));
            }
        }
        return books;
    }

    //@Override
    public Map<String, Integer> getCategoryCount() throws SQLException {
        Map<String, Integer> map = new HashMap<>();
        String sql = "SELECT Category, COUNT(*) FROM books GROUP BY Category";

        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String category = rs.getString(1);
                int count = rs.getInt(2);
                map.put(category, count);
            }
        }
        return map;
    }

    //@Override
    public List<Book> getOverdueBooks(List<Book> allBooks) {
        return allBooks.stream()
                .filter(book -> book.getStatus() == 'I')
                .collect(Collectors.toList());
    }

    //@Override
    public Map<String, Long> getBookCountPerCategory(List<Book> allBooks) {
        return allBooks.stream()
                .collect(Collectors.groupingBy(
                        Book::getCategory,
                        Collectors.counting()
                ));
    }

    private Book mapRowToBook(ResultSet rs) throws SQLException {
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

//package com.librarymanagement.dao;
//import com.librarymanagement.model.*;
//
//import java.sql.*;
//import java.util.*;
//import java.util.stream.Collectors;
//import org.springframework.stereotype.Component;
//@Component
//public class BookDAOImpl implements BookDAO {
//	
//    private final Connection connection;
//    public BookDAOImpl(Connection connection) {
//        this.connection = connection;
//    }
//    @Override
//    public void addBook(Book book) throws SQLException {
//        String sql = "INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)";
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setString(1, book.getTitle());
//            ps.setString(2, book.getAuthor());
//            ps.setString(3, book.getCategory());
//            ps.setString(4, String.valueOf(book.getStatus()));
//            ps.setString(5, String.valueOf(book.getAvailability()));
//            ps.executeUpdate();
//        }
//    }
//
//   @Override
//public void updateBook(Book book) throws SQLException {
//    String fetchOld = "SELECT Title, Author, Category, Status FROM books WHERE BookId=?";
//    String insertLog = "INSERT INTO book_logs (BookId, Title, Author, Category, Status, Time) VALUES (?, ?, ?, ?, ?, ?)";
//    String updateBook = "UPDATE books SET Title=?, Author=?, Category=?, Status=? WHERE BookId=?";
//
//    PreparedStatement psFetch = null;
//    PreparedStatement psLog = null;
//    PreparedStatement psUpdate = null;
//    ResultSet rs = null;
//
//    try {
//        connection.setAutoCommit(false); // 🔁 Begin manual transaction
//
//        // 1. Fetch existing book details
//        psFetch = connection.prepareStatement(fetchOld);
//        psFetch.setInt(1, book.getBookId());
//        rs = psFetch.executeQuery();
//
//        if (!rs.next()) {
//            throw new SQLException("Book with ID " + book.getBookId() + " not found.");
//        }
//
//        String oldTitle = rs.getString("Title");
//        String oldAuthor = rs.getString("Author");
//        String oldCategory = rs.getString("Category");
//        String oldStatus = rs.getString("Status");
//
//        // 2. Log old values
//        psLog = connection.prepareStatement(insertLog);
//        psLog.setInt(1, book.getBookId());
//        psLog.setString(2, oldTitle);
//        psLog.setString(3, oldAuthor);
//        psLog.setString(4, oldCategory);
//        psLog.setString(5, oldStatus);
//        psLog.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
//        psLog.executeUpdate();
//
//        // 3. Update book table with new values
//        psUpdate = connection.prepareStatement(updateBook);
//        psUpdate.setString(1, book.getTitle());
//        psUpdate.setString(2, book.getAuthor());
//        psUpdate.setString(3, book.getCategory());
//        psUpdate.setString(4, String.valueOf(book.getStatus()));
//        psUpdate.setInt(5, book.getBookId());
//        psUpdate.executeUpdate();
//
//        connection.commit(); // ✅ Commit all changes
//    } catch (SQLException e) {
//        if (connection != null) {
//            try {
//                connection.rollback(); // ❌ Rollback on error
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//        throw e; // rethrow the original exception
//    } finally {
//        // ✅ Clean up
//        if (rs != null) rs.close();
//        if (psFetch != null) psFetch.close();
//        if (psLog != null) psLog.close();
//        if (psUpdate != null) psUpdate.close();
//        connection.setAutoCommit(true); // Restore default auto-commit
//    }
//}
//
//        
//    
//    @Override
//public void updateBookAvailability(int bookId, char availability) throws SQLException {
//    String fetchOld = "SELECT Availability FROM books WHERE BookId=?";
//    String insertLog = "INSERT INTO bookUpdate_logs (BookId, Availability, Time) VALUES (?, ?, ?)";
//    String updateBook = "UPDATE books SET Availability=? WHERE BookId=?";
//
//    PreparedStatement psFetch = null;
//    PreparedStatement psLog = null;
//    PreparedStatement psUpdate = null;
//    ResultSet rs = null;
//
//    try {
//        connection.setAutoCommit(false); // 🔁 Begin manual transaction
//
//        // 1. Fetch old availability
//        psFetch = connection.prepareStatement(fetchOld);
//        psFetch.setInt(1, bookId);
//        rs = psFetch.executeQuery();
//
//        if (!rs.next()) {
//            throw new SQLException("Book with ID " + bookId + " not found.");
//        }
//
//        String oldAvailability = rs.getString("Availability");
//
//        // 2. Insert old availability into log
//        psLog = connection.prepareStatement(insertLog);
//        psLog.setInt(1, bookId);
//        psLog.setString(2, oldAvailability);
//        psLog.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
//        psLog.executeUpdate();
//
//        // 3. Update book availability
//        psUpdate = connection.prepareStatement(updateBook);
//        psUpdate.setString(1, String.valueOf(availability));
//        psUpdate.setInt(2, bookId);
//        psUpdate.executeUpdate();
//
//        connection.commit(); // ✅ Commit transaction
//    } catch (SQLException e) {
//        if (connection != null) {
//            try {
//                connection.rollback(); // ❌ Rollback on error
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//        throw e;
//    } finally {
//        // ✅ Clean up
//        if (rs != null) rs.close();
//        if (psFetch != null) psFetch.close();
//        if (psLog != null) psLog.close();
//        if (psUpdate != null) psUpdate.close();
//        connection.setAutoCommit(true); // Restore default
//    }
//}
//
//
//    @Override
//    public Book getBookById(int bookId) throws SQLException {
//        String sql = "SELECT BookId,Title,Author,Category,Status,Availability FROM books WHERE BookId=?";
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, bookId);
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    Book b = new Book();
//                    b.setBookId(rs.getInt("BookId"));
//                    b.setTitle(rs.getString("Title"));
//                    b.setAuthor(rs.getString("Author"));
//                    b.setCategory(rs.getString("Category"));
//                    b.setStatus(rs.getString("Status").charAt(0));
//                    b.setAvailability(rs.getString("Availability").charAt(0));
//                    return b;
//                }
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public List<Book> getAllBooks() throws SQLException {
//        List<Book> books = new ArrayList<>();
//
//        String sql="Select BookId,Title,Author,Category,Status,Availability from books";
//        try (Statement stmt = connection.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                Book b = new Book();
//                b.setBookId(rs.getInt("BookId"));
//                b.setTitle(rs.getString("Title"));
//                b.setAuthor(rs.getString("Author"));
//                b.setCategory(rs.getString("Category"));
//                b.setStatus(rs.getString("Status").charAt(0));
//                b.setAvailability(rs.getString("Availability").charAt(0));
//                books.add(b);
//            }
//        }
//        return books;
//    }
//    
//    public Map<String,Integer> getCategoryCount() throws SQLException {
//    	Map<String,Integer>map=new HashMap<>();
//    	String sql="select Category,count(*) from books group by Category";
//    	try (Statement stmt = connection.createStatement();
//                ResultSet rs = stmt.executeQuery(sql)) {
//               while (rs.next()) {
//                   String category=rs.getString(0);
//                   int count=rs.getInt(1);
//                   map.put(category, count);
//               }
//           }
//    	return map;
//    }
//    
// public List<Book> getOverdueBooks(List<Book> allBooks) {
//    return allBooks.stream()
//            .filter(book -> book.getStatus() == 'I') 
//            .collect(Collectors.toList());
//}
//
//public Map<String, Long> getBookCountPerCategory(List<Book> allBooks) {
//    return allBooks.stream()
//            .collect(Collectors.groupingBy(
//                Book::getCategory,
//                Collectors.counting()
//            ));
//}
//    
//}
//
//
//
////
////package com.LibraryManagement.dao;
////
////import com.LibraryManagement.model.Book;
////
////import java.sql.*;
////import java.util.ArrayList;
////import java.util.List;
////
////public class BookDAOImpl implements BookDAO {
////
////    private final Connection connection;
////
////    public BookDAOImpl(Connection connection) {
////        this.connection = connection;
////    }
////
////    @Override
////    public void addBook(Book book) throws SQLException {
////        String insertSql = "INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)";
////        String logSql = "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availability, Action, LogTime) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
////
////        try {
////            connection.setAutoCommit(false);
////
////            try (PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
////                ps.setString(1, book.getTitle());
////                ps.setString(2, book.getAuthor());
////                ps.setString(3, book.getCategory());
////                ps.setString(4, String.valueOf(book.getStatus()));
////                ps.setString(5, String.valueOf(book.getAvailability()));
////                ps.executeUpdate();
////
////                ResultSet rs = ps.getGeneratedKeys();
////                if (rs.next()) {
////                    int bookId = rs.getInt(1);
////                    book.setBookId(bookId);
////
////                    try (PreparedStatement logPs = connection.prepareStatement(logSql)) {
////                        logPs.setInt(1, bookId);
////                        logPs.setString(2, book.getTitle());
////                        logPs.setString(3, book.getAuthor());
////                        logPs.setString(4, book.getCategory());
////                        logPs.setString(5, String.valueOf(book.getStatus()));
////                        logPs.setString(6, String.valueOf(book.getAvailability()));
////                        logPs.setString(7, "INSERT");
////                        logPs.executeUpdate();
////                    }
////                }
////            }
////
////            connection.commit();
////        } catch (SQLException e) {
////            connection.rollback();
////            throw e;
////        } finally {
////            connection.setAutoCommit(true);
////        }
////    }
////
////    @Override
////    public void updateBook(Book book) throws SQLException {
////        String updateSql = "UPDATE books SET Title=?, Author=?, Category=?, Status=?, Availability=? WHERE BookId=?";
////        String logSql = "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availability, Action, LogTime) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
////
////        try {
////            connection.setAutoCommit(false);
////
////            try (PreparedStatement ps = connection.prepareStatement(updateSql)) {
////                ps.setString(1, book.getTitle());
////                ps.setString(2, book.getAuthor());
////                ps.setString(3, book.getCategory());
////                ps.setString(4, String.valueOf(book.getStatus()));
////                ps.setString(5, String.valueOf(book.getAvailability()));
////                ps.setInt(6, book.getBookId());
////                ps.executeUpdate();
////            }
////
////            try (PreparedStatement logPs = connection.prepareStatement(logSql)) {
////                logPs.setInt(1, book.getBookId());
////                logPs.setString(2, book.getTitle());
////                logPs.setString(3, book.getAuthor());
////                logPs.setString(4, book.getCategory());
////                logPs.setString(5, String.valueOf(book.getStatus()));
////                logPs.setString(6, String.valueOf(book.getAvailability()));
////                logPs.setString(7, "UPDATE");
////                logPs.executeUpdate();
////            }
////
////            connection.commit();
////        } catch (SQLException e) {
////            connection.rollback();
////            throw e;
////        } finally {
////            connection.setAutoCommit(true);
////        }
////    }
////
////    @Override
////    public List<Book> getAllBooks() throws SQLException {
////        List<Book> books = new ArrayList<>();
////        String sql = "SELECT * FROM books";
////        try (PreparedStatement ps = connection.prepareStatement(sql);
////             ResultSet rs = ps.executeQuery()) {
////            while (rs.next()) {
////                Book book = new Book();
////                book.setBookId(rs.getInt("BookId"));
////                book.setTitle(rs.getString("Title"));
////                book.setAuthor(rs.getString("Author"));
////                book.setCategory(rs.getString("Category"));
////                book.setStatus(rs.getString("Status").charAt(0));
////                book.setAvailability(rs.getString("Availability").charAt(0));
////                books.add(book);
////            }
////        }
////        return books;
////    }
////}
////
////
////
////
