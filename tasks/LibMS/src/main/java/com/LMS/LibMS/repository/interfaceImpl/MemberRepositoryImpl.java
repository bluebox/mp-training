package com.LMS.LibMS.repository.interfaceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.LMS.LibMS.model.Member;
import com.LMS.LibMS.repository.interfaces.MemberRepository;
import com.LMS.LibMS.rowmapper.MemberRowMapper;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public MemberRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
	}
	
	@Override
	public List<Member> findMembers(){
		
		String sqlString = "SELECT * FROM members";
		
		return namedParameterJdbcTemplate.query(sqlString, new MemberRowMapper());
	}
	
	

}
