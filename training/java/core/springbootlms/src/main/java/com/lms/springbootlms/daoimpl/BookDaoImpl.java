package com.lms.springbootlms.daoimpl;

import com.lms.springbootlms.dao.BookDao;
import com.lms.springbootlms.exception.DaoException;
import com.lms.springbootlms.model.Book;
import com.lms.springbootlms.model.BookCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean isTitleExists(String title) throws DaoException {
        try {
            String sql = "SELECT COUNT(book_id) FROM books WHERE title = ?";
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, title);
            return count != null && count > 0;
        } catch (DataAccessException e) {
            throw new DaoException("Error checking if title exists: " + title, e);
        }
    }

    @Override
    public Book getBookByTitle(String title) throws DaoException {
        try {
            String sql = "SELECT book_id, title, author, category, status, availability FROM books WHERE title = ?";
            List<Book> books = jdbcTemplate.query(sql, (rs, rowNum) -> new Book(
                    rs.getString("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    BookCategory.valueOf(rs.getString("category")),
                    rs.getString("status").charAt(0),
                    rs.getString("availability").charAt(0)
            ), title);
            return books.isEmpty() ? null : books.get(0);
        } catch (DataAccessException e) {
            throw new DaoException("Error fetching book by title: " + title, e);
        }
    }

    @Override
    public ArrayList<Book> getBooks() throws DaoException {
        try {
            String sql = "SELECT book_id, title, author, category, status, availability FROM books";
            return new ArrayList<>(jdbcTemplate.query(sql, (rs, rowNum) -> new Book(
                    rs.getString("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    BookCategory.valueOf(rs.getString("category")),
                    rs.getString("status").charAt(0),
                    rs.getString("availability").charAt(0)
            )));
        } catch (DataAccessException e) {
            throw new DaoException("Error fetching all books", e);
        }
    }

    @Override
    public Book getBookById(String bookId) throws DaoException {
        try {
            String sql = "SELECT book_id, title, author, category, status, availability FROM books WHERE book_id = ?";
            List<Book> books = jdbcTemplate.query(sql, (rs, rowNum) -> new Book(
                    rs.getString("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    BookCategory.valueOf(rs.getString("category")),
                    rs.getString("status").charAt(0),
                    rs.getString("availability").charAt(0)
            ), bookId);
            return books.isEmpty() ? null : books.get(0);
        } catch (DataAccessException e) {
            throw new DaoException("Error fetching book by ID: " + bookId, e);
        }
    }

    @Override
    public List<Book> getAvailableBooksByCategory(BookCategory category) throws DaoException {
        try {
            String sql;
            Object[] params;

            if (category == null) {
                sql = "SELECT book_id, title, author, category, status, availability " +
                      "FROM books WHERE availability = 'A' AND status = 'A'";
                params = new Object[]{};
            } else {
                sql = "SELECT book_id, title, author, category, status, availability " +
                      "FROM books WHERE category = ? AND availability = 'A' AND status = 'A'";
                params = new Object[]{category.name()};
            }

            return jdbcTemplate.query(sql, params, (rs, rowNum) -> {
                Book book = new Book();
                book.setBookId(rs.getString("book_id"));
                book.setBookTitle(rs.getString("title"));
                book.setBookAuthor(rs.getString("author"));
                book.setBookCategory(BookCategory.valueOf(rs.getString("category")));
                book.setAvailability(rs.getString("availability").charAt(0));
                book.setStatus(rs.getString("status").charAt(0));
                return book;
            });
        } catch (DataAccessException e) {
            throw new DaoException("Error fetching available books by category: " + category, e);
        }
    }

    @Override
    public void updateBookAvailability(String bookId, char availability) throws DaoException {
        try {
            String sql = "UPDATE books SET availability = ? WHERE book_id = ?";
            jdbcTemplate.update(sql, String.valueOf(availability), bookId);
        } catch (DataAccessException e) {
            throw new DaoException("Error updating book availability for ID: " + bookId, e);
        }
    }

    @Override
    public Book addBook(Book newBook) throws DaoException {
        try {
            String sql = "INSERT INTO books (title, author, category, status, availability) VALUES (?, ?, ?, ?, ?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, newBook.getBookTitle());
                ps.setString(2, newBook.getBookAuthor());
                ps.setString(3, newBook.getBookCategory().name());
                ps.setString(4, String.valueOf(newBook.getStatus()));
                ps.setString(5, String.valueOf(newBook.getAvailability()));
                return ps;
            }, keyHolder);

            if (keyHolder.getKey() != null) {
                Integer id = keyHolder.getKey().intValue();
                String idQuery = "SELECT book_id FROM books WHERE id = ?";
                String bookId = jdbcTemplate.queryForObject(idQuery, String.class, id);
                return new Book(bookId, newBook.getBookTitle(), newBook.getBookAuthor(),
                        newBook.getBookCategory(), newBook.getStatus(), newBook.getAvailability());
            }
            return null;
        } catch (DataAccessException e) {
            throw new DaoException("Error adding new book: " + newBook.getBookTitle(), e);
        }
    }

    @Override
    public Boolean updateBook(Book updateBook) throws DaoException {
        try {
            String sql = "UPDATE books SET title = ?, author = ?, category = ?, status = ?, availability = ? WHERE book_id = ?";
            int rows = jdbcTemplate.update(sql,
                    updateBook.getBookTitle(),
                    updateBook.getBookAuthor(),
                    updateBook.getBookCategory().name(),
                    String.valueOf(updateBook.getStatus()),
                    String.valueOf(updateBook.getAvailability()),
                    updateBook.getBookId());
            return rows > 0;
        } catch (DataAccessException e) {
            throw new DaoException("Error updating book with ID: " + updateBook.getBookId(), e);
        }
    }

    @Override
    public void deleteBookById(String bookId) throws DaoException {
        try {
            String sql = "DELETE FROM books WHERE book_id = ?";
            jdbcTemplate.update(sql, bookId);
        } catch (DataAccessException e) {
            throw new DaoException("Error deleting book with ID: " + bookId, e);
        }
    }

    @Override
    public List<Book> getAvailableBooks() throws DaoException {
        try {
            String sql = "SELECT book_id, title, author, category, status, availability " +
                         "FROM books WHERE availability = 'A' AND status = 'A'";

            return jdbcTemplate.query(sql, (rs, rowNum) -> {
                Book book = new Book();
                book.setBookId(rs.getString("book_id"));
                book.setBookTitle(rs.getString("title"));
                book.setBookAuthor(rs.getString("author"));
                book.setBookCategory(BookCategory.valueOf(rs.getString("category")));
                book.setStatus(rs.getString("status").charAt(0));
                book.setAvailability(rs.getString("availability").charAt(0));
                return book;
            });

        } catch (DataAccessException e) {
            throw new DaoException("Error fetching available books", e);
        }
    }

}
