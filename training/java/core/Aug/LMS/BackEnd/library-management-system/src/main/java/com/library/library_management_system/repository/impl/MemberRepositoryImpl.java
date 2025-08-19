package com.library.library_management_system.repository.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.domain.Member;
import com.library.library_management_system.exception.MemberNotFoundException;
import com.library.library_management_system.mapper.MemberRowMapper;
import com.library.library_management_system.repository.MemberRepository;
import com.library.library_management_system.utils.MemberStatus;
import com.library.library_management_system.utils.SQLQueries;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
	private final JdbcTemplate jdbcTemplate;

	public MemberRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int addMember(Member member) {

		return jdbcTemplate.update(SQLQueries.MEMBER_INSERT, member.getName(), member.getEmail(), member.getMobile(),
				member.getGender().getDbName(), member.getAddress(), MemberStatus.ACTIVE.getDbName());

	}

	@Override
	public boolean getMemberByMobile(Long mobile) {

		Integer count = jdbcTemplate.queryForObject(SQLQueries.MEMBER_SELECT_BY_MOBILE, Integer.class, mobile);
		return count != null && count > 0;

	}

	@Override
	public boolean getMemberByEmail(String email) {

		Integer count = jdbcTemplate.queryForObject(SQLQueries.MEMBER_SELECT_BY_EMAIL, Integer.class, email);
		return count != null && count > 0;

	}

	@Override
	public int UpdateMember(Member member) {

		return jdbcTemplate.update(SQLQueries.MEMBER_UPDATE, member.getName(), member.getEmail(), member.getMobile(),
				member.getGender().getDbName(), member.getAddress(), member.getId());

	}

	@Override
	public boolean getMemberByMobileExceptId(Long mobile, int id) {

		Integer count = jdbcTemplate.queryForObject(SQLQueries.MEMBER_SELECT_BY_MOBILE_EXCEPT_ID, Integer.class, mobile,
				id);
		return count != null && count > 0;

	}

	@Override
	public boolean getMemberByEmailExceptId(String email, int id) {

		Integer count = jdbcTemplate.queryForObject(SQLQueries.MEMBER_SELECT_BY_EMAIL_EXCEPT_ID, Integer.class, email,
				id);
		return count != null && count > 0;

	}

	@Override
	public Member getMemberById(int id) {

		List<Member> members = jdbcTemplate.query(SQLQueries.MEMBER_SELECT_BY_ID, new MemberRowMapper(), id);
		if (members.isEmpty()) {
			throw new MemberNotFoundException("Member with id " + id + " not found");
		}
		return members.get(0);

	}

	@Override
	public List<Member> getAllMembers() {

		return jdbcTemplate.query(SQLQueries.SELECT_ALL_MEMBERS, new MemberRowMapper());
	}

	@Override
	public int deleteMember(Member member) {

		return jdbcTemplate.update(SQLQueries.MEMBER_DELETE, member.getId());

	}

	@Override
	public int memberLog(Member member) {

		return jdbcTemplate.update(SQLQueries.MEMBERS_LOG_INSERT, member.getId(), member.getName(), member.getEmail(),
				member.getMobile(), member.getGender().getDbName(), member.getAddress(),
				member.getStatus().getDbName());

	}

}
