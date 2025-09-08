package com.medplus.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.medplus.lms.domain.Gender;
import com.medplus.lms.domain.IssueStatus;
import com.medplus.lms.domain.Member;
import com.medplus.lms.domain.Status;
import com.medplus.lms.exceptions.ManagementException;

@Repository
public class MemberRepository implements MemberRepositoryInterface{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MemberRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Member getMemberByEmailOrMobile(String email, String mobile) {
        String sql = "SELECT member_id, name, email, mobile, gender, address, status, " +
                     "created_by, created_at, updated_by, updated_at " +
                     "FROM members WHERE (email = :email OR mobile = :mobile)";
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("mobile", mobile);
        List<Member> list;
        try {
        	list= jdbcTemplate.query(sql, params, new MemberRowMapper());
        }
        catch(Exception e) {
        	throw new ManagementException("Failed in getting member by email or mobile in member dao");
        }
        return list.isEmpty() ? null : list.get(0);
    }
    
    public void addMember(Member member) {
        String sql = "INSERT INTO members " +
                     "(name, email, mobile, gender, address, status, created_by,created_at,updated_by,updated_at) " +
                     "VALUES (:name, :email, :mobile, :gender, :address, :status, :createdBy,NOW(),:updatedBy,NOW())";
        Map<String, Object> params = new HashMap<>();
        params.put("name", member.getName());
        params.put("email", member.getEmail());
        params.put("mobile", member.getMobile());
        params.put("gender", member.getGender().getCode());
        params.put("address", member.getAddress());
        params.put("status", member.getStatus().getCode());
        params.put("createdBy", member.getCreatedBy());
        params.put("updatedBy", member.getCreatedBy());
        
        try {
        	jdbcTemplate.update(sql, params);
        }
        catch(Exception e) {
        	e.printStackTrace();
        	throw new ManagementException("Failed to add member from member dao");
        }
    }


    public List<Member> getAllMembers() {
        String sql = "select member_id, name, email, mobile, gender, address, status, created_by, created_at, updated_by, updated_at from members";
        try {
        	return jdbcTemplate.query(sql, new MemberRowMapper());
        }
        catch(Exception e) {
        	e.printStackTrace();
        	throw new ManagementException("Failed to get all members from member dao");
        }
        
    }

    public Member findMemberById(int memberId) {
        String sql = "select member_id, name, email, mobile, gender, address, status, created_by, created_at, updated_by, updated_at from members where member_id = :memberId";
        Map<String, Object> params = Map.of("memberId", memberId);
        List<Member> list;
        try {
        	list = jdbcTemplate.query(sql, params, new MemberRowMapper());
        }
        catch(Exception e) {
        	e.printStackTrace();
        	throw new ManagementException("Failed to find  member from member dao");
        }
        return list.isEmpty() ? null : list.get(0);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateMember(Member member) throws ManagementException {
        String logSql = "INSERT INTO members_log " +
                "(member_id, name, email, mobile, gender, address, status, created_by, created_at, updated_by, updated_at,logged_time,logged_by) " +
                "SELECT member_id, name, email, mobile, gender, address, status, created_by, created_at, updated_by, updated_at,NOW(),:loggedBy " +
                "FROM members WHERE member_id = :memberId";
        
        String sql = "UPDATE members SET name = :name, email = :email, mobile = :mobile, gender = :gender, address = :address, " +
                "updated_by = :updatedBy, updated_at = NOW() WHERE member_id = :memberId";

        Map<String, Object> logParams = new HashMap<>();
        logParams.put("memberId", member.getMemberId());
        logParams.put("loggedBy", member.getUpdatedBy());
      

        
        Map<String, Object> params = new HashMap<>();
        params.put("memberId", member.getMemberId());
        params.put("name", member.getName());
        params.put("email", member.getEmail());
        params.put("mobile", member.getMobile());
        params.put("gender", member.getGender().getCode());
        params.put("address", member.getAddress());
        params.put("updatedBy",  member.getUpdatedBy());
        try {
        	jdbcTemplate.update(logSql, logParams);
        	jdbcTemplate.update(sql, params);
        }
        catch(Exception e) {
        	e.printStackTrace();
        	throw new ManagementException("Failed to update member with ID: " + member.getMemberId());
        }
    }


    private static class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setMemberId(rs.getInt("member_id"));
            member.setName(rs.getString("name"));
            member.setEmail(rs.getString("email"));
            member.setMobile(rs.getString("mobile"));
            member.setGender(Gender.fromCode(rs.getString("gender")));
            member.setAddress(rs.getString("address"));
            member.setStatus(Status.fromCode(rs.getString("status")));
            member.setCreatedBy(rs.getString("created_by"));
            if (rs.getTimestamp("created_at") != null) {
                member.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
            member.setUpdatedBy(rs.getString("updated_by"));
            if (rs.getTimestamp("updated_at") != null) {
                member.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            }
            return member;
        }
    }
    public boolean hasActiveIssuedBooks(int memberId) {
        String sql = "SELECT COUNT(issueId) FROM issue_record WHERE memberId = :memberId AND status = :status";
        
        Map<String, Object> params = new HashMap<>();
        params.put("memberId", memberId);
        params.put("status", IssueStatus.ISSUED.getCode());
        Integer count;
        try {
        	count = jdbcTemplate.queryForObject(sql, params, Integer.class);
        }
        catch(Exception e) {
        	e.printStackTrace();
        	throw new ManagementException("Failed to update member with ID: " + memberId);
        }
        return count != null && count > 0;
    }

    @Transactional(rollbackFor=Exception.class)
    public void updateMemberStatus(Member member) {
    	String logSql = "INSERT INTO members_log " +
                "(member_id, name, email, mobile, gender, address, status, created_by, created_at, updated_by, updated_at,logged_time,logged_by) " +
                "SELECT member_id, name, email, mobile, gender, address, status, created_by, created_at, updated_by, updated_at,NOW(),:loggedBy " +
                "FROM members WHERE member_id = :memberId";
    	
        String sql = "UPDATE members SET status = :status WHERE member_id = :memberId";
        
        Map<String, Object> logParams = new HashMap<>();
        logParams.put("memberId", member.getMemberId());
        logParams.put("loggedBy", member.getUpdatedBy());
        
        
        Map<String, Object> params = new HashMap<>();
        params.put("status", member.getStatus().getCode());
        params.put("memberId", member.getMemberId());
        try {
        	jdbcTemplate.update(logSql, logParams);
            jdbcTemplate.update(sql, params);
        }
        catch(Exception e) {
        	e.printStackTrace();
        	throw new ManagementException("Failed to update member status with ID: " + member.getMemberId());
        }
        
    }


}
