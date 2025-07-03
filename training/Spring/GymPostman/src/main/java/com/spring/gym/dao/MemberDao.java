package com.spring.gym.dao;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.gym.beans.Member;
import com.spring.gym.rowmappers.MemberRowMapper;

@Repository
public class MemberDao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public MemberDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public boolean addMember(Member member, int amount, String days) {
		try {
			String sql = "INSERT INTO member (name, age, memberships, joinDate, expiryDate, status,amount,period,refund) "
					+ "VALUES (:name, :age, :memberships, :joinDate, :expiryDate, :status,:amount,:period,:refund)";

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(member.getJoinDate());
			calendar.add(Calendar.MONTH, 1);
			if (days.equals("year")) {
				calendar.add(Calendar.MONTH, 11);
			}
			Date expiryDate = calendar.getTime();

			MapSqlParameterSource params = new MapSqlParameterSource().addValue("name", member.getName())
					.addValue("age", member.getAge()).addValue("memberships", String.join(",", member.getMemberships()))
					.addValue("joinDate", member.getJoinDate()).addValue("expiryDate", expiryDate)
					.addValue("status", "ACTIVE").addValue("amount", amount).addValue("period", days)
					.addValue("refund", "ACTIVE");

			return namedParameterJdbcTemplate.update(sql, params) > 0;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean findMember(int id) {
		try {
			String sql = "SELECT 1 FROM member WHERE id = :id";
			MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);

			Integer result = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
			return result == 1;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateMember(int id, String days) {
		try {
			String sql = "select expiryDate from member where id = :id";
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("id", id);
			Date expiryDate = namedParameterJdbcTemplate.queryForObject(sql, params, Date.class);
			Date today = Date.from(Instant.now());
			if (expiryDate.before(today)) {
				if (days.equals("month")) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(today);
					calendar.add(Calendar.MONTH, 1);
					expiryDate = calendar.getTime();
				} else {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(today);
					calendar.add(Calendar.MONTH, 12);
					expiryDate = calendar.getTime();
				}
			} else {
				if (days.equals("month")) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(expiryDate);
					calendar.add(Calendar.MONTH, 1);
					expiryDate = calendar.getTime();
				} else {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(expiryDate);
					calendar.add(Calendar.MONTH, 12);
					expiryDate = calendar.getTime();
				}
			}

			sql = "update member set expiryDate = :expiryDate where id = :id";
			params.addValue("expiryDate", expiryDate);

			return namedParameterJdbcTemplate.update(sql, params) > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteMember(Member member) {
		try {
			String sql = "delete from member where id = :id";
			MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", member.getId());
			return namedParameterJdbcTemplate.update(sql, params) > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Member> findMembersByJoinDateBetween(Date startDate, Date endDate) {
		try {
			String sql = "SELECT id,name, age, memberships, joinDate, expiryDate, status FROM member WHERE joinDate BETWEEN :start AND :end";

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("start", startDate);
			params.addValue("end", endDate);

			return namedParameterJdbcTemplate.query(sql, params, new MemberRowMapper());

		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	public Member findMemberById(int id) {
		try {
			String sql = "SELECT id,name, age, memberships, joinDate, expiryDate, status FROM member WHERE id = :id";
			MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);

			return namedParameterJdbcTemplate.queryForObject(sql, params, new MemberRowMapper());

		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Member> viewAllMembers() {
		try {
			String sql = "SELECT id,name, age, memberships, joinDate, expiryDate, status FROM member";

			return namedParameterJdbcTemplate.query(sql, new MemberRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	public List<Member> findMembersByStatus(String status) {
		try {
			String sql = "SELECT id,name, age, memberships, joinDate, expiryDate, status FROM member WHERE status = :status";

			MapSqlParameterSource params = new MapSqlParameterSource().addValue("status", status);

			return namedParameterJdbcTemplate.query(sql, params, new MemberRowMapper());

		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	public boolean updateMemberStatus(int id, String status) {
		try {
			String sql = "UPDATE member SET status = :status WHERE id = :id";
			MapSqlParameterSource params = new MapSqlParameterSource().addValue("status", status).addValue("id", id);

			return namedParameterJdbcTemplate.update(sql, params) > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Member> findByMembership(String membership) {
		try {
			String sql = "SELECT id,name, age, memberships, joinDate, expiryDate, status from member where memberships LIKE  :membership ";
			MapSqlParameterSource params = new MapSqlParameterSource().addValue("membership", "%" + membership + "%");
			return namedParameterJdbcTemplate.query(sql, params, new MemberRowMapper());
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	public List<Member> viewAllMembersByRefund() {
		try {
			String sql = "SELECT id,name, age, memberships, joinDate, expiryDate, status FROM member where refund = 'ACTIVE'";

			return namedParameterJdbcTemplate.query(sql, new MemberRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	public void updateMemberByRefund(Member member) {
		try {
			String sql = "update member set refund = 'INACTIVE' where id = :id";
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("id", member.getId());
			namedParameterJdbcTemplate.update(sql, params);
		} catch (Exception e) {

		}
	}
	public void cancel(int id) {
		String sql = "update member set refund = 'INACTIVE',expiryDate = :expiryDate where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		params.addValue("expiryDate", Date.from(Instant.now()));
		namedParameterJdbcTemplate.update(sql, params);
	}
	
	public List<String> amountAndRefund(int id){
		List<String> val = new ArrayList<>();
		String sql = "SELECT refund from member where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		val.add(namedParameterJdbcTemplate.queryForObject(sql, params,String.class));
		sql = "SELECT amount from member where id = :id";
		val.add(namedParameterJdbcTemplate.queryForObject(sql, params,String.class));
		sql = "SELECT period from member where id = :id";
		val.add(namedParameterJdbcTemplate.queryForObject(sql, params,String.class));
		sql = "SELECT joinDate from member where id = :id";
		val.add(namedParameterJdbcTemplate.queryForObject(sql, params,String.class));
		return val;

	}
}
