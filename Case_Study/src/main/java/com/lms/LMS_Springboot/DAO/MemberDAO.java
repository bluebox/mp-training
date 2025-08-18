package com.lms.LMS_Springboot.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.lms.LMS_Springboot.Model.Book;
import com.lms.LMS_Springboot.Model.Member;
import com.lms.LMS_Springboot.Model.checking_enum.Availability;
import com.lms.LMS_Springboot.Model.checking_enum.Gender;
import com.lms.LMS_Springboot.Model.checking_enum.Status;
@Component
@Repository
public class MemberDAO {
	@Autowired
	MemberRowMapper memberrowmapper;
	
	@Autowired
	Member member;
	@Autowired 
	private JdbcTemplate jdbctemplate;
	public int addMember(Member member) {
        String sql = "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";
        int res=jdbctemplate.update(sql, member.getName(),member.getEmail(),member.getMobile(),member.getGender().getType(),member.getAddress());
		return res;
	}
	public int updateMember(Member member) {
		System.out.println(member);
        String sql = "UPDATE members SET Name = ?, Email = ?, Mobile = ?, Gender = ?, Address = ? WHERE Memberid = ?";
        
        String sqllog="INSERT INTO members_log (Memberid,Name, Email, Mobile, Gender, Address) VALUES (?,?, ?, ?, ?, ?)";
//        int memberid=getmemberidwithmember(member.getMobile());
        Member oldmember=getById(member.getMemberid());
        jdbctemplate.update(sqllog,oldmember.getMemberid(), oldmember.getName(),oldmember.getEmail(),oldmember.getMobile(),oldmember.getGender().getType(),oldmember.getAddress());
        int res=jdbctemplate.update(sql,member.getName(),member.getEmail(),member.getMobile(),member.getGender().getType(),member.getAddress(),member.getMemberid());
        return res;
		
	}
	public List<Member> viewAllMembers(){
        String sql = "SELECT * FROM library_management_system.members;";
	    return jdbctemplate.query(sql,memberrowmapper);
	}
    public Member getById(int memberId) {
        String sql = "SELECT Memberid, Name, Email, Mobile, Gender, Address FROM members WHERE Memberid = ?";
		Member member=jdbctemplate.queryForObject(sql,memberrowmapper ,memberId);
		return member;
    	
    }
    public List<Member> viewjoinMembers(){
      	 String query="SELECT issue_records.status,members.memberid,members.name,members.email,members.mobile,members.gender,members.address   FROM issue_records INNER JOIN members ON members.memberid = issue_records.memberid where issue_records.status='I'";
 	    return jdbctemplate.query(query,memberrowmapper);
	    
    }
     
   

}








@Component
@Scope("prototype")
class MemberRowMapper implements RowMapper<Member> {
	

	@Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Member member=new Member();
    	
        member.setMemberid(rs.getInt("memberid"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("Email"));
        member.setMobile(rs.getLong("mobile"));
        member.setGender(Gender.getstatus(rs.getString("gender")));
        member.setAddress((rs.getString("address")));
        return member;
    	
}
}
