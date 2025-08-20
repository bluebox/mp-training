package com.example.lms.repository;

import com.example.lms.model.IssueRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class IssueRepository {

    private final JdbcTemplate jdbc;

    public int save(IssueRecord record) {
        return jdbc.update(
            "INSERT INTO issue_records (book_id, member_id, status, issue_date) VALUES (?,?,?,?)",
            record.getBookId(),
            record.getMemberId(),
            record.getStatus(),
            Date.valueOf(record.getIssueDate())
        );
    }

    public int markReturned(Long issueId, java.time.LocalDate returnDate) {
        return jdbc.update(
            "UPDATE issue_records SET status='R', return_date=? WHERE issue_id=?",
            Date.valueOf(returnDate),
            issueId
        );
    }

    public IssueRecord findById(Long id) {
        List<IssueRecord> result = jdbc.query(
            "SELECT * FROM issue_records WHERE issue_id=?",
            (rs, rowNum) -> new IssueRecord(
                rs.getLong("issue_id"),
                rs.getLong("book_id"),
                rs.getLong("member_id"),
                rs.getString("status"),
                rs.getDate("issue_date").toLocalDate(),
                rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null
            ),
            id
        );
        return result.isEmpty() ? null : result.get(0);
    }

    public List<IssueRecord> findAll() {
        return jdbc.query(
            "SELECT * FROM issue_records",
            (rs, rowNum) -> new IssueRecord(
                rs.getLong("issue_id"),
                rs.getLong("book_id"),
                rs.getLong("member_id"),
                rs.getString("status"),
                rs.getDate("issue_date").toLocalDate(),
                rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null
            )
        );
    }
}
