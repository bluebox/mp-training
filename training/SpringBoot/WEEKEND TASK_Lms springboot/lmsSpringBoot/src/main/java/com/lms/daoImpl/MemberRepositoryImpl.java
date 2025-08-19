package com.lms.daoImpl;

import com.lms.exceptions.DAOException;
import com.lms.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public boolean addMember(Member member) throws DAOException {
        String sql = "INSERT INTO members (name, email, mobile, gender, address) " +
                     "VALUES (:name, :email, :mobile, :gender, :address)";

        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("name", member.getName());
            params.addValue("email", member.getEmail());
            params.addValue("mobile", member.getMobile());
            params.addValue("gender", member.getGender());
            params.addValue("address", member.getAddress());

            int rows = namedParameterJdbcTemplate.update(sql, params);
            return rows > 0;

        } catch (Exception e) {
          //  e.printStackTrace();
            throw new DAOException("Error adding member: " + e.getMessage(), e);
        }
    }

    public boolean updateMember(Member member) throws DAOException {
        String sql = "UPDATE members SET name=?, email=?, mobile=?, gender=?, address=? WHERE member_id=?";
        try {
            return jdbcTemplate.update(sql,
                    member.getName(),
                    member.getEmail(),
                    member.getMobile(),
                    member.getGender(),
                    member.getAddress(),
                    member.getMemberId()) > 0;
        } catch (Exception e) {
           // e.printStackTrace();
            throw new DAOException("Error updating member: " + e.getMessage(), e);
        }
    }

    public List<Member> getAllMembers() throws DAOException {
        String sql = "SELECT member_id, name, email, mobile, gender, address FROM members";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class));
        } catch (Exception e) {
          //  e.printStackTrace();
            throw new DAOException("Error fetching members: " + e.getMessage(), e);
        }
    }

    public Member getMemberById(int id) throws DAOException {
        String sql = "SELECT member_id, name, email, mobile, gender, address FROM members WHERE member_id = ?";

        try {
            List<Member> members = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class), id);
            return members.isEmpty() ? null : members.get(0);
        } catch (Exception e) {
           // e.printStackTrace();
            throw new DAOException("Error fetching member by ID: " + e.getMessage(), e);
        }
    }


    public Member getMemberByEmail(String email) throws DAOException {
        String sql = "SELECT member_id, name, email, mobile, gender, address FROM members WHERE email = ?";
        try {
            List<Member> members = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class), email);
            return members.isEmpty() ? null : members.get(0);
        } catch (Exception e) {
           // e.printStackTrace();
            throw new DAOException("Error fetching member by email: " + e.getMessage(), e);
        }
    }

    public Member getMemberByMobile(String mobile) throws DAOException {
        String sql = "SELECT member_id, name, email, mobile, gender, address FROM members WHERE mobile = ?";
        try {
            List<Member> members = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class), mobile);
            return members.isEmpty() ? null : members.get(0);
        } catch (Exception e) {
          //  e.printStackTrace();
            throw new DAOException("Error fetching member by mobile: " + e.getMessage(), e);
        }
    }
        
    
}
