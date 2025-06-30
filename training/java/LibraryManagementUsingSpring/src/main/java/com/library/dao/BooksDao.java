package com.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.library.domain.Availability;
import com.library.domain.Book;
import com.library.domain.Status;

@Repository
public class BooksDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean createBook(Book book) {
        String sql = "INSERT INTO Books (title, author, category, status, availability) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getStatus().getCode(),        // Store "A" / "I"
                book.getAvailable().getCode()) > 0;
    }

    public boolean updateBook(Book book) {
        // Backup current data
        jdbcTemplate.update("INSERT INTO LogBooks SELECT * FROM Books WHERE bookId = ?", book.getBookId());

        String sql = "UPDATE Books SET title = ?, author = ?, category = ?, status = ?, availability = ? WHERE bookId = ?";
        return jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getStatus().getCode(),        // "A" or "I"
                book.getAvailable().getCode(),     // "A" or "I"
                book.getBookId()) > 0;
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT bookId ,title, author, category, status, availability FROM Books", this::mapRow);
    }

    public Book getBookById(int bookId) {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT bookId, title, author, category, status, availability FROM Books WHERE bookId = ?",
                this::mapRow,
                bookId
            );
        } catch (EmptyResultDataAccessException e) {
            return null; // or Optional.empty() if you prefer
        }
    }


    public void toggleAvailability(int bookId) {
        Book book = getBookById(bookId);
        Availability newAvailability = (book.getAvailable() == Availability.AVAILABLE)
                ? Availability.ISSUED
                : Availability.AVAILABLE;

        jdbcTemplate.update("UPDATE Books SET availability = ? WHERE bookId = ?",
                newAvailability.getCode(), bookId);
    }

    private Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(
                rs.getInt("bookId"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("category"),
                Status.fromCode(rs.getString("status")),                 // Convert "A"/"I" to enum
                Availability.fromCode(rs.getString("availability"))      // Convert "A"/"I" to enum
        );
    }
}
