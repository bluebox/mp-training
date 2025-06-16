package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pojo.Book;
import pojo.Book.Availability;
import pojo.Book.Status;
import utility.DBUtil;
import utility.DBqueries;

public class BookDAO {

    public void addBook(Book book) throws SQLException {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DBqueries.INSERT_BOOK)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getCategory());
            preparedStatement.setString(4, book.getStatus().toString().substring(0, 1));
            preparedStatement.setString(5, book.getAvailability().toString().substring(0, 1));
            preparedStatement.executeUpdate();
        }
    }

    public void updateBookDetails(Book book) throws SQLException {
        try (Connection connection = DBUtil.getConnection()) { 
            connection.setAutoCommit(false);
            try (PreparedStatement LogStmt = connection.prepareStatement(DBqueries.INSERT_BOOK_LOG)) {
                LogStmt.setInt(1, book.getBookid());
                LogStmt.executeUpdate();
            }

            try (PreparedStatement updateStmt = connection.prepareStatement(DBqueries.UPDATE_BOOK_DETAILS)) {
                updateStmt.setString(1, book.getTitle());
                updateStmt.setString(2, book.getAuthor());
                updateStmt.setString(3, book.getCategory());
                updateStmt.setString(4, book.getStatus().toString().substring(0, 1));
                updateStmt.setInt(5, book.getBookid());
                updateStmt.executeUpdate();
            }
            connection.commit();
        }
    }
    
    public void updateBookAvailability(int bookId, Availability availability) throws SQLException {
        try (Connection connection = DBUtil.getConnection()) { 
            connection.setAutoCommit(false);
            try (PreparedStatement LogStmt = connection.prepareStatement(DBqueries.INSERT_BOOK_LOG)) {
                LogStmt.setInt(1, bookId);
                LogStmt.executeUpdate();
            }
            try (PreparedStatement updateStmt = connection.prepareStatement(DBqueries.UPDATE_BOOK_AVAILABILITY)) {
                updateStmt.setString(1, availability.toString().substring(0, 1));
                updateStmt.setInt(2, bookId);
                updateStmt.executeUpdate();
            }
            connection.commit(); 
        }
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DBqueries.SELECT_ALL_BOOKS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookid(resultSet.getInt("bookid"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setCategory(resultSet.getString("category"));
                book.setStatus(Status.valueOf(resultSet.getString("status").equals("A") ? "ACTIVE" : "INACTIVE"));
                book.setAvailability(Availability.valueOf(resultSet.getString("availability").equals("A") ? "AVAILABLE" : "ISSUED"));
                books.add(book);
            }
        }
        return books;
    }

    public Book getBookById(int bookId) throws SQLException {
        Book book = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books WHERE bookid = ?")) {
            preparedStatement.setInt(1, bookId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    book = new Book();
                    book.setBookid(resultSet.getInt("bookid"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setCategory(resultSet.getString("category"));
                    book.setStatus(Status.valueOf(resultSet.getString("status").equals("A") ? "ACTIVE" : "INACTIVE"));
                    book.setAvailability(Availability.valueOf(resultSet.getString("availability").equals("A") ? "AVAILABLE" : "ISSUED"));
                }
            }
        }
        return book;
    }
}