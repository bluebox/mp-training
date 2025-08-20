package com.library.library_management_system.row_mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.library.library_management_system.domain.Member;

public class MemberRowMapper implements RowMapper<Member> {

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member();
		member.setMemberId(rs.getInt("member_id"));
		member.setMemberName(rs.getString("name"));
		member.setMemberMail(rs.getString("email"));
		member.setMobileNo(rs.getString("mobile")); 
		member.setGender(rs.getString("gender"));
		member.setMemberAddress(rs.getString("address"));
		return member;
	}

}
