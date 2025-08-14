package com.LMS.LibMS.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.LMS.LibMS.model.Member;
import com.LMS.LibMS.rowmapper.MemberRowMapper;

@Repository
public class MemberRepository {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public MemberRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
	}
	
	public List<Member> findMembers(){
		
		String sqlString = "SELECT * FROM members";
		
		return namedParameterJdbcTemplate.query(sqlString, new MemberRowMapper());
	}
	
	

}
