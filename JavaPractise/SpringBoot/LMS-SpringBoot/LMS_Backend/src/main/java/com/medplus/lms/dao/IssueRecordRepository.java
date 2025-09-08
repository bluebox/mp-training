package com.medplus.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.medplus.lms.domain.IssueRecordDto;
import com.medplus.lms.domain.IssueStatus;
import com.medplus.lms.exceptions.ManagementException;

@Repository
public class IssueRecordRepository implements IssueRecordRepositoryInterface{

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public IssueRecordRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void issueBook(int bookId, int memberId, String issuedBy, LocalDateTime issueDate, IssueStatus status) {
		String sql = "INSERT INTO issue_record (bookId, memberId, status, issueDate, issuedBy) "
				+ "VALUES (:bookId, :memberId, :status, :issueDate, :issuedBy)";
		Map<String, Object> params = new HashMap<>();
		params.put("bookId", bookId);
		params.put("memberId", memberId);
		params.put("status", status.getCode());
		params.put("issueDate", issueDate);
		params.put("issuedBy", issuedBy);
		try {
			jdbcTemplate.update(sql, params);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ManagementException("Failed to issue book");
		}
	}

	public int findActiveIssueId(int bookId, int memberId) throws ManagementException {
		String findSql = "SELECT issueId FROM issue_record " + "WHERE bookId = :bookId AND memberId = :memberId "
				+ "AND status = :status ORDER BY issueDate DESC LIMIT 1";

		Map<String, Object> params = new HashMap<>();
		params.put("bookId", bookId);
		params.put("memberId", memberId);
		params.put("status", IssueStatus.ISSUED.getCode());

		List<Integer> issueIds ;
		try {
			issueIds= jdbcTemplate.query(findSql, params, new IssueIdRowMapper());
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ManagementException("Failed in findActiveIssueId in dao");
		}
		if (issueIds.isEmpty()) {
			throw new ManagementException("No active issue record found");
		}
		return issueIds.get(0);
	}

	public static class IssueIdRowMapper implements RowMapper<Integer> {
		@Override
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getInt("issueId");
		}
	}

	@Transactional
	public void returnBook(int bookId, int memberId, String returnedTo){
		
		String logSql = "INSERT INTO issue_records_log "
				+ "(issueId, bookId, memberId, status, issueDate, returnDate, issuedBy, returnedTo,loggedTime,loggedBy) "
				+ "SELECT issueId, bookId, memberId, status, issueDate, :returnDate, issuedBy, :returnedTo ,NOW(),:loggedBy "
				+ "FROM issue_record WHERE issueId = :issueId";
		
		String updateSql = "UPDATE issue_record SET status = :status, returnDate = :returnDate, returnedTo = :returnedTo "
				+ "WHERE issueId = :issueId";
		
		int issueId = findActiveIssueId(bookId, memberId);
		LocalDateTime now = LocalDateTime.now();

		Map<String, Object> logParams = new HashMap<>();
		logParams.put("issueId", issueId);
		logParams.put("returnDate", now);
		logParams.put("returnedTo", returnedTo);
		logParams.put("loggedBy", returnedTo);

		Map<String, Object> updateParams = new HashMap<>();
		updateParams.put("status", IssueStatus.RETURNED.getCode());
		updateParams.put("returnDate", now);
		updateParams.put("returnedTo", returnedTo);
		updateParams.put("issueId", issueId);
		
		try {
			jdbcTemplate.update(logSql, logParams);
			jdbcTemplate.update(updateSql, updateParams);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ManagementException("Failed to return book from issue report dao");
		}
		
	}

	public List<IssueRecordDto> getAllIssues() {
	    String sql = """
	        SELECT ir.issueId AS issue_id,
	               ir.bookId AS book_id,
	               ir.memberId AS member_id,
	               ir.status,
	               ir.issueDate AS issue_date,
	               ir.returnDate AS return_date,
	               ir.issuedBy AS issued_by,
	               ir.returnedTo AS returned_to,
	               m.name AS member_name,
	               b.title AS book_title
	        FROM issue_record ir
	        JOIN members m ON ir.memberId = m.member_id
	        JOIN books b ON ir.bookId = b.book_id
	    """;
	    try {
	    	return jdbcTemplate.query(sql, new IssueRecordRowMapper());
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ManagementException("Failed to load issue records "+e);
		}
	    
	}

	private static class IssueRecordRowMapper implements RowMapper<IssueRecordDto> {
	    @Override
	    public IssueRecordDto mapRow(ResultSet rs, int rowNum) throws SQLException {
	        IssueRecordDto ir = new IssueRecordDto();
	        ir.setIssueId(rs.getInt("issue_id"));
	        ir.setBookId(rs.getInt("book_id"));
	        ir.setMemberId(rs.getInt("member_id"));
	        ir.setStatus(IssueStatus.fromCode(rs.getString("status"))); 
	        if (rs.getTimestamp("issue_date") != null)
	            ir.setIssueDate(rs.getTimestamp("issue_date").toLocalDateTime());
	        if (rs.getTimestamp("return_date") != null)
	            ir.setReturnDate(rs.getTimestamp("return_date").toLocalDateTime());
	        ir.setIssuedBy(rs.getString("issued_by"));
	        ir.setReturnedTo(rs.getString("returned_to"));
	        ir.setMemberName(rs.getString("member_name"));
	        ir.setBookTitle(rs.getString("book_title"));
	        return ir;
	    }
	}


}
