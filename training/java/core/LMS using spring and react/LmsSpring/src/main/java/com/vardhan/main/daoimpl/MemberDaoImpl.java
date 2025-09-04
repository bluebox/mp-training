package com.vardhan.main.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.vardhan.main.dao.MemberDao;
import com.vardhan.main.model.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<Member> MEMBER_ROW_MAPPER = new RowMapper<Member>() {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Member.builder()
                    .memberId(rs.getInt("member_id"))
                    .name(rs.getString("name"))
                    .email(rs.getString("email"))
                    .mobile(rs.getString("mobile"))
                    .gender(rs.getString("gender"))
                    .address(rs.getString("address"))
                    .status(rs.getString("status"))
                    .build();
        }
    };

    @Override
    public Member save(Member member) throws DataAccessException {
        String sql = "INSERT INTO members (name, email, mobile, gender, address, status) VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setString(3, member.getMobile());
            ps.setString(4, member.getGender());
            ps.setString(5, member.getAddress());
            ps.setString(6, member.getStatus() != null ? member.getStatus() : "ACTIVE");
            return ps;
        }, keyHolder);

        member.setMemberId(keyHolder.getKey().intValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Integer memberId) throws DataAccessException {
        String sql = "SELECT member_id, name, email, mobile, gender, address, status FROM members WHERE member_id = ? AND status != 'DELETED'";
        try {
            Member member = jdbcTemplate.queryForObject(sql, MEMBER_ROW_MAPPER, memberId);
            return Optional.ofNullable(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Member> findByIdIncludingDeleted(Integer memberId) throws DataAccessException {
        String sql = "SELECT member_id, name, email, mobile, gender, address, status FROM members WHERE member_id = ?";
        try {
            Member member = jdbcTemplate.queryForObject(sql, MEMBER_ROW_MAPPER, memberId);
            return Optional.ofNullable(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Member> findByMobile(String mobile) throws DataAccessException {
        String sql = "SELECT member_id, name, email, mobile, gender, address, status FROM members WHERE mobile = ? AND status != 'DELETED'";
        try {
            Member member = jdbcTemplate.queryForObject(sql, MEMBER_ROW_MAPPER, mobile);
            return Optional.ofNullable(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Member> findByEmail(String email) throws DataAccessException {
        String sql = "SELECT member_id, name, email, mobile, gender, address, status FROM members WHERE email = ? AND status != 'DELETED'";
        try {
            Member member = jdbcTemplate.queryForObject(sql, MEMBER_ROW_MAPPER, email);
            return Optional.ofNullable(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Member> findAll() throws DataAccessException {
        String sql = "SELECT member_id, name, email, mobile, gender, address, status FROM members WHERE status != 'DELETED' ORDER BY name";
        return jdbcTemplate.query(sql, MEMBER_ROW_MAPPER);
    }

    @Override
    public List<Member> findAllActive() throws DataAccessException {
        String sql = "SELECT member_id, name, email, mobile, gender, address, status FROM members WHERE status = 'ACTIVE' ORDER BY name";
        return jdbcTemplate.query(sql, MEMBER_ROW_MAPPER);
    }

    @Override
    public List<Member> findAllIncludingDeleted() throws DataAccessException {
        String sql = "SELECT member_id, name, email, mobile, gender, address, status FROM members ORDER BY name";
        return jdbcTemplate.query(sql, MEMBER_ROW_MAPPER);
    }

    @Override
    public Member update(Member member) throws DataAccessException {
        String sql = "UPDATE members SET name=?, email=?, mobile=?, gender=?, address=?, status=? WHERE member_id=?";
        int rowsAffected = jdbcTemplate.update(sql,
                member.getName(),
                member.getEmail(),
                member.getMobile(),
                member.getGender(),
                member.getAddress(),
                member.getStatus(),
                member.getMemberId());

        if (rowsAffected > 0) {
            return member;
        }
        throw new DataAccessException("Failed to update member with ID: " + member.getMemberId()) {};
    }

    @Override
    public boolean deleteById(Integer memberId) throws DataAccessException {
        String sql = "DELETE FROM members WHERE member_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, memberId);
        return rowsAffected > 0;
    }

    @Override
    public boolean softDeleteById(Integer memberId) throws DataAccessException {
        String sql = "UPDATE members SET status = 'DELETED' WHERE member_id = ? AND status != 'DELETED'";
        int rowsAffected = jdbcTemplate.update(sql, memberId);
        return rowsAffected > 0;
    }

    @Override
    public boolean reactivateById(Integer memberId) throws DataAccessException {
        String sql = "UPDATE members SET status = 'ACTIVE' WHERE member_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, memberId);
        return rowsAffected > 0;
    }

    @Override
    public boolean existsByMobile(String mobile) throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM members WHERE mobile = ? AND status != 'DELETED'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, mobile);
        return count != null && count > 0;
    }

    @Override
    public boolean existsByEmail(String email) throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM members WHERE email = ? AND status != 'DELETED'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public boolean existsByMobileActive(String mobile) throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM members WHERE mobile = ? AND status = 'ACTIVE'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, mobile);
        return count != null && count > 0;
    }

    @Override
    public boolean existsByEmailActive(String email) throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM members WHERE email = ? AND status = 'ACTIVE'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public long count() throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM members WHERE status != 'DELETED'";
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        return count != null ? count : 0;
    }

    @Override
    public long countActive() throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM members WHERE status = 'ACTIVE'";
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        return count != null ? count : 0;
    }
}
