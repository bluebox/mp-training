package com.example.lms.repository;

import com.example.lms.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final JdbcTemplate jdbc;

    public int save(Member member) {
        return jdbc.update(
            "INSERT INTO members (name, email, mobile, gender, address) VALUES (?,?,?,?,?)",
            member.getName(),
            member.getEmail(),
            member.getMobile(),
            member.getGender(),
            member.getAddress()
        );
    }

    public int update(Member member) {
        return jdbc.update(
            "UPDATE members SET name=?, email=?, mobile=?, gender=?, address=? WHERE member_id=?",
            member.getName(),
            member.getEmail(),
            member.getMobile(),
            member.getGender(),
            member.getAddress(),
            member.getMemberId()
        );
    }

    public int deleteById(Long id) {
        return jdbc.update("DELETE FROM members WHERE member_id=?", id);
    }

    public Member findById(Long id) {
        List<Member> result = jdbc.query(
            "SELECT * FROM members WHERE member_id=?",
            (rs, rowNum) -> new Member(
                rs.getLong("member_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("mobile"),
                rs.getString("gender"),
                rs.getString("address")
            ),
            id
        );
        return result.isEmpty() ? null : result.get(0);
    }

    public List<Member> findAll() {
        return jdbc.query(
            "SELECT * FROM members",
            (rs, rowNum) -> new Member(
                rs.getLong("member_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("mobile"),
                rs.getString("gender"),
                rs.getString("address")
            )
        );
    }
}
