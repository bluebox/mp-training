package com.lms.Repository;

import com.lms.Exceptions.BookDaoException;
import com.lms.Models.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.List;

@Repository
public class BookRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String DEFAULT_STATUS = "A";

    
    public int addBook(Book book) {
        String sql = "INSERT INTO books(title, author, category, status, availability) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
        //	System.out.println("gopi");
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, book.getTitle());
                ps.setString(2, book.getAuthor());
                ps.setString(3, book.getCategory());
                ps.setString(4, DEFAULT_STATUS);
                ps.setString(5, DEFAULT_STATUS);
               // System.out.println("gopi");
                return ps;
            }, keyHolder);
//System.out.println("gopi");
            return keyHolder.getKey().intValue();
        } catch (Exception e) {
            throw new BookDaoException("Failed to insert book", e);
        }
    }

    
    public void updateBook(Book book) {
        String updateSql = "UPDATE books SET title=?, author=?, category=?, status=? WHERE bookId=?";
        String logSql = "INSERT INTO books_log(bookId, title, author, category, status, availability, operation_type) VALUES (?, ?, ?, ?, ?, ?, ?)";
 //System.out.println("gopi");
        try {
            jdbcTemplate.update(logSql, book.getBookId(), book.getTitle(), book.getAuthor(),
                    book.getCategory(), book.getStatus(), book.getAvailability(), "Update");

            jdbcTemplate.update(updateSql, book.getTitle(), book.getAuthor(), book.getCategory(),
                    book.getStatus(), book.getBookId());
        } catch (Exception e) {
            throw new BookDaoException("Failed to update book", e);
        }
    }

   
    public void updateAvailability(Book book) {
        String updateSql = "UPDATE books SET availability=? WHERE bookId=?";
        String logSql = "INSERT INTO books_log(bookId, title, author, category, status, availability, operation_type) VALUES (?, ?, ?, ?, ?, ?, ?)";
//System.out.println("gopi");
        try {
            jdbcTemplate.update(updateSql, book.getAvailability(), book.getBookId());

            jdbcTemplate.update(logSql, book.getBookId(), book.getTitle(), book.getAuthor(),
                    book.getCategory(), book.getStatus(), book.getAvailability(), "Updated_availability");
        } catch (Exception e) {
            throw new BookDaoException("Failed to update availability", e);
        }
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM books";
        try {
            return jdbcTemplate.query(sql, this::mapRowToBook);
        } catch (Exception e) {
            throw new BookDaoException("Failed to fetch books", e);
        }
    }

    public Book getBookById(int bookId) {
        String sql = "SELECT * FROM books WHERE bookId = ?";
        try {
            List<Book> books = jdbcTemplate.query(sql, new Object[]{bookId}, this::mapRowToBook);
            return books.isEmpty() ? null : books.get(0);
        } catch (Exception e) {
            throw new BookDaoException("Failed to fetch book by ID", e);
        }
    }



    public void updateStatus(Book book) {
        String updateSql = "UPDATE books SET status=? WHERE bookId=?";
        String logSql = "INSERT INTO books_log(bookId, title, author, category, status, availability, operation_type) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            jdbcTemplate.update(updateSql, book.getStatus(), book.getBookId());

            jdbcTemplate.update(logSql, book.getBookId(), book.getTitle(), book.getAuthor(),
                    book.getCategory(), book.getStatus(), book.getAvailability(), "Updated_Status");
        } catch (Exception e) {
            throw new BookDaoException("Failed to update status", e);
        }
    }

    
    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getInt("bookId"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setCategory(rs.getString("category"));
        book.setStatus(rs.getString("status"));
        book.setAvailability(rs.getString("availability"));
        return book;
    }
}

