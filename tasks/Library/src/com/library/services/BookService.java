package com.library.services;

import com.library.dao.Book;
import com.library.dao.BookDAO;
import com.library.util.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private BookDAO bookDAO = new BookDAO();

    public boolean addBook(Book book) {

        return bookDAO.addBook(book);
    }

    public List<Book> getAllBooks() {

        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";

        try (Connection conn = DB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("BookId"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Category"),
                        rs.getString("Status").charAt(0),
                        rs.getString("Availability").charAt(0)
                );
                books.add(book);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching books: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return books;
    }


}