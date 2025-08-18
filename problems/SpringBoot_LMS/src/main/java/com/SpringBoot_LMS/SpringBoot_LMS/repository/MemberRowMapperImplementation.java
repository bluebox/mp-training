package com.SpringBoot_LMS.SpringBoot_LMS.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.SpringBoot_LMS.SpringBoot_LMS.model.Gender;
import com.SpringBoot_LMS.SpringBoot_LMS.model.Member;
@Component
public class MemberRowMapperImplementation implements RowMapper<Member>{

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
	    Member member=new Member();
	    member.setMemberId(rs.getInt("MemberId"));
	    member.setName(rs.getString("Name"));
	    member.setEmail(rs.getString("Email"));
	    member.setMobile(rs.getString("Mobile"));
	    member.setGender(Gender.getGender(rs.getString("Gender")));
        member.setAddress(rs.getString("Address"));
		return member;
	
	}

}
