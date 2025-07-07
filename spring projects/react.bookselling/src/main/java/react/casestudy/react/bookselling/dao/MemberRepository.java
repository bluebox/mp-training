package react.casestudy.react.bookselling.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import react.casestudy.react.bookselling.domain.Member;

@Repository
public class MemberRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addMember(Member member) {
        String sql = "INSERT INTO Member (name, email, mobile, age, gender, address) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, member.getName(), member.getEmail(), member.getMobile(),
                member.getAge(), String.valueOf(member.getGender()), member.getAddress());
    }

    public int updateMember(int id, Member member) {
        String sql = "UPDATE Member SET name=?, email=?, mobile=?, age=?, gender=?, address=? WHERE memberId=?";
        return jdbcTemplate.update(sql, member.getName(), member.getEmail(), member.getMobile(),
                member.getAge(), String.valueOf(member.getGender()), member.getAddress(), id);
    }

    public Member getMemberById(int id) {
        String sql = "SELECT memberId ,name, email, mobile, age, gender, address FROM Member WHERE memberId=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Member.class), id);
    }

    public List<Member> getAllMembers() {
        String sql = "SELECT memberId ,name, email, mobile, age, gender, address FROM Member";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class));
    }

    public List<Member> searchMembersByName(String name) {
        String sql = "SELECT memberId , name, email, mobile, age, gender, address FROM Member WHERE name LIKE ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class), "%" + name + "%");
    }

    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM Member WHERE memberId=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}

