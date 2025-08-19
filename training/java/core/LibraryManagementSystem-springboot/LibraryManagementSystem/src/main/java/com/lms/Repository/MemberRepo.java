package com.lms.Repository;





import com.lms.Exceptions.MemberDaoException;
import com.lms.Models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class MemberRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int registerMember(Member member) {
        String insert = "INSERT INTO member(name, email, mobile, gender, address) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            int rowsAffected = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, member.getName());
                ps.setString(2, member.getEmail());

                if (member.getMobile() == null) {
                    ps.setNull(3, Types.BIGINT);
                } else {
                    ps.setLong(3, member.getMobile());
                }

                ps.setString(4, member.getGender());
                ps.setString(5, member.getAddress());
                return ps;
            }, keyHolder);

//           
//            if (rowsAffected == 0) {
//                throw new MemberDaoException("Insert failed: no rows affected");
//            }
//
//            
            Number key = keyHolder.getKey();
//            if (key == null) {
//                throw new MemberDaoException("Insert succeeded but no key was returned.");
//            }

            return key.intValue();

        } catch (Exception e) {
            e.printStackTrace(); 
            throw new MemberDaoException("Failed to register new member", e);
        }
    }


    public void updateMember(Member member) {
        String updateMember = "UPDATE member SET name = ?, email = ?, mobile = ?, gender = ?, address = ? WHERE memberId = ?";
        String updateMemberLog = "INSERT INTO member_log(memberId, name, email, mobile, gender, address, operation_type) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
           
            jdbcTemplate.update(updateMemberLog,
                    member.getMemberId(), member.getName(), member.getEmail(),
                    member.getMobile(), member.getGender(), member.getAddress(), "Update");

        
            jdbcTemplate.update(updateMember,
                    member.getName(), member.getEmail(), member.getMobile(),
                    member.getGender(), member.getAddress(), member.getMemberId());

        } catch (Exception e) {
            throw new MemberDaoException("Failed to update member", e);
        }
    }

    public List<Member> getAllMembers() {
        String query = "SELECT * FROM member";
        try {
            return jdbcTemplate.query(query, (rs, rowNum) -> mapResultToMember(rs));
        } catch (Exception e) {
            throw new MemberDaoException("Failed to fetch members", e);
        }
    }

    public Member getMemberById(int id) {
        String query = "SELECT * FROM member WHERE memberId = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> mapResultToMember(rs));
        } catch (Exception e) {
            throw new MemberDaoException("Error fetching member by ID: " + e.getMessage(), e);
        }
    }

    private Member mapResultToMember(ResultSet rs) throws SQLException {
        return new Member(
                rs.getInt("memberId"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getLong("mobile"),
                rs.getString("gender"),
                rs.getString("address")
        );
    }
}
