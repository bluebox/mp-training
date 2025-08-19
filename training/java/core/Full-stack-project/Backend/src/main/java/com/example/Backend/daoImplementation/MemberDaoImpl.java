package com.example.Backend.daoImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.Backend.constants.MemberGender;
import com.example.Backend.dao.MemberDao;
import com.example.Backend.domain.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final RowMapper<Member> memberRowMapper = (rs, rowNum) -> {
		Member member = new Member();
		member.setMemberId(rs.getInt("member_id"));
		member.setMemberName(rs.getString("name"));
		member.setMemberMail(rs.getString("email"));
		member.setMobileNo(rs.getString("mobile"));
		member.setGender(MemberGender.fromAny(rs.getString("gender")));
		member.setMemberAddress(rs.getString("address"));
		return member;
	};

	@Override
	public int addMember(Member member) {
		String sql = "insert into members(name,email,mobile,gender,address) values(?,?,?,?,?)";
		int rows = jdbcTemplate.update(sql, member.getMemberName(), member.getMemberMail(), member.getMobileNo(),
				member.getGender() != null ? member.getGender().getDbValue() : null, member.getMemberAddress());
		return rows;
	}

	@Override
	public int updateMember(Member member) {
		memberlog(member.getMemberId());
		String sql = "update members set name=?,email=?,mobile=?,gender=?,address=? where member_id=?";
		int rows = jdbcTemplate.update(sql, member.getMemberName(), member.getMemberMail(), member.getMobileNo(),
				member.getGender() != null ? member.getGender().getDbValue() : null, member.getMemberAddress(),
				member.getMemberId());
		return rows;
	}

	@Override
	public List<Member> findAllMembers() {
		String sql = "select member_id,name,email,mobile,gender,address from members";
		return jdbcTemplate.query(sql, memberRowMapper);
	}

	@Override
	public Member findById(int id) {
		String sql = "select member_id,name,email,mobile,gender,address from members where member_id=?";
		return jdbcTemplate.queryForObject(sql, memberRowMapper, id);
	}

	@Override
	public int memberlog(int memberId) {
		String sql = "insert into members_log(member_id,name,email,mobile,gender,address) "
				+ "select member_id,name,email,mobile, gender, address from members where member_id=?";
		return jdbcTemplate.update(sql, memberId);
	}

}
