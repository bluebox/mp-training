package PustakaLokam.library.dao;

import PustakaLokam.library.enums.AvailabilityStatus;
import PustakaLokam.library.enums.BookCondition;
import PustakaLokam.library.model.Book;
import PustakaLokam.library.utilities.DBConnectivityUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public boolean insertBook(Book book) throws SQLException {
        String sqlQuery = "INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectivityUtility.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getCategory());
            String bookCondition = (book.getCondition() == BookCondition.ACTIVE ? "Active" : "Inactive");
            statement.setString(4, bookCondition);
            String bookAvailability = (book.getAvailability() == AvailabilityStatus.AVAILABLE ? "Available"
                    : "Issued");
            statement.setString(5, bookAvailability);

            return statement.executeUpdate() > 0;
        }
    }

    public boolean updateBookDetails(Book book) {
        String query = "UPDATE books SET Title = ?, Author = ?, Category = ? WHERE BookID = ?";

        try (Connection conn = DBConnectivityUtility.getConnection();
                PreparedStatement statement = conn.prepareStatement(query)) {

            int bookID = book.getBookID();
            String bookTitle = book.getTitle();
            String author = book.getAuthor();
            String category = book.getCategory();

            statement.setString(1, bookTitle);
            statement.setString(2, author);
            statement.setString(3, category);
            statement.setInt(4, bookID);

            return statement.executeUpdate() > 0;

        } catch (SQLException SQLE) {
            SQLE.printStackTrace();
            return false;
        }
    }

    // public boolean updateBookAvailability(int bookID, AvailabilityStatus
    // availabilityStatus) {
    // String sqlQuery = "UPDATE books SET Availability = ? WHERE BookID = ?";

    // try (Connection conn = DBConnectivityUtility.getConnection();
    // PreparedStatement statement = conn.prepareStatement(sqlQuery)) {
    // String availabilityStatusStr = (availabilityStatus ==
    // AvailabilityStatus.AVAILABLE) ? "Available"
    // : "Issued";
    // statement.setString(1, availabilityStatusStr);
    // statement.setInt(2, bookID);

    // int recordsUpdated = statement.executeUpdate();
    // return recordsUpdated > 0;

    // } catch (SQLException SQLE) {
    // SQLE.printStackTrace();
    // return false;
    // }
    // }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        String sqlQuery = "SELECT * FROM books";
        try (Connection conn = DBConnectivityUtility.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);

                ResultSet setOfBooks = statement.executeQuery()) {
            while (setOfBooks.next()) {
                Book book = new Book();
                book.setBookID(setOfBooks.getInt("BookID"));
                book.setAuthor(setOfBooks.getString("Author"));
                book.setTitle(setOfBooks.getString("Title"));
                book.setCategory(setOfBooks.getString("Category"));

                String readAvailabilityFromTable = setOfBooks.getString("Availability");
                if ("Available".equalsIgnoreCase(readAvailabilityFromTable)) {
                    book.setAvailability(AvailabilityStatus.AVAILABLE);
                } else {
                    book.setAvailability(AvailabilityStatus.ISSUED);
                }
                String readConditionFromTable = setOfBooks.getString("Status");
                if ("Active".equalsIgnoreCase(readConditionFromTable)) {
                    book.setCondition(BookCondition.ACTIVE);
                } else {
                    book.setCondition(BookCondition.INACTIVE);
                }

                books.add(book);
            }
        } catch (SQLException SQLE) {
            SQLE.printStackTrace();
        }
        return books;
    }

    public Book getBookByID(int bookID) throws SQLException {
        String sqlQuery = "SELECT * FROM books WHERE bookID = ?";
        try (Connection conn = DBConnectivityUtility.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery)) {
            statement.setInt(1, bookID);
            try (ResultSet resultBook = statement.executeQuery()) {
                if (resultBook.next()) {
                    Book book = new Book();
                    book.setBookID(resultBook.getInt("BookID"));
                    book.setTitle(resultBook.getString("Title"));
                    book.setAuthor(resultBook.getString("Author"));
                    book.setCategory(resultBook.getString("Category"));

                    if ("Available".equalsIgnoreCase(resultBook.getString("Availability"))) {
                        book.setAvailability(AvailabilityStatus.AVAILABLE);
                    } else {
                        book.setAvailability(AvailabilityStatus.ISSUED);
                    }

                    if ("Active".equalsIgnoreCase(resultBook.getString("Status"))) {
                        book.setCondition(BookCondition.ACTIVE);
                    } else {
                        book.setCondition(BookCondition.INACTIVE);
                    }

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
        String sqlQuery = "INSERT INTO books(Title, Author, Category, Status, Availability) VALUES (?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = DBConnectivityUtility.getConnection();
            conn.setAutoCommit(false);
            try (
                    PreparedStatement statement = conn.prepareStatement(sqlQuery)) {

                for (int i = 0; i < books.size(); i++) {
                    statement.setString(1, books.get(i).getTitle());
                    statement.setString(2, books.get(i).getAuthor());
                    statement.setString(3, books.get(i).getCategory());

                    String bookCondition = (books.get(i).getCondition() == BookCondition.ACTIVE ? "Active"
                            : "Inactive");
                    statement.setString(4, bookCondition);
                    String bookAvailability = (books.get(i).getAvailability() == AvailabilityStatus.AVAILABLE
                            ? "Available"
                            : "Issued");
                    statement.setString(5, bookAvailability);
                    statement.addBatch();
                }

                int[] acknowledgements = statement.executeBatch();
                conn.commit();

                for (int i = 0; i < acknowledgements.length; i++) {
                    if (acknowledgements[i] == Statement.EXECUTE_FAILED) {
                        return false;
                    }
                }
                return true;
            }
        } catch (SQLException SQLE) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            SQLE.printStackTrace();
            return false;
        }
    }
}
