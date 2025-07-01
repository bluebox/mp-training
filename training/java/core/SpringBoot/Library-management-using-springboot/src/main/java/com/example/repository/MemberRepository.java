package com.example.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.Member;

@Repository
public class MemberRepository {
	private final JdbcTemplate jdbcTemplate;
	@Autowired
	public MemberRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	public int add(Member m) {
		return jdbcTemplate.update("insert into members values(?,?,?,?,?,?)",m.getMemberId(),m.getName(),m.getEmail(),m.getMobile(),String.valueOf(m.getGender()),m.getAddress());
	}
	public List<Map<String, Object>> showAll() {
		return jdbcTemplate.queryForList("select * from members");
	}
	public int update(int memberId,String name,String email,Long mobile,char gender,String address) {
		return jdbcTemplate.update("update members set Name=?,Email=?,Mobile=?,Gender=?,Address=? where MemberId=?",name,email,mobile,String.valueOf(gender),address);
	}
	public int delete(int memberId) {
		return jdbcTemplate.update("delete from members where MemberId=?",memberId);
	}
}
