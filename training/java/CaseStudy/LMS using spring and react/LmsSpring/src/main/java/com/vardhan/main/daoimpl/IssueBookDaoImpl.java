package com.vardhan.main.daoimpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import org.springframework.transaction.annotation.Transactional;

import com.vardhan.main.dao.IssueBookDao;
import com.vardhan.main.model.IssueBook;

@Repository
public class IssueBookDaoImpl implements IssueBookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<IssueBook> ISSUE_BOOK_ROW_MAPPER = new RowMapper<IssueBook>() {
        @Override
        public IssueBook mapRow(ResultSet rs, int rowNum) throws SQLException {
            Date actualReturnDate = rs.getDate("actual_return_date");
            return IssueBook.builder()
                    .issueId(rs.getInt("issue_id"))
                    .bookId(rs.getString("book_id"))
                    .memberId(rs.getInt("member_id"))
                    .issueDate(rs.getDate("issue_date").toLocalDate())
                    .returnDate(rs.getDate("return_date").toLocalDate())
                    .actualReturnDate(actualReturnDate != null ? actualReturnDate.toLocalDate() : null)
                    .build();
        }
    };

    @Override
    @Transactional
    public IssueBook save(IssueBook issueBook) throws DataAccessException {
        String sql = "INSERT INTO issue_books (member_id, book_id, issue_date, return_date) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, issueBook.getMemberId());
            ps.setString(2, issueBook.getBookId());
            ps.setDate(3, Date.valueOf(issueBook.getIssueDate()));
            ps.setDate(4, Date.valueOf(issueBook.getReturnDate()));
            return ps;
        }, keyHolder);

        updateBookAvailability(issueBook.getBookId(), "I");
        issueBook.setIssueId(keyHolder.getKey().intValue());
        return issueBook;
    }

    @Override
    public Optional<IssueBook> findById(Integer issueId) throws DataAccessException {
        String sql = "SELECT issue_id, member_id, book_id, issue_date, return_date, actual_return_date FROM issue_books WHERE issue_id = ?";
        try {
            IssueBook issueBook = jdbcTemplate.queryForObject(sql, ISSUE_BOOK_ROW_MAPPER, issueId);
            return Optional.ofNullable(issueBook);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<IssueBook> findAll() throws DataAccessException {
        String sql = "SELECT issue_id, member_id, book_id, issue_date, return_date, actual_return_date FROM issue_books ORDER BY issue_date DESC";
        return jdbcTemplate.query(sql, ISSUE_BOOK_ROW_MAPPER);
    }

    @Override
    public List<IssueBook> findByMemberId(Integer memberId) throws DataAccessException {
        String sql = "SELECT issue_id, member_id, book_id, issue_date, return_date, actual_return_date FROM issue_books WHERE member_id = ? ORDER BY issue_date DESC";
        return jdbcTemplate.query(sql, ISSUE_BOOK_ROW_MAPPER, memberId);
    }

    @Override
    public List<IssueBook> findByBookId(String bookId) throws DataAccessException {
        String sql = "SELECT issue_id, member_id, book_id, issue_date, return_date, actual_return_date FROM issue_books WHERE book_id = ? ORDER BY issue_date DESC";
        return jdbcTemplate.query(sql, ISSUE_BOOK_ROW_MAPPER, bookId);
    }

    @Override
    public List<IssueBook> findActiveIssuesByMember(Integer memberId) throws DataAccessException {
        String sql = "SELECT issue_id, member_id, book_id, issue_date, return_date, actual_return_date FROM issue_books WHERE member_id = ? AND actual_return_date IS NULL ORDER BY issue_date DESC";
        return jdbcTemplate.query(sql, ISSUE_BOOK_ROW_MAPPER, memberId);
    }

    @Override
    public List<IssueBook> findOverdueBooks() throws DataAccessException {
        String sql = "SELECT issue_id, member_id, book_id, issue_date, return_date, actual_return_date FROM issue_books WHERE actual_return_date IS NULL AND return_date < CURRENT_DATE ORDER BY return_date";
        return jdbcTemplate.query(sql, ISSUE_BOOK_ROW_MAPPER);
    }

    @Override
    public List<IssueBook> findReturnedBooks() throws DataAccessException {
        String sql = "SELECT issue_id, member_id, book_id, issue_date, return_date, actual_return_date FROM issue_books WHERE actual_return_date IS NOT NULL ORDER BY actual_return_date DESC";
        return jdbcTemplate.query(sql, ISSUE_BOOK_ROW_MAPPER);
    }

    @Override
    public List<IssueBook> findActiveIssues() throws DataAccessException {
        String sql = "SELECT issue_id, member_id, book_id, issue_date, return_date, actual_return_date FROM issue_books WHERE actual_return_date IS NULL ORDER BY issue_date DESC";
        return jdbcTemplate.query(sql, ISSUE_BOOK_ROW_MAPPER);
    }

    @Override
    @Transactional
    public boolean returnBook(Integer issueId, LocalDate actualReturnDate) throws DataAccessException {
        String getBookIdSql = "SELECT book_id FROM issue_books WHERE issue_id = ?";
        String bookId = jdbcTemplate.queryForObject(getBookIdSql, String.class, issueId);

        String updateSql = "UPDATE issue_books SET actual_return_date = ? WHERE issue_id = ?";
        int rowsAffected = jdbcTemplate.update(updateSql, Date.valueOf(actualReturnDate), issueId);

        if (rowsAffected > 0) {
            updateBookAvailability(bookId, "A");
            return true;
        }
        return false;
    }

    @Override
    public IssueBook update(IssueBook issueBook) throws DataAccessException {
        String sql = "UPDATE issue_books SET member_id = ?, book_id = ?, issue_date = ?, return_date = ?, actual_return_date = ? WHERE issue_id = ?";
        int rowsAffected = jdbcTemplate.update(sql,
                issueBook.getMemberId(),
                issueBook.getBookId(),
                Date.valueOf(issueBook.getIssueDate()),
                Date.valueOf(issueBook.getReturnDate()),
                issueBook.getActualReturnDate() != null ? Date.valueOf(issueBook.getActualReturnDate()) : null,
                issueBook.getIssueId());

        if (rowsAffected > 0) {
            return issueBook;
        }
        throw new DataAccessException("Failed to update issue book with ID: " + issueBook.getIssueId()) {};
    }

    @Override
    public boolean deleteById(Integer issueId) throws DataAccessException {
        String sql = "DELETE FROM issue_books WHERE issue_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, issueId);
        return rowsAffected > 0;
    }

    @Override
    public boolean isBookCurrentlyIssued(String bookId) throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM issue_books WHERE book_id = ? AND actual_return_date IS NULL";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, bookId);
        return count != null && count > 0;
    }

    @Override
    public boolean hasMemberIssuedBook(Integer memberId, String bookId) throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM issue_books WHERE member_id = ? AND book_id = ? AND actual_return_date IS NULL";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, memberId, bookId);
        return count != null && count > 0;
    }

    @Override
    public long countActiveIssuesByMember(Integer memberId) throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM issue_books WHERE member_id = ? AND actual_return_date IS NULL";
        Long count = jdbcTemplate.queryForObject(sql, Long.class, memberId);
        return count != null ? count : 0;
    }

    @Override
    public long countOverdueBooks() throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM issue_books WHERE actual_return_date IS NULL AND return_date < CURRENT_DATE";
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        return count != null ? count : 0;
    }

    private void updateBookAvailability(String bookId, String availability) throws DataAccessException {
        String sql = "UPDATE books SET availability = ? WHERE book_id = ?";
        jdbcTemplate.update(sql, availability, bookId);
    }
}
