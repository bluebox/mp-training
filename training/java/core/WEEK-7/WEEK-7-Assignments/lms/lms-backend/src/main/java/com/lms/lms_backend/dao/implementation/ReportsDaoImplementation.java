package com.lms.lms_backend.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lms.lms_backend.dao.ReportsDao;
import com.lms.lms_backend.model.CategoryCount;
import com.lms.lms_backend.model.ReportDetails;

@Repository
public class ReportsDaoImplementation implements ReportsDao {
	private final JdbcTemplate jdbcTemplate;

	public ReportsDaoImplementation(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<CategoryCount> getBookCountByCategory() {

		return jdbcTemplate.query("SELECT category, COUNT(*) AS book_count FROM books WHERE status = 'A'   GROUP BY category",
				(rs, rowNum) -> new CategoryCount(rs.getString("category"), rs.getInt("book_count")));

	}

	@Override
	public List<ReportDetails> getActiveIssuedBooks() {

		return jdbcTemplate.query("SELECT ir.issue_id, m.member_id , m.name AS member_name , b.book_id ,b.title AS book_title , ir.issue_date FROM members m JOIN issue_records ir ON m.member_id = ir.member_id JOIN books b ON ir.book_id = b.book_id WHERE ir.return_date IS NULL AND b.status='A' ORDER BY m.member_id", new ReportBookDetailsRowMapper());

	}

	@Override
	public List<ReportDetails> getOverdueBooks() {

		return jdbcTemplate.query("SELECT ir.issue_id, b.book_id , b.title AS book_title , m.member_id , m.name AS member_name , ir.issue_date FROM issue_records ir JOIN members m ON ir.member_id = m.member_id JOIN books b ON ir.book_id = b.book_id WHERE ir.return_date IS NULL AND DATEDIFF(CURDATE(), ir.issue_date) > 14  AND b.status = 'A' ORDER BY ir.issue_date", new ReportBookDetailsRowMapper());

	}
	
	private class ReportBookDetailsRowMapper implements RowMapper<ReportDetails>{
		
		@Override
		public ReportDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ReportDetails(
            	rs.getInt("issue_id"),
                rs.getInt("member_id"),
                rs.getString("member_name"),
                rs.getInt("book_id"),
                rs.getString("book_title"),
                rs.getDate("issue_date").toLocalDate()
            );
        }
	}
}
