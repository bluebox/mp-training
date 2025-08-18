package repository;

import model.Member;
import model.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MemberRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    private RowMapper<Member> memberRowMapper = new RowMapper<>() {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setId(rs.getInt("MemberId"));
            member.setName(rs.getString("Name"));
            member.setEmail(rs.getString("Email"));
            member.setMobile(rs.getString("Mobile"));
            member.setGender(Gender.getGender(rs.getString("Gender")));
            member.setAddress(rs.getString("Address"));
            return member;
        }
    };

    // ADD MEMBER
    public int addMember(Member member) {
        String sql = "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                member.getName(),
                member.getEmail(),
                member.getMobile(),
                member.getGender().getType(),
                member.getAddress()
        );
    }

    //  Update Member 
    @Transactional
    public Member updateMember(Member member) throws Exception {
        String updateSql = "UPDATE members SET Name = ?, Email = ?, Mobile = ?, Gender = ?, Address = ? WHERE MemberId = ?";
        String logSql = "INSERT INTO members_log (MemberId, Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?, ?)";

        int logCount = jdbcTemplate.update(logSql,
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getMobile(),
                member.getGender().name().substring(0, 1),
                member.getAddress()
        );

        int updateCount = jdbcTemplate.update(updateSql,
                member.getName(),
                member.getEmail(),
                member.getMobile(),
                member.getGender().name().substring(0, 1),
                member.getAddress(),
                member.getId()
        );

        if (logCount > 0 && updateCount > 0) {
            return member;
        } else {
            throw new Exception("Failed to update member and log changes.");
        }
    }

    // Get All Members
    public List<Member> getAllMembers() {
        String sql = "SELECT MemberId, Name, Email, Mobile, Gender, Address FROM members";
        return jdbcTemplate.query(sql, memberRowMapper);
    }

    // Get Member By ID
    public Member getMemberById(int memberId) {
        String sql = "SELECT MemberId, Name, Email, Mobile, Gender, Address FROM members WHERE MemberId = ?";
        return jdbcTemplate.queryForObject(sql, memberRowMapper, memberId);
    }
}
