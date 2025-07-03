package com.library.app.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.library.app.model.Member;
import com.library.app.rowMappers.MemberRowMapper;

@Repository
public class MemberRepo {
	
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public MemberRepo(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int addMember(Member member) throws DataAccessException {
		String memberSql = "insert into members (Name, Email, Mobile, Gender, Address) values (?, ?, ?, ?, ?)";
		String memberLogSql = "insert into members_log (Name, Email, Mobile, Gender, Address, OperationType) values (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(memberSql, member.getName(), member.getEmail(), 
				member.getMobile(),member.getGender().getval(), member.getAddress());
		return jdbcTemplate.update(memberLogSql, member.getName(), member.getEmail(), 
				member.getMobile(),member.getGender().getval(), member.getAddress(), "Insert");

	}

	public int updateMember(Member member) throws Exception {
		String memberUpdateSql = "update members set Name=?, Email=?, Mobile=?, Gender=?, Address=? where MemberId=?";
		String memberUpdateLogSql = "insert into members_log (MemberId, Name, Email, Mobile, Gender, Address, OperationType) values (?, ?, ?, ?, ?, ?, ?)";
		int rowsChanged=jdbcTemplate.update(memberUpdateSql, member.getName(), member.getEmail(), member.getMobile(),
				member.getGender().getval(), member.getAddress(), member.getMemberId());
		jdbcTemplate.update(memberUpdateLogSql, member.getMemberId(), member.getName(), member.getEmail(),
				member.getMobile(), member.getGender().getval(), member.getAddress(), "Update");
		if (rowsChanged == 0) {
		    throw new Exception("No Member found with this Member ID : " + member.getMemberId());
		}
		return rowsChanged;

	}

	public Member getMemberById(int memberId)throws Exception {
		String sql = "select * from members where MemberId = ?";
		Member member = null;
		try {
		member = jdbcTemplate.queryForObject(sql, new MemberRowMapper(), memberId);
		}
		catch(Exception e) {
		    throw new Exception("No Member found with this Member ID : " +memberId);
		}
		return member;
	}

	public List<Member> getAllMembers() throws DataAccessException {
		String sql = "select * from members";
		return jdbcTemplate.query(sql, new MemberRowMapper());

	}

}
