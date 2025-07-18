package com.example.library.daoimpl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.library.dao.MemberDao;
import com.example.library.exception.DatabaseException;
import com.example.library.model.Member;
import com.example.library.model.MemberIssueDTO;

@Repository
public class MemberDAOImpl implements MemberDao{

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public MemberDAOImpl(JdbcTemplate jdbcTemplate) {
	    this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addMember(Member member) throws DatabaseException {
		String insert = "INSERT INTO members (name, Email, mobile, gender, address) VALUES (?, ?, ?, ?, ?)";
		try {
			jdbcTemplate.update(insert,
				member.getName(),
				member.getEmail(),
				member.getMobile(),
				String.valueOf(member.getGender()),
				member.getAddress()
			);
		} catch (Exception e) {
			throw new DatabaseException("Error adding member", e);
		}
	}

	@Override
	public void updateMember(Member member) throws Exception {
		String update = "UPDATE members SET name=?, email=?, mobile=?, gender=?, address=? WHERE memberId=?";
		String insertLog = "INSERT INTO members_log (memberId, name, email, mobile, gender, address) VALUES (?, ?, ?, ?, ?, ?)";
		Member existingMember = getMemberById(member.getMemberId());
		if (existingMember == null) {
			throw new Exception("Member not found");
		}
		if (member.equals(existingMember)) {
			throw new Exception("No change in values");
		}
		jdbcTemplate.update(insertLog,
			existingMember.getMemberId(),
			existingMember.getName(),
			existingMember.getEmail(),
			existingMember.getMobile(),
			String.valueOf(existingMember.getGender()),
			existingMember.getAddress()
		);
		jdbcTemplate.update(update,
			member.getName(),
			member.getEmail(),
			member.getMobile(),
			String.valueOf(member.getGender()),
			member.getAddress(),
			member.getMemberId()
		);
	}

	@Override
	public List<Member> getAllMembers() throws Exception {
		String query = "SELECT memberId, name, email, mobile, gender, address FROM members";
		return jdbcTemplate.query(query, (rs, rowNum) -> new Member(
			rs.getInt("memberId"),
			rs.getString("name"),
			rs.getString("email"),
			rs.getLong("mobile"),
			rs.getString("gender").charAt(0),
			rs.getString("address")
		));
	}

	@Override
	public Member getMemberById(int memberId) throws Exception {
		String query = "SELECT memberId, name, email, mobile, gender, address FROM members where memberId=?";
		List<Member> members = jdbcTemplate.query(query, (rs, rowNum) -> new Member(
			rs.getInt("memberId"),
			rs.getString("name"),
			rs.getString("email"),
			rs.getLong("mobile"),
			rs.getString("gender").charAt(0),
			rs.getString("address")
		), memberId);
		return members.isEmpty() ? null : members.get(0);
	}

	@Override
	public List<MemberIssueDTO> getMembersWithActiveIssueBooks() {
		String query = 
			"SELECT m.MemberId, m.Name AS MemberName, m.Mobile, m.Address, " +
			"b.BookId, b.Title AS BookName, ir.IssueDate " +
			"FROM members m " +
			"JOIN issue_records ir ON m.MemberId = ir.MemberId " +
			"JOIN books b ON b.BookId = ir.BookId " +
			"WHERE ir.Status = 'I'";
		try {
			return jdbcTemplate.query(query, (rs, rowNum) -> new MemberIssueDTO(
				rs.getInt("MemberId"),
				rs.getString("MemberName"),
				rs.getInt("BookId"),
				rs.getString("BookName"),
				rs.getLong("Mobile"),
				rs.getString("Address"),
				rs.getDate("IssueDate").toLocalDate()
			));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
}
