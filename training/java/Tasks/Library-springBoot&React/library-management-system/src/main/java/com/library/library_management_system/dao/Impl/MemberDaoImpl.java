package com.library.library_management_system.dao.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.dao.MemberDaoInterface;
import com.library.library_management_system.domain.Member;
import com.library.library_management_system.row_mapper.MemberRowMapper;

@Repository
public class MemberDaoImpl implements MemberDaoInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Member insertMember(Member member) {
		String sql = "INSERT INTO members (name, email, mobile, gender, address) VALUES (?, ?, ?, ?, ?)";
		int rows = jdbcTemplate.update(sql, member.getMemberName(), member.getMemberMail(), member.getMobileNo(),
				member.getGender(), member.getMemberAddress());
		if (rows == 0) {
			return null;
		}
		return member;
	}

	@Override
	public Member updateMember(Member member) {
		String sql = "UPDATE members SET name=?, email=?, mobile=?, gender=?, address=? WHERE member_id=?";
		int rows = jdbcTemplate.update(sql, member.getMemberName(), member.getMemberMail(), member.getMobileNo(),
				member.getGender(), member.getMemberAddress(), member.getMemberId());
		if (rows == 0) {
			return null;
		}
		return getMemberById(member.getMemberId());
	}

	@Override
	public int deleteMember(int memberId) {
		String sql = "DELETE FROM members WHERE member_id=?";
		return jdbcTemplate.update(sql, memberId);
	}

	@Override
	public Member getMemberById(int id) {
		try {
			String sql = "SELECT * FROM members WHERE member_id=?";
			return jdbcTemplate.queryForObject(sql, new MemberRowMapper(), id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Member> getAllMembers() {
		String sql = "SELECT * FROM members";
		return jdbcTemplate.query(sql, new MemberRowMapper());
	}
}
