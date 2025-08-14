package com.LMS.LibMS.repository.interfaceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.LMS.LibMS.model.IssueRecord;
import com.LMS.LibMS.repository.interfaces.IssueRepository;
import com.LMS.LibMS.rowmapper.IssuedRowMapper;

@Repository
public class IssueRepositoryImpl implements IssueRepository{
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public IssueRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
	}
	
	@Override
	public List<IssueRecord> findIssuedBooks(){
		String sqString = "SELECT * FROM issue_records";
		return namedParameterJdbcTemplate.query(sqString, new IssuedRowMapper());
	}

	
}
