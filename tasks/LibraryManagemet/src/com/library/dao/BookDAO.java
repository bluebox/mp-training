package com.library.dao;

import com.library.domain.Book;
import com.library.utilities.ConnectionMaker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public boolean insertBook(Book book) {
        String sql = "INSERT INTO book (title, author, category, status, availability) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionMaker.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getCategory());
            pstmt.setString(4, book.getStatus());
            pstmt.setString(5, book.getAvailability());

            int rows = pstmt.executeUpdate();
            System.out.println(rows);
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Book> getBooksByAvailability(String availability) {
        List<Book> issuedBooks = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE availability = ?";

        try (Connection conn = ConnectionMaker.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, availability);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getString("status"),
                        rs.getString("availability")
                );
                issuedBooks.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return issuedBooks;
    }


    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try (Connection conn = ConnectionMaker.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM book")) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getString("status"),
                        rs.getString("availability")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    public void updateDetails(Connection conn, Book book) {
        try
        {
//			Updating the information regarding the BookId into the books_log table
            PreparedStatement p = conn.prepareStatement(
                    "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availability) " +
                            "SELECT BookId, Title, Author, Category, Status, Availability FROM books WHERE BookId = ?"
            );
            p.setInt(1,book.getBookId());

            PreparedStatement ps = conn.prepareStatement("UPDATE books SET Title = ?, Author = ?, Category = ?, Status = ? WHERE BookId = ?");
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, String.valueOf(book.getCategory()));
            ps.setString(4, book.getStatus());
            ps.setInt(5, book.getBookId());
            System.out.println(ps.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}