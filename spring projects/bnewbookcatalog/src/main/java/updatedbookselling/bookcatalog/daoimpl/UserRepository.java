package updatedbookselling.bookcatalog.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import updatedbookselling.bookcatalog.domain.Member;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Member findByUserIdAndPassword(String memberId, String password) {
        String sql = "SELECT memberId , name , roles , memberPassword FROM Member WHERE memberId = ? AND memberPassword = ?";

        try {
             return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Member.class) , memberId , password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public String getRoleByUserId(String memberId) {
        String sql = "SELECT roles FROM Member WHERE memberId = ?";

        try {
            return jdbcTemplate.queryForObject(sql,  String.class,memberId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

