package com.LMS.LibMS.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.LMS.LibMS.model.Member;
import com.LMS.LibMS.model.enums.Gender;

public class MemberRowMapper implements RowMapper<Member>{

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member();
		
		member.setMemberID(rs.getInt("memberID"));
		member.setName(rs.getString("name"));
		member.setEmail(rs.getString("email"));
		member.setPhoneNumber(rs.getLong("phoneNumber"));
		member.setGender(Gender.fromCode(rs.getString("gender")));
		member.setAddress(rs.getString("address"));
		member.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
		member.setCreatedBy(rs.getString("created_by"));
		if(rs.getTimestamp("updated_at")!=null) {
			member.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());			
		}
		member.setUpdatedBy(rs.getString("updated_by"));
		
		return member;
	}
	

}
