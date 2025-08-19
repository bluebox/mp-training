package com.lms.daoImpl;

import com.lms.dao.BookDao;
import com.lms.exceptions.DAOException;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Book> rowMapper = (rs, rowNum) -> {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setBookId(rs.getString("book_id"));
        book.setBookTitle(rs.getString("title"));
        book.setBookAuthor(rs.getString("author"));
        book.setBookCategory(BookCategory.valueOf(rs.getString("category")));
        book.setStatus(rs.getString("status"));
        book.setAvailability(rs.getString("availability"));
        return book;
    };

    @Override
    public List<Book> findAll() throws DAOException {
        String sql = "SELECT * FROM books";
        try {
            return jdbcTemplate.query(sql, rowMapper);
        } catch (Exception e) {
            throw new DAOException("Error fetching all books: " + e.getMessage(), e);
        }
    }

    @Override
    public Book findByBookId(String bookId) throws DAOException {
        String sql = "SELECT * FROM books WHERE book_id = ?";
        try {
            List<Book> books = jdbcTemplate.query(sql, rowMapper, bookId);
            return books.isEmpty() ? null : books.get(0); 
        } catch (Exception e) {
            throw new DAOException("Error fetching book by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean save(Book book) throws DAOException {
        String sql = "INSERT INTO books (book_id, title, author, category, status, availability) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            int rows = jdbcTemplate.update(sql,
                    book.getBookId(),
                    book.getBookTitle(),
                    book.getBookAuthor(),
                    book.getBookCategory().name(),
                    book.getStatus(),
                    book.getAvailability());
            return rows > 0;
        } catch (Exception e) {
            throw new DAOException("Error adding book: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean update(Book book) throws DAOException {
        String sql = "UPDATE books SET title=?, author=?, category=?, status=?, availability=? WHERE book_id=?";
        try {
            int rows = jdbcTemplate.update(sql,
                    book.getBookTitle(),
                    book.getBookAuthor(),
                    book.getBookCategory().name(),
                    book.getStatus(),
                    book.getAvailability(),
                    book.getBookId());
            return rows > 0;
        } catch (Exception e) {
            throw new DAOException("Error updating book: " + e.getMessage(), e);
        }
    }
}
