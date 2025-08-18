package com.lms.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lms.model.Member;

@Repository
public class MemberRepository {

	private JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public Member registerMember(Member member) {

		String sql = "INSERT INTO member(name,email,mobile,gender,address) VALUES(?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, member.getName());
			ps.setString(2, member.getEmail());
			ps.setLong(3, member.getMobile());
			ps.setString(4, member.getGender());
			ps.setString(5, member.getAddress());
			return ps;
		}, keyHolder);
		member.setMemberId(keyHolder.getKey().intValue());

		return member;
	}

	public Member updateMember(Member member) {

		String updateMember = "UPDATE member SET name=?, email=?, mobile=? , gender=? , address=? WHERE memberId=?";
		String updateMember_log = "INSERT INTO member_log(memberId,name,email,mobile,gender,address,operation_type) VALUES (?,?,?,?,?,?,?)";

		template.update(updateMember_log, member.getMemberId(), member.getName(), member.getEmail(),member.getMobile(), member.getGender(),
				member.getAddress(), "Update");
		template.update(updateMember, member.getName(), member.getEmail(), member.getMobile(), member.getGender(),
				member.getAddress(), member.getMemberId());
		return member;
	}

	public List<Member> getAllMembers() {
		String sql = "SELECT * FROM member";
		List<Member> members = template.query(sql, (rs, row) -> {
			Member member = new Member();
			member.setMemberId(rs.getInt(1));
			member.setName(rs.getString("name"));
			member.setEmail(rs.getString(3));
			member.setMobile(rs.getLong(4));
			member.setGender(rs.getString(5));
			member.setAddress(rs.getString(6));
			return member;
		});
		return members;
	}

	private final RowMapper<Member> memberRowMapper = (rs, rowNum) -> {
		Member member = new Member();
		member.setMemberId(rs.getInt(1));
		member.setName(rs.getString("name"));
		member.setEmail(rs.getString(3));
		member.setMobile(rs.getLong(4));
		member.setGender(rs.getString(5));
		member.setAddress(rs.getString(6));
		return member;
	};

	public Optional<Member> fetchMemberById(int id) {
		String sql = "SELECT * FROM member WHERE MemberId = ?";
		List<Member> member = template.query(sql, ps -> ps.setInt(1, id), memberRowMapper);
		return member.isEmpty() ? Optional.empty() : Optional.of(member.get(0));
	}
}
