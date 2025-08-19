package com.lms.lms_backend.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lms.lms_backend.constant.MemberGender;
import com.lms.lms_backend.constant.MemberStatus;
import com.lms.lms_backend.dao.MemberDao;
import com.lms.lms_backend.model.Member;

@Repository
public class MemberDaoImplementation implements MemberDao {

    private final JdbcTemplate jdbcTemplate;

    public MemberDaoImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertMemberLog(Member member) {
        String sql = "INSERT INTO members_log(member_id, name, email, mobile, gender, address, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                member.getMemberId(),
                member.getName(),
                member.getEmail(),
                member.getMobile(),
                member.getGender().getDbName(),
                member.getAddress(),
                member.getStatus().getDbName());
    }

    @Override
    public int addMember(Member member) {
        String sql = "INSERT INTO members(name, email, mobile, gender, address, status) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                member.getName(),
                member.getEmail(),
                member.getMobile(),
                member.getGender().getDbName(),
                member.getAddress(),
                MemberStatus.ACTIVE.getDbName());
    }

    @Override
    public int updateMemberDetails(Member oldMember, Member newMember) {
        String sql = "UPDATE members SET name=?, email=?, mobile=?, gender=?, address=? WHERE member_id=? AND status='A'";
        return jdbcTemplate.update(sql,
                newMember.getName(),
                newMember.getEmail(),
                newMember.getMobile(),
                newMember.getGender().getDbName(),
                newMember.getAddress(),
                oldMember.getMemberId());
    }

    @Override
    public int deleteMember(Member member) {
        String sql = "UPDATE members SET status=? WHERE member_id=? AND status='A'";
        return jdbcTemplate.update(sql,
                MemberStatus.INACTIVE.getDbName(),
                member.getMemberId());
    }

    @Override
    public List<Member> selectAllMembers() {
        String sql = "SELECT * FROM members WHERE status='A'";
        return jdbcTemplate.query(sql, new MemberRowMapper());
    }

    @Override
    public Member selectMemberById(int id) {
        String sql = "SELECT * FROM members WHERE member_id=? AND status='A'";
        return jdbcTemplate.query(sql, new MemberRowMapper(), id)
                .stream()
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public List<String> selectAllGenders() {
        return Stream.of(MemberGender.values()).map(e -> e.getDisplayName()).collect(Collectors.toList());
    }

    private static class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Member(
                rs.getInt("member_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getLong("mobile"),
                MemberGender.fromDbName(rs.getString("gender")),
                rs.getString("address"),
                MemberStatus.fromDbName(rs.getString("status"))
            );
        }
    }
}
