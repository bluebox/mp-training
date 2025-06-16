package DAO;
import casestudy.Book; import casestudy.DatabaseUtil; import casestudy.LibraryException;import java.sql.*;
import java.util.ArrayList; import java.util.List;

public class BookDAO { public void addBook(Book book) throws LibraryException {
	String sql = "INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)"; 
	try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
	{ pstmt.setString(1, book.getTitle()); pstmt.setString(2, book.getAuthor()); pstmt.setString(3, book.getCategory()); pstmt.setString(4, String.valueOf(book.getStatus())); pstmt.setString(5, String.valueOf(book.getAvailability())); pstmt.executeUpdate();try (ResultSet rs = pstmt.getGeneratedKeys()) {
    if (rs.next()) {
        logBookAction(rs.getInt(1), "ADD");
    }
}
} 
	catch (SQLException e) {
throw new LibraryException("Error adding book: " + e.getMessage());
}
}

public void updateBook(Book book) throws LibraryException {
String sql = "UPDATE books SET Title = ?, Author = ?, Category = ?, Status = ? WHERE BookId = ?";
try (Connection conn = DatabaseUtil.getConnection();
 PreparedStatement pstmt = conn.prepareStatement(sql)) {
pstmt.setString(1, book.getTitle());
pstmt.setString(2, book.getAuthor());
pstmt.setString(3, book.getCategory());
pstmt.setString(4, String.valueOf(book.getStatus()));
pstmt.setInt(5, book.getBookId());
int rows = pstmt.executeUpdate();
if (rows > 0) {
    logBookAction(book.getBookId(), "UPDATE");
} else {
    throw new LibraryException("Book not found");
}
} catch (SQLException e) {
throw new LibraryException("Error updating book: " + e.getMessage());
}
}

public void updateBookStatus(int bookId, char status) throws LibraryException {
String sql = "UPDATE books SET Status = ? WHERE BookId = ?";
try (Connection conn = DatabaseUtil.getConnection();
 PreparedStatement pstmt = conn.prepareStatement(sql)) {
pstmt.setString(1, String.valueOf(status));
pstmt.setInt(2, bookId);
int rows = pstmt.executeUpdate();
if (rows > 0) {
    logBookAction(bookId, "UPDATE_STATUS");
} else {
    throw new LibraryException("Book not found");
}
} catch (SQLException e) {
throw new LibraryException("Error updating book status: " + e.getMessage());
}
}

public List<Book> getAllBooks() throws LibraryException {
List<Book> books = new ArrayList<>();
String sql = "SELECT * FROM books";
try (Connection conn = DatabaseUtil.getConnection();
 Statement stmt = conn.createStatement();
 ResultSet rs = stmt.executeQuery(sql)) {
while (rs.next()) {
    Book book = new Book();
    book.setBookId(rs.getInt("BookId"));
    book.setTitle(rs.getString("Title"));
    book.setAuthor(rs.getString("Author"));
    book.setCategory(rs.getString("Category"));
    book.setStatus(rs.getString("Status").charAt(0));
    book.setAvailability(rs.getString("Availability").charAt(0));
    books.add(book);
}
} catch (SQLException e) {
throw new LibraryException("Error retrieving books: " + e.getMessage());
}
return books;
}

public Book getBookById(int bookId) throws LibraryException {
String sql = "SELECT * FROM books WHERE BookId = ?";
try (Connection conn = DatabaseUtil.getConnection();
 PreparedStatement pstmt = conn.prepareStatement(sql)) {
pstmt.setInt(1, bookId);
try (ResultSet rs = pstmt.executeQuery()) {
    if (rs.next()) {
        Book book = new Book();
        book.setBookId(rs.getInt("BookId"));
        book.setTitle(rs.getString("Title"));
        book.setAuthor(rs.getString("Author"));
        book.setCategory(rs.getString("Category"));
        book.setStatus(rs.getString("Status").charAt(0));
        book.setAvailability(rs.getString("Availability").charAt(0));
        return book;
    }
}
throw new LibraryException("Book not found with ID: " + bookId);
} catch (SQLException e) {
throw new LibraryException("Error retrieving book: " + e.getMessage());
}
}

private void logBookAction(int bookId, String action) throws SQLException {
String logSql = "INSERT INTO books_log (BookId, Action, ActionDate) VALUES (?, ?, NOW())";
try (Connection conn = DatabaseUtil.getConnection();
 PreparedStatement pstmt = conn.prepareStatement(logSql)) {
pstmt.setInt(1, bookId);
pstmt.setString(2, action);
pstmt.executeUpdate();
}
}
}