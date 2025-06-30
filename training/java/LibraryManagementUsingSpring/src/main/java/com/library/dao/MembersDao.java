package com.library.dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.library.domain.Gender;
import com.library.domain.Member;

@Repository
public class MembersDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Insert a new member
    public boolean addMember(Member member) {
        String sql = "INSERT INTO Member (name,email,mobile,gender,address) VALUES (?, ?, ?, ?, ?)";
        int rows = jdbcTemplate.update(sql,
                member.getName(),
                member.getEmail(),
                member.getMobile(),
                member.getGender().getCode(),  // 'M' or 'F'
                member.getAddress());
        return rows > 0;
    }

    // Update existing member with transaction and logging
    public boolean updateMember(Member member) {
        // Backup old data to member_log first
        String insertLogSql = "INSERT INTO member_log (memberId, name, email, mobile, gender, address) " +
                              "SELECT memberId, name, email, mobile, gender, address FROM Member WHERE memberId = ?";
        jdbcTemplate.update(insertLogSql, member.getMemberId());

        // Update member
        String updateSql = "UPDATE Member SET name = ?, email = ?, mobile = ?, gender = ?, address = ? WHERE memberId = ?";
        int rows = jdbcTemplate.update(updateSql,
                member.getName(),
                member.getEmail(),
                member.getMobile(),
                member.getGender().getCode(),
                member.getAddress(),
                member.getMemberId());
        return rows > 0;
    }

    // Fetch all members
    public List<Member> getAllMembers() {
        String sql = "SELECT memberId, name, email, mobile, gender, address FROM Member";
        return jdbcTemplate.query(sql, new MemberRowMapper());
    }

    // Fetch single member by ID
    public Member getMemberById(int id) {
    	try {
    		 String sql = "SELECT memberId, name, email, mobile, gender, address FROM Member WHERE memberId = ?";
    	     return jdbcTemplate.queryForObject(sql, new MemberRowMapper(), id);
    	}catch(Exception e) {
    		return null;
    	}
       
    }

    // Check if member exists by ID
    public boolean existsById(int memberId) {
        String sql = "SELECT COUNT(*) FROM Member WHERE memberId = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, memberId);
        return count != null && count > 0;
    }

    // RowMapper implementation converting ResultSet to Member with Gender enum
    private static class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Member(
                    rs.getInt("memberId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getLong("mobile"),
                    Gender.fromCode(rs.getString("gender")),
                    rs.getString("address")
            );
        }
    }
}
