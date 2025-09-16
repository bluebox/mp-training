package com.LMS.LibMS.repository.interfaceImpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.LMS.LibMS.model.Member;
import com.LMS.LibMS.repository.interfaces.MemberRepository;
import com.LMS.LibMS.rowmapper.MemberRowMapper;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public MemberRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public void addMember(Member member) {
		String sql = "INSERT INTO members (name, email, phoneNumber, gender, address, created_at, created_by) "
				+ "VALUES (:name, :email, :phoneNumber, :gender, :address, :createdAt, :createdBy)";

		MapSqlParameterSource params = memberparams(member, "add");

		namedParameterJdbcTemplate.update(sql, params);
	}

	@Override
	public List<Member> findMembersById(List<Integer> ids) {

		String sql = "SELECT memberID, name, email, phoneNumber, gender, address, created_at, created_by, updated_at, updated_by FROM members WHERE memberID IN( :id)";
		List<Member> members = namedParameterJdbcTemplate.query(sql, Map.of("id", ids), new MemberRowMapper());
		return members.isEmpty() ? null : members;
	}

	@Override
	public List<Member> getAllMembers() {
		String sql = "SELECT memberID, name, email, phoneNumber, gender, address, created_at, created_by, updated_at, updated_by FROM members";
		return namedParameterJdbcTemplate.query(sql, new MemberRowMapper());
	}

	@Override
	public boolean updateMember(Member member) throws Exception {

		String sql = "UPDATE members SET name = :name, email = :email, phoneNumber = :phoneNumber, "
				+ "gender = :gender, address = :address, updated_at = :updatedAt, updated_by = :updatedBy "
				+ "WHERE memberID = :memberID";

		MapSqlParameterSource params = memberparams(member, "update");

		int rows = namedParameterJdbcTemplate.update(sql, params);
		return rows > 0;
	}

	@Override
	public boolean deleteMembersById(List<Integer> memberIds) throws Exception {
		String sql = "DELETE FROM members WHERE memberID IN (:ids)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("ids", memberIds);
		int rows = namedParameterJdbcTemplate.update(sql, params);
		return rows > 0;
	}

	@Override
	public boolean logMember(Member member) {

		String insertSql = "INSERT INTO members_log (MemberId, Name, Email, PhoneNumber, Gender, Address, "
				+ "created_at, created_by, updated_at, updated_by, LogDate) "
				+ "VALUES (:memberId, :name, :email, :phoneNumber, :gender, :address, "
				+ ":createdAt, :createdBy, :updatedAt, :updatedBy, :logDate)";

		MapSqlParameterSource params = memberparams(member, "logs");
		
		params.addValue("logDate", new Timestamp(System.currentTimeMillis()));

		int rowsInserted = namedParameterJdbcTemplate.update(insertSql, params);
		return rowsInserted > 0;
	}
	
	
	private  MapSqlParameterSource memberparams(Member member , String flag) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("name", member.getName());
		params.addValue("email", member.getEmail());
		params.addValue("phoneNumber", member.getPhoneNumber());
		params.addValue("gender", String.valueOf(member.getGender().getCode()));
		params.addValue("address", member.getAddress());
		
		if(flag.equals("add") || flag.equals("logs")) {
			params.addValue("createdAt", member.getCreatedAt());
			params.addValue("createdBy", member.getCreatedBy());			
		}
		
		if(flag.equals("update") || flag.equals("logs")) {
			params.addValue("memberId", member.getMemberID());
			params.addValue("updatedAt", member.getUpdatedAt());
			params.addValue("updatedBy", member.getUpdatedBy());			
		}
		
		return params;
		
	}
}