package dev.tulasidhar.lms.DAO.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dev.tulasidhar.lms.model.Member;
import lombok.extern.slf4j.Slf4j;
import dev.tulasidhar.lms.DAO.MemberDao;
import dev.tulasidhar.lms.DAO.mappers.MemberRowMapper;


@Repository
@Slf4j
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public MemberDaoImpl(){
		log.info("Member Dao instantiated and template is there : " + jdbcTemplate);
	}

    @Override
    public int insertMember(Member newMember) {
        String query = "INSERT INTO members(Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?);";
        
        
        int rowsAffected = jdbcTemplate.update(query,
        					newMember.getMember_Name(),
        					newMember.getEmail(),
        					newMember.getMobile_No(),
        					String.valueOf(newMember.getGender()),
        					newMember.getAddress());
        
        
        return rowsAffected;
    }

    @Override
    public List<Member> fetchAllMembers() {
    	log.info("Hello user" + jdbcTemplate.toString());
        String query = "SELECT MemberId,Name,Email,Mobile,Gender,Address FROM members;";
        
        List<Member> members = jdbcTemplate.query(query,
				new MemberRowMapper());
        
        return members;
    }
    
    @Override
    public int updateMember(Member member) {
        String query = "UPDATE members SET Name=?, Email=?, Mobile=?, Gender=?, Address=? WHERE MemberId=?";
        int rowsAffected = jdbcTemplate.update(query,
        										member.getMember_Name(),
        										member.getEmail(),
        										member.getMobile_No(),
        										String.valueOf(member.getGender()),
        										member.getAddress(),
        										member.getMember_Id());
        return rowsAffected;
    }
    
    
    
	@Override
	public Member getMemberById(int memberId) {
		String query="SELECT MemberId,Name,Email,Mobile,Gender,Address FROM members WHERE MemberId=?";
		Member member = jdbcTemplate.queryForObject(query, new Object[]{memberId} ,new MemberRowMapper());
		
		return member;
	}

	@Override
	public void addMemberLogs(Member member) {
		
	}
}
