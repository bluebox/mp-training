package com.lms.springbootlms.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lms.springbootlms.dao.MemberDao;
import com.lms.springbootlms.exception.DaoException;
import com.lms.springbootlms.model.Member;

import java.util.List;

@Repository
public class MemberDaoImpl implements MemberDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean addMember(Member member) throws DaoException {
        String sql = "INSERT INTO members (name, email, mobile, gender, address) VALUES (?, ?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql,
                member.getName(),
                member.getEmail(),
                member.getMobile(),
                member.getGender(),
                member.getAddress()) > 0;
        } catch (Exception e) {
            throw new DaoException("Error inserting member: " + e.getMessage(), e);
        }

    }

    @Override
    public boolean updateMember(Member member) throws DaoException {
        String sql = "UPDATE members SET name=?, email=?, mobile=?, gender=?, address=? WHERE member_id=?";
        return jdbcTemplate.update(sql,
                member.getName(),
                member.getEmail(),
                member.getMobile(),
                member.getGender(),
                member.getAddress(),
                member.getMemberId()) > 0;
    }

    @Override
    public Member getMemberById(int id) throws DaoException {
        String sql = "SELECT member_id, name, email, mobile, gender, address FROM members WHERE member_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Member.class), id);
    }

    @Override
    public List<Member> getAllMembers() throws DaoException {
        String sql = "SELECT member_id, name, email, mobile, gender, address FROM members";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class));
    }

    @Override
    public int generateNewMemberId() throws DaoException {
        String sql = "SELECT MAX(member_id) AS max_id FROM members";
        Integer maxId = jdbcTemplate.queryForObject(sql, Integer.class);
        return (maxId != null ? maxId + 1 : 1);
    }
    @Override
    public Member getMemberByEmail(String email) throws DaoException {
        String sql = "SELECT member_id, name, email, mobile, gender, address FROM members WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Member.class), email);
        } catch (EmptyResultDataAccessException e) {
            return null; 
        } catch (Exception e) {
            throw new DaoException("Error fetching member by email: " + e.getMessage(), e);
        }
    }

    @Override
    public Member getMemberByMobile(String mobile) throws DaoException {
        String sql = "SELECT member_id, name, email, mobile, gender, address FROM members WHERE mobile = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Member.class), mobile);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            throw new DaoException("Error fetching member by mobile: " + e.getMessage(), e);
        }
    }

}
