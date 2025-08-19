package com.lms.lms_backend.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lms.lms_backend.constant.BookAvailability;
import com.lms.lms_backend.constant.BookCategory;
import com.lms.lms_backend.constant.BookStatus;
import com.lms.lms_backend.dao.BookDao;
import com.lms.lms_backend.model.Book;

@Repository
public class BookDaoImplementation implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertBookLog(Book book) {
        String sql = "INSERT INTO books_log(book_id, title, author, category, status, availability) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory().getDisplayName(),
                book.getStatus().getDbName(),
                book.getAvailability().getDbName());
    }

    @Override
    public int addBook(Book book) {
        String sql = "INSERT INTO books(title, author, category, status, availability) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getCategory().getDisplayName(),
                BookStatus.ACTIVE.getDbName(),
                BookAvailability.AVAILABLE.getDbName());
    }

    @Override
    public int updateBookDetails(Book oldBook, Book newBook) {
        String sql = "UPDATE books SET title=?, author=?, category=? WHERE book_id=? AND availability='A'";
        return jdbcTemplate.update(sql,
                newBook.getTitle(),
                newBook.getAuthor(),
                newBook.getCategory().getDisplayName(),
                oldBook.getBookId());
    }

    @Override
    public int updateBookAvailability(Book oldBook, String availability) {
        String sql = "UPDATE books SET availability=? WHERE book_id=?";
        return jdbcTemplate.update(sql, availability, oldBook.getBookId());
    }

    @Override
    public int deleteBook(Book oldBook) {
        String sql = "UPDATE books SET status=? WHERE book_id=? AND availability=? AND status='A'";
        return jdbcTemplate.update(sql,
                BookStatus.INACTIVE.getDbName(),
                oldBook.getBookId(),
                BookAvailability.AVAILABLE.getDbName());
    }

    @Override
    public List<Book> selectAllBooks() {
        String sql = "SELECT * FROM books WHERE status='A'";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    @Override
    public Book selectBookById(int id) {
        String sql = "SELECT * FROM books WHERE book_id=? AND status='A'";
        return jdbcTemplate.query(sql, new BookRowMapper(), id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Book> selectAllMemberBooks(int memberId) {
        String sql = "SELECT b.* FROM books b JOIN issue_records i ON b.book_id=i.book_id " +
                     "WHERE i.member_id=? AND i.status='I' AND b.status='A'";
        return jdbcTemplate.query(sql, new BookRowMapper(), memberId);
    }

    @Override
    public List<String> getAllCategories() {
        return Stream.of(BookCategory.values())
                     .map(BookCategory::getDisplayName)
                     .collect(Collectors.toList());
    }

    private static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(
                rs.getInt("book_id"),
                rs.getString("title"),
                rs.getString("author"),
                BookCategory.fromDisplayName(rs.getString("category")),
                BookStatus.fromDbName(rs.getString("status")),
                BookAvailability.fromDbName(rs.getString("availability"))
            );
        }
    }
}
