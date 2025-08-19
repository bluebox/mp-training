package com.lms.LMS_Springboot.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lms.LMS_Springboot.Model.*;
import com.lms.LMS_Springboot.Model.checking_enum.Gender;


@Scope("prototype")
@Repository
public class MemberDAO {
	@Autowired
    private JdbcTemplate template;
	public boolean addMember(Member member) {
        String sql = "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";
        int added=template.update(sql,member.getName(),member.getEmail(),member.getMobile(),member.getGender().name().substring(0, 1),member.getAddress());
        if (added>0) {
        	return true;
        }
        return false;
	}
	public boolean updateMember(Member member, int memberid) throws SQLException {
        String logSql = "INSERT INTO members_log (MemberId, Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?, ?)";

        String updateSql = "UPDATE members SET Name = ?, Email = ?, Mobile = ?, Gender = ?, Address = ? WHERE Memberid = ?";
        
        int logs=template.update(logSql,memberid,member.getName(),member.getEmail(),member.getMobile(),member.getGender().name().substring(0, 1),member.getAddress());
        if(logs==0) {
        	return false;
        }
        int updates=template.update(updateSql,member.getName(),member.getEmail(),member.getMobile(),member.getGender().name().substring(0, 1),member.getAddress(),memberid);
        if(updates==0) {
        	return false;
        }
        return true;

}
	public List<Member> getAllMembers() {
        String sql = "SELECT Memberid, Name, Email, Mobile, Gender, Address FROM members";
        List<Member> members =template.query(sql, new MemberRowMapper());

        return members;
        

}
	public static final class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setMemberid(rs.getInt("memberid"));
            member.setName(rs.getString("name"));
            member.setEmail(rs.getString("email"));
            member.setMobile(rs.getString("mobile"));
            member.setGender(Gender.getstatus(rs.getString("gender")));
            member.setAddress(rs.getString("address"));
            return member;
        }
    }
	public Member getById(int memberId) {
        String sql = "SELECT Name, Email, Mobile, Gender, Address FROM members WHERE Memberid = ?";
        Member member=template.queryForObject(sql, new MemberRowMapperbyid(),memberId );
        return member;
        
}
	public static final class MemberRowMapperbyid implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setName(rs.getString("name"));
            member.setEmail(rs.getString("email"));
            member.setMobile(rs.getString("mobile"));
            member.setGender(Gender.getstatus(rs.getString("gender")));
            member.setAddress(rs.getString("address"));
            return member;
        }
    }
}
	
