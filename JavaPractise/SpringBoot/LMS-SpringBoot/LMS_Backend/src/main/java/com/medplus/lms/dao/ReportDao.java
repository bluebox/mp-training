package com.medplus.lms.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.medplus.lms.domain.IssueRecordDto;
import com.medplus.lms.domain.IssueStatus;
import com.medplus.lms.domain.MemberIssuedBookDto;
import com.medplus.lms.exceptions.ManagementException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class ReportDao implements ReportDaoInterface{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ReportDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<IssueRecordDto> getOverdueBooks() {
        LocalDateTime overdueDate = LocalDateTime.now().minusDays(15);

        String sql = "SELECT ir.bookId, b.title AS book_title, m.name AS member_name, ir.issueDate, ir.returnDate " +
                     "FROM issue_record ir " +
                     "JOIN books b ON ir.bookId = b.book_id " +
                     "JOIN members m ON ir.memberId = m.member_id " +
                     "WHERE ir.status = :status AND ir.issueDate < :overdueDate";

        Map<String, Object> params = new HashMap<>();
        params.put("status", IssueStatus.ISSUED.getCode());
        params.put("overdueDate", Timestamp.valueOf(overdueDate));

        try {
            return jdbcTemplate.query(sql, params, new OverdueIssueRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private static class OverdueIssueRowMapper implements RowMapper<IssueRecordDto> {
        @Override
        public IssueRecordDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            IssueRecordDto ir = new IssueRecordDto();
            ir.setBookId(rs.getInt("bookId"));
            ir.setBookTitle(rs.getString("book_title"));
            ir.setMemberName(rs.getString("member_name"));

            Timestamp issueTimestamp = rs.getTimestamp("issueDate");
            Timestamp returnTimestamp = rs.getTimestamp("returnDate");

            if (issueTimestamp != null) {
                ir.setIssueDate(issueTimestamp.toLocalDateTime());
            }

            if (returnTimestamp != null) {
                ir.setReturnDate(returnTimestamp.toLocalDateTime());
            } else if (issueTimestamp != null) {
                ir.setReturnDate(issueTimestamp.toLocalDateTime().plusDays(15));
            }

            return ir;
        }
    }

    public Map<String, Long> getBooksCountPerCategory() {
        String sql = "SELECT category, COUNT(*) AS count FROM books GROUP BY category";

        try {
            return jdbcTemplate.query(sql, Collections.emptyMap(), rs -> {
                Map<String, Long> countMap = new LinkedHashMap<>();
                while (rs.next()) {
                    countMap.put(rs.getString("category"), rs.getLong("count"));
                }
                return countMap;
            });
        } catch (Exception e) {
            throw new ManagementException("Error fetching books per category");
        }
    }

    public List<MemberIssuedBookDto> getMembersWithActiveIssuedBooks() {
        String sql = "SELECT m.member_id, m.name, m.email, b.book_id, b.title AS book_title, ir.issueDate " +
                     "FROM members m " +
                     "JOIN issue_record ir ON m.member_id = ir.memberId " +
                     "JOIN books b ON ir.bookId = b.book_id " +
                     "WHERE ir.status = :status";

        Map<String, Object> params = new HashMap<>();
        params.put("status", IssueStatus.ISSUED.getCode());

        return jdbcTemplate.query(sql, params, new MemberIssuedBookRowMapper());
    }

    private static class MemberIssuedBookRowMapper implements RowMapper<MemberIssuedBookDto> {
        @Override
        public MemberIssuedBookDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            MemberIssuedBookDto dto = new MemberIssuedBookDto();
            dto.setMemberId(rs.getInt("member_id"));
            dto.setName(rs.getString("name"));
            dto.setEmail(rs.getString("email"));
            dto.setBookId(rs.getInt("book_id"));
            dto.setBookTitle(rs.getString("book_title"));
            Timestamp issueTimestamp = rs.getTimestamp("issueDate");
            if (issueTimestamp != null) {
                dto.setIssueDate(issueTimestamp.toLocalDateTime());
            }
            return dto;
        }
    }
}
