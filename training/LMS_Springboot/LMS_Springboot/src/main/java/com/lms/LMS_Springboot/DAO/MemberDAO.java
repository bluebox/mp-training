package com.lms.LMS_Springboot.DAO;

import com.lms.LMS_Springboot.Model.Member;
import com.lms.LMS_Springboot.Model.checking_enum.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Member> getAllMembers() {
        return jdbcTemplate.query("SELECT memberId,name,email,mobile,address,gender FROM members",
                (rs, rowNum) -> new Member(
                        rs.getInt("memberId"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("mobile"), // changed from getLong to getString
                        rs.getString("address"),
                        Gender.getstatus(rs.getString("gender"))
                ));
    }

    public Member getMemberById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT memberId,name,email,mobile,address,gender FROM members WHERE memberId=?",
                    new Object[]{id},
                    (rs, rowNum) -> new Member(
                            rs.getInt("memberId"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("mobile"), // changed from getLong to getString
                            rs.getString("address"),
                            Gender.getstatus(rs.getString("gender"))
                    ));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int addMember(Member member) {
        return jdbcTemplate.update(
                "INSERT INTO members (name, email, mobile, address, gender) VALUES (?,?,?,?,?)",
                member.getName(),
                member.getEmail(),
                member.getMobile(), // now String
                member.getAddress(),
                member.getGender().getType()
        );
    }

    public int updateMember(int id, Member member) {
        return jdbcTemplate.update(
                "UPDATE members SET name=?, email=?, mobile=?, address=?, gender=? WHERE memberId=?",
                member.getName(),
                member.getEmail(),
                member.getMobile(), // now String
                member.getAddress(),
                member.getGender().getType(),
                id
        );
    }

  
}