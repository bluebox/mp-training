package com.spring.gym.dao;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.spring.gym.beans.Member;

@Repository
public class MemberDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public MemberDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public boolean addMember(Member member) {
		try {
			String sql = "INSERT INTO member(name,age,memberships,joinDate,expiryDate,status) VALUES (:name,:age,:memberships,:joinDate,:expiryDate,:status)";
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(member.getJoinDate());
			calendar.add(Calendar.MONTH, 1); // Add 1 month
			Date expiryDate = calendar.getTime();

			MapSqlParameterSource params = new MapSqlParameterSource()
			    .addValue("name", member.getName())
			    .addValue("age", member.getAge())
			    .addValue("memberships", String.join(",", member.getMemberships()))
			    .addValue("joinDate", member.getJoinDate())
			    .addValue("expiryDate", expiryDate)
			    .addValue("status", "ACTIVE");
			return jdbcTemplate.update(sql,params)>0;
			
		} catch (Exception e) {
			return false;
		}
	}

}
