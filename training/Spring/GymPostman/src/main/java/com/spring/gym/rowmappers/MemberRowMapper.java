package com.spring.gym.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.springframework.jdbc.core.RowMapper;

import com.spring.gym.beans.Member;

public class MemberRowMapper implements RowMapper<Member> {
	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member();
		member.setId(rs.getInt("id"));
		member.setName(rs.getString("name"));
		member.setAge(rs.getInt("age"));
		member.setMemberships(Arrays.asList(rs.getString("memberships").split(",")));
		member.setJoinDate(rs.getDate("joinDate"));
		member.setExpireDate(rs.getDate("expiryDate"));
		member.setStatus(rs.getString("status"));
		return member;
	}
}
