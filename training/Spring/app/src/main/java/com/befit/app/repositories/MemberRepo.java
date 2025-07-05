package com.befit.app.repositories;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.befit.app.beans.Member;

@Repository
public class MemberRepo {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public MemberRepo(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	public boolean addMember(Member member) {
		String sql = "insert into member (name,mobile,age,weight,height,address,memberShip,joinDate,expiryDate,status) values (:name,:mobile,:age,:weight,:height,:address,:memberShip,:joinDate,:expiryDate,:status)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", member.getName());
		params.addValue("mobile", member.getMobile());
		params.addValue("age", member.getAge());
		params.addValue("weight", member.getWeight());
		params.addValue("height", member.getHeight());
		params.addValue("address", member.getAddress());
		params.addValue("membership", member.getMemberShip());
		params.addValue("joinDate", member.getJoinDate());
		params.addValue("expiryDate", member.getExpiryDate());
		params.addValue("status", member.getStatus());
		
		try {
			namedParameterJdbcTemplate.update(sql, params);
			return true;
		}catch(Exception e) {
			System.out.println(e.getStackTrace());
			return false;
		}
	}
	
	public boolean updateMember(Member member) {
		String sql = "update  member set name=:name,mobile=:mobile,age=:age,weight=:weight,height=:height,address=:address,memberShip=:memberShip,joinDate=:joinDate,expiryDate=:expiryDate,status=:status where id=:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", member.getId());
		params.addValue("name", member.getName());
		params.addValue("mobile", member.getMobile());
		params.addValue("age", member.getAge());
		params.addValue("weight", member.getWeight());
		params.addValue("height", member.getHeight());
		params.addValue("address", member.getAddress());
		params.addValue("membership", member.getMemberShip());
		params.addValue("joinDate", member.getJoinDate());
		params.addValue("expiryDate", member.getExpiryDate());
		params.addValue("status", member.getStatus());
		
		try {
			namedParameterJdbcTemplate.update(sql, params);
			return true;
		}catch(Exception e) {
			System.out.println(e.getStackTrace());
			return false;
		}
	}
	
//	public Date expiry(Member member) {
//		MapSqlParameterSource params = new MapSqlParameterSource();
//		params.addValue("id", member.getId());
//		try {
//			Date expiryDate =namedParamterJdbcTemplate.queryForObject("select expiryDate from member where id = :id)",params,Date.class);
//			return expiryDate;
//		}catch(Exception e) {
//			System.out.println(e.getStackTrace());
//			return null;
//		}
//	}
	
	public boolean renew(Member member,Date expiryDate) {
		
		String sql = "update  member set expiryDate = :expiryDate,status='ACTIVE' where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", member.getId());
		params.addValue("expiryDate", expiryDate);
		
		try {
			namedParameterJdbcTemplate.update(sql, params);
			return true;
		}catch(Exception e) {
			System.out.println(e.getStackTrace());
			return false;
		}
	}
	
	
	public Member viewMember(Member member) {
		String sql = "select id,name,mobile,age,weight,height,address,memberShip,joinDate,expiryDate,status from member where id = :id)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", member.getId());
		try {
			Member returnMember = namedParameterJdbcTemplate.queryForObject(sql, params,new BeanPropertyRowMapper<>(Member.class));
			return returnMember;
		}catch(Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
	}
	
	public List<Member> viewAllMember() {
		String sql = "select (id,name,mobile,age,weight,height,address,memberShip,joinDate,expiryDate,status) from member)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		try {
			List<Member> returnMember = namedParameterJdbcTemplate.query(sql, params,new BeanPropertyRowMapper<>(Member.class));
			return returnMember;
		}catch(Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
	}
	
	public List<Member> findMembersByJoinDateBetween(Date startDate, Date endDate) {
		try {
			String sql = "SELECT id,name,mobile,age,weight,height,address,memberShip,joinDate,expiryDate,status FROM member WHERE joinDate BETWEEN :start AND :end";

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("start", startDate);
			params.addValue("end", endDate);

			return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper(Member.class));

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	

}
