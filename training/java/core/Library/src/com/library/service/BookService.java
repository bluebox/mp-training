package com.library.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.library.dao.Book;
import com.library.enums.Availability;
import com.library.enums.Status;
import com.library.exception.UserDefinedException;

public class BookService {
	public static Connection conn;
	public static void BookDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu","practice","Vbhanu@123");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addBook(Book b) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("insert into books values(?,?,?,?,?,?)");
		stmt.setInt(1, b.getBookId());
		stmt.setString(2, b.getTitle());
		stmt.setString(3, b.getAuthor());
		stmt.setString(4, b.getCategory());
		stmt.setString(5, b.getStatus().toString().substring(0, 1));
		stmt.setString(6, b.getAvailability().toString().substring(0, 1));
	}
	public List<Book> getAllBooks() throws UserDefinedException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from books")) {
            while (rs.next()) {
                Book book = new Book(rs.getInt("BookId"),rs.getString("Title"),rs.getString("Author"),rs.getString("Category"),rs.getString("Status").charAt(0)=='A'?Status.Active:Status.Inactive,rs.getString("Availability").charAt(0)=='A'?Availability.Available.Available:Availability.Available.Issued);
                books.add(book);
            }

        } catch (SQLException e) {
            throw new UserDefinedException("Error fetching books: " + e.getMessage());
        }
        return books;
    }
	public void updateBookDetails(Book book) throws UserDefinedException {
        String sql = "UPDATE books SET title=?, author=?, category=?, status=? WHERE bookId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setString(4, String.valueOf(book.getStatus().toString().charAt(0)));
            stmt.setInt(5, book.getBookId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new UserDefinedException("Error updating book details: " + e.getMessage());
        }
    }
	public void updateAvailability(int bookId, char availability) throws UserDefinedException {
        String sql = "UPDATE books SET availability=? WHERE bookId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, String.valueOf(availability));
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UserDefinedException("Error updating book availability: " + e.getMessage());
        }
    }
	public boolean isBookAvailable(int bookId) throws UserDefinedException {
        String sql = "SELECT availability FROM books WHERE bookId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("availability").charAt(0) == 'A';
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new UserDefinedException("Error checking book availability: " + e.getMessage());
        }
    }
}
