package dev.tulasidhar.lms.DAO.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dev.tulasidhar.lms.model.Member;

public class MemberRowMapper implements RowMapper<Member> {

	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member();
		member.setMember_Id(rs.getInt("MemberId"));
		member.setMember_Name(rs.getString("Name"));
		member.setMobile_No(rs.getString("Mobile"));
		member.setEmail(rs.getString("Email"));
		member.setAddress(rs.getString("Address"));
		member.setGender(rs.getString("Gender").charAt(0));
		return member;
	}
}
