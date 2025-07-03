package com.library.app.rowMappers;

import java.sql.*;

import org.springframework.jdbc.core.RowMapper;

import com.library.app.model.Gender;
import com.library.app.model.Member;

public class MemberRowMapper implements RowMapper<Member> {
	   @Override
	    public Member mapRow(ResultSet rs, int rowNumber) throws SQLException {
	        Member member = new Member();
	        member.setMemberId(rs.getInt("MemberId"));
			member.setName(rs.getString("Name"));
			member.setEmail(rs.getString("Email"));
			member.setMobile(rs.getLong("Mobile"));
			char genderChar = rs.getString("Gender").charAt(0);
			if (genderChar == 'M') {
			    member.setGender(Gender.MALE);
			} else {
			    member.setGender(Gender.FEMALE);
			}
			member.setAddress(rs.getString("Address"));
	        return member;
	    }
}
