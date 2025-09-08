package dev.kaushik.library.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import dev.kaushik.library.dao.IssueRecordDAO;
import dev.kaushik.library.model.IssueRecord;
import dev.kaushik.library.model.enums.IssueStatus;

@Repository
public class IssueRecordDAOImpl implements IssueRecordDAO {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public IssueRecordDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private final RowMapper<IssueRecord> issueRecordRowMapper = (rs, rowNum) -> {
        IssueRecord issueRecord = new IssueRecord();
        issueRecord.setIssueId(rs.getInt("issueId"));
        issueRecord.setBookId(rs.getInt("bookId"));
        issueRecord.setMemberId(rs.getInt("memberId"));
        issueRecord.setStatus(IssueStatus.fromCode(rs.getString("status")));
        issueRecord.setIssueDate(rs.getTimestamp("issueDate").toLocalDateTime());
        issueRecord.setIssuedBy(rs.getString("issued_by"));
        issueRecord.setReturnDate(rs.getTimestamp("returnDate") != null ? rs.getTimestamp("returnDate").toLocalDateTime() : null);
        issueRecord.setReturnedBy(rs.getString("returned_by"));
        return issueRecord;
    };

	@Override
	public int addIssueRecord(IssueRecord issueRecord) {
		String sql = "INSERT INTO issue_records (bookId,memberId,status,issueDate,issued_by) "
				+ "VALUES (:bookId,:memberId,:status,:issueDate,:issuedBy)";

		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("bookId", issueRecord.getBookId())
				.addValue("memberId", issueRecord.getMemberId())
				.addValue("status", issueRecord.getStatus().getCode())
				.addValue("issueDate", issueRecord.getIssueDate())
				.addValue("issuedBy", issueRecord.getIssuedBy());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[] { "issueId" });

		return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
	}

	@Override
	public boolean markAsReturned(IssueRecord issueRecord) {
		IssueRecord oldRecord = getActiveIssueRecordByBookId(issueRecord.getBookId());
	    
	    String sql = "UPDATE issue_records SET status = :status, returnDate = :returnDate, returned_by = :returnedBy " +
	                 "WHERE issueId = :issueId";
	    MapSqlParameterSource params = new MapSqlParameterSource()
	        .addValue("status", issueRecord.getStatus().getCode())
	        .addValue("returnDate", issueRecord.getReturnDate())
	        .addValue("returnedBy", issueRecord.getReturnedBy())
	        .addValue("issueId", oldRecord.getIssueId());

	    int rowsAffected = namedParameterJdbcTemplate.update(sql, params);
	    if (rowsAffected > 0) {
	        logIssueRecordChange(oldRecord);
	        return true;
	    }
	    return false;
	}
	
	private void logIssueRecordChange(IssueRecord issueRecord) {
        String sql = "INSERT INTO issue_records_log (IssueId,BookId,MemberId,Status,IssueDate,issued_by,ReturnDate,returned_by,LogDate) " +
                     "VALUES (:issueId,:bookId,:memberId,:status,:issueDate,:issuedBy,:returnDate,:returnedBy,CURRENT_TIMESTAMP)";

        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("issueId", issueRecord.getIssueId())
            .addValue("bookId", issueRecord.getBookId())
            .addValue("memberId", issueRecord.getMemberId())
            .addValue("status", issueRecord.getStatus().getCode())
            .addValue("issueDate", issueRecord.getIssueDate())
            .addValue("issuedBy", issueRecord.getIssuedBy())
            .addValue("returnDate", issueRecord.getReturnDate())
            .addValue("returnedBy", issueRecord.getReturnedBy());

        namedParameterJdbcTemplate.update(sql, params);
    }

	@Override
	public List<IssueRecord> getIssuedRecords(IssueRecord criteria) {
		StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM issue_records WHERE 1=1");
		MapSqlParameterSource params = new MapSqlParameterSource();
		if (criteria != null) {
			if (criteria.getIssueId() != null && criteria.getIssueId() != 0) {
				sqlBuilder.append(" AND issueId=:issueId");
				params.addValue("issueId", criteria.getIssueId());
			}
			if (criteria.getBookId() != null && criteria.getBookId() != 0) {
				sqlBuilder.append(" AND bookId=:bookId");
				params.addValue("bookId", criteria.getBookId());
			}
			if (criteria.getMemberId() != null && criteria.getMemberId() != 0) {
				sqlBuilder.append(" AND memberId=:memberId");
				params.addValue("memberId", criteria.getMemberId());
			}
			if (criteria.getStatus() != null) {
				sqlBuilder.append(" AND status=:status");
				params.addValue("status", criteria.getStatus().getCode());
			}
		}
		return namedParameterJdbcTemplate.query(sqlBuilder.toString(), params, issueRecordRowMapper);
	}

	@Override
	public IssueRecord getActiveIssueRecordByBookId(int bookId)  {
		String sql = "SELECT * FROM issue_records WHERE bookId = :bookId AND status = :status";

        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("bookId", bookId)
            .addValue("status", IssueStatus.ISSUED.getCode());

        return namedParameterJdbcTemplate.query(sql, params, issueRecordRowMapper).get(0);
	}

}
