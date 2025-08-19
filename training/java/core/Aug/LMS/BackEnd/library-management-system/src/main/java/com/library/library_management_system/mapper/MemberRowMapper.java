package com.library.library_management_system.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.library.library_management_system.domain.Member;
import com.library.library_management_system.utils.MemberGender;
import com.library.library_management_system.utils.MemberStatus;

public class MemberRowMapper implements RowMapper<Member> {

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {

		Member member = new Member();

		member.setId(rs.getInt("member_id"));
		member.setName(rs.getString("name"));
		member.setEmail(rs.getString("email"));
		member.setMobile(rs.getLong("mobile"));
		member.setGender(MemberGender.fromDbName(rs.getString("gender")));
		member.setAddress(rs.getString("address"));
		member.setStatus(MemberStatus.fromDbName(rs.getString("status")));

		return member;
	}

}
