package com.vardhan.main.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.vardhan.main.dao.BookDao;
import com.vardhan.main.model.Book;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<Book> BOOK_ROW_MAPPER = new RowMapper<Book>() {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .id(rs.getInt("id"))
                    .bookId(rs.getString("book_id"))
                    .title(rs.getString("title"))
                    .author(rs.getString("author"))
                    .category(rs.getString("category"))
                    .status(rs.getString("status"))
                    .availability(rs.getString("availability"))
                    .build();
        }
    };

    @Override
    public Book save(Book book) throws DataAccessException {
        String sql = "INSERT INTO books (title, author, category, status, availability) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setString(4, book.getStatus());
            ps.setString(5, book.getAvailability());
            return ps;
        }, keyHolder);

        //jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getCategory(), book.getStatus(), book.getAvailability());
        
        int generatedId = keyHolder.getKey().intValue();
        book.setId(generatedId);
        String bookIdSql = "SELECT book_id FROM books WHERE id = ?";
        String generatedBookId = jdbcTemplate.queryForObject(bookIdSql, String.class, generatedId);
        book.setBookId(generatedBookId);
        return book;
    }

    @Override
    public Optional<Book> findById(Integer id) throws DataAccessException {
        String sql = "SELECT id, book_id, title, author, category, status, availability FROM books WHERE id = ?";
        try {
            Book book = jdbcTemplate.queryForObject(sql, BOOK_ROW_MAPPER, id);
            return Optional.ofNullable(book);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Book> findByBookId(String bookId) throws DataAccessException {
        String sql = "SELECT id, book_id, title, author, category, status, availability FROM books WHERE book_id = ?";
        try {
            Book book = jdbcTemplate.queryForObject(sql, BOOK_ROW_MAPPER, bookId);
            return Optional.ofNullable(book);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() throws DataAccessException {
        String sql = "SELECT id, book_id, title, author, category, status, availability FROM books ORDER BY title";
        return jdbcTemplate.query(sql, BOOK_ROW_MAPPER);
    }

    @Override
    public List<Book> findByCategory(String category) throws DataAccessException {
        String sql = "SELECT id, book_id, title, author, category, status, availability FROM books WHERE category = ? ORDER BY title";
        return jdbcTemplate.query(sql, BOOK_ROW_MAPPER, category);
    }

    @Override
    public List<Book> findByAuthor(String author) throws DataAccessException {
        String sql = "SELECT id, book_id, title, author, category, status, availability FROM books WHERE author LIKE ? ORDER BY title";
        return jdbcTemplate.query(sql, BOOK_ROW_MAPPER, "%" + author + "%");
    }

    @Override
    public List<Book> findByTitle(String title) throws DataAccessException {
        String sql = "SELECT id, book_id, title, author, category, status, availability FROM books WHERE title LIKE ? ORDER BY title";
        return jdbcTemplate.query(sql, BOOK_ROW_MAPPER, "%" + title + "%");
    }

    @Override
    public List<Book> findAvailableBooks() throws DataAccessException {
        String sql = "SELECT id, book_id, title, author, category, status, availability FROM books WHERE availability = 'A' AND status = 'A' ORDER BY title";
        return jdbcTemplate.query(sql, BOOK_ROW_MAPPER);
    }

    @Override
    public List<Book> findAvailableBooksByCategory(String category) throws DataAccessException {
        String sql = "SELECT id, book_id, title, author, category, status, availability FROM books WHERE category = ? AND availability = 'A' AND status = 'A' ORDER BY title";
        return jdbcTemplate.query(sql, BOOK_ROW_MAPPER, category);
    }

    @Override
    public Book update(Book book) throws DataAccessException {
        String sql = "UPDATE books SET title = ?, author = ?, category = ?, status = ?, availability = ? WHERE book_id = ?";
        int rowsAffected = jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getStatus(),
                book.getAvailability(),
                book.getBookId());

        if (rowsAffected > 0) {
            return book;
        }
        throw new DataAccessException("Failed to update book with ID: " + book.getBookId()) {};
    }

    @Override
    public boolean updateAvailability(String bookId, String availability) throws DataAccessException {
        String sql = "UPDATE books SET availability = ? WHERE book_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, availability, bookId);
        return rowsAffected > 0;
    }

    @Override
    public boolean updateStatus(String bookId, String status) throws DataAccessException {
        String sql = "UPDATE books SET status = ? WHERE book_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, status, bookId);
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteByBookId(String bookId) throws DataAccessException {
        String sql = "DELETE FROM books WHERE book_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, bookId);
        return rowsAffected > 0;
    }

    @Override
    public boolean existsByBookId(String bookId) throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM books WHERE book_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, bookId);
        return count != null && count > 0;
    }

    @Override
    public long count() throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM books";
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        return count != null ? count : 0;
    }

    @Override
    public long countByCategory(String category) throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM books WHERE category = ?";
        Long count = jdbcTemplate.queryForObject(sql, Long.class, category);
        return count != null ? count : 0;
    }

    @Override
    public boolean existsByTitle(String title) throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM books WHERE title = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, title);
        return count != null && count > 0;
    }

    @Override
    public Optional<Book> findByExactTitle(String title) throws DataAccessException {
        String sql = "SELECT id, book_id, title, author, category, status, availability FROM books WHERE title = ?";
        try {
            Book book = jdbcTemplate.queryForObject(sql, BOOK_ROW_MAPPER, title);
            return Optional.ofNullable(book);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
