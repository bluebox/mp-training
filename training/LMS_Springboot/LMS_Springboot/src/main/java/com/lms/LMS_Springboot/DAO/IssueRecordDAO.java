package com.lms.LMS_Springboot.DAO;

import com.lms.LMS_Springboot.Model.IssueRecord;
import com.lms.LMS_Springboot.Model.checking_enum.Status_issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class IssueRecordDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Get all issue records
    public List<IssueRecord> getAllRecords() {
        return jdbcTemplate.query("SELECT issueId,bookId,memberId,status,issueDate,returnDate FROM issue_records",
                (rs, rowNum) -> new IssueRecord(
                        rs.getInt("issueId"),
                        rs.getInt("bookId"),
                        rs.getInt("memberId"),
                        Status_issue.getstatus(rs.getString("status")),
                        rs.getDate("issueDate").toLocalDate(),
                        rs.getDate("returnDate") != null ? rs.getDate("returnDate").toLocalDate() : null
                ));
    }

    // Get record by ID
    public IssueRecord getRecordById(int id) {
        return jdbcTemplate.queryForObject("SELECT issueId,bookId,memberId,status,issueDate,returnDate FROM issue_records WHERE issueId=?",
                new Object[]{id},
                (rs, rowNum) -> new IssueRecord(
                        rs.getInt("issueId"),
                        rs.getInt("bookId"),
                        rs.getInt("memberId"),
                        Status_issue.getstatus(rs.getString("status")),
                        rs.getDate("issueDate").toLocalDate(),
                        rs.getDate("returnDate") != null ? rs.getDate("returnDate").toLocalDate() : null
                ));
    }

    // Issue a new book (with availability check)
    public int issueBook(IssueRecord record) {
        // Step 1: Check if book is available
        String availability = jdbcTemplate.queryForObject(
                "SELECT availability FROM books WHERE bookId = ?",
                new Object[]{record.getBookId()},
                String.class
        );

        if ("I".equals(availability)) {
            // Book already issued
            return -1;  
        }

        // Step 2: Insert issue record
        int rows = jdbcTemplate.update(
                "INSERT INTO issue_records (bookId, memberId, status, issueDate) VALUES (?,?,?,?)",
                record.getBookId(),
                record.getMemberId(),
                record.getStatus().getType(),
                Date.valueOf(record.getIssueDate())
        );

        // Step 3: Update book availability → Issued
        if (rows > 0) {
            jdbcTemplate.update(
                    "UPDATE books SET availability=? WHERE bookId=?",
                    "I",
                    record.getBookId()
            );
        }

        return rows;
    }

    // Return a book (update status + returnDate + book availability)
    public int returnBook(int id, LocalDate returnDate) {
        // Step 1: Update issue record
        int rows = jdbcTemplate.update(
                "UPDATE issue_records SET status=?, returnDate=? WHERE issueId=?",
                Status_issue.RETURNED.getType(),
                Date.valueOf(returnDate),
                id
        );

        if (rows > 0) {
            // Step 2: Find the bookId of this issue record
            Integer bookId = jdbcTemplate.queryForObject(
                    "SELECT bookId FROM issue_records WHERE issueId=?",
                    new Object[]{id},
                    Integer.class
            );

            // Step 3: Update book availability → Available
            jdbcTemplate.update(
                    "UPDATE books SET availability=? WHERE bookId=?",
                    "A",
                    bookId
            );
        }

        return rows;
    }
}
