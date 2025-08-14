package com.LMS.LibMS.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.LMS.LibMS.model.IssueRecord;
import com.LMS.LibMS.rowmapper.IssuedRowMapper;

@Repository
public class IssueRepository {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public IssueRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
	}
	
	
	public List<IssueRecord> findIssuedBooks(){
		String sqString = "SELECT * FROM issue_records";
		return namedParameterJdbcTemplate.query(sqString, new IssuedRowMapper());
	}

	
}
