package com.SpringBoot_LMS.SpringBoot_LMS.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.SpringBoot_LMS.SpringBoot_LMS.model.Book;
import com.SpringBoot_LMS.SpringBoot_LMS.model.Member;

@Repository
public class MemberRepository implements MemberRepositoryInterface {

	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	
	 @Autowired
	private MemberRowMapperImplementation rowmapper;
	
	@Override
	public int addMember(Member member) throws Exception {
		String query = "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";
		int value=0;
		value=jdbcTemplate.update(query,member.getName(),member.getEmail(),member.getMobile(),member.getGender().getType(),member.getAddress());
		if(value>0) {
			return value;
		}
		return 0;
	}

	@Override
	public Member getMemberById(int id) throws Exception {
		 String query = "SELECT MemberId,Name, Email, Mobile, Gender, Address FROM members WHERE MemberId = ?";
		 Member book=jdbcTemplate.queryForObject(query,rowmapper,id);
			if(book == null) {
				return null;
			}
			return book;
	}

	@Override
	public Member updateMember(Member member) throws Exception {
		 String query = "UPDATE members SET Name = ?, Email = ?, Mobile = ?, Gender = ?, Address = ? WHERE MemberId = ?";
	     String logquery = "INSERT INTO memberslog (MemberId, Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?, ?)";

	     int logvalue=jdbcTemplate.update(logquery,member.getMemberId(),member.getName(),member.getEmail(),member.getMobile(),member.getGender().getType(),member.getAddress());
			int value=jdbcTemplate.update(query,member.getName(),member.getEmail(),member.getMobile(),member.getGender().getType(),member.getAddress(),member.getMemberId());
			if(logvalue>0 && value>0) {
				return getMemberById(member.getMemberId());
			}
			return null;
	}

	@Override
	public List<Member> getAllMembers() throws Exception {
		 String query = "SELECT MemberId,Name, Email, Mobile, Gender, Address FROM members"; 
		 List<Member> members=jdbcTemplate.query(query,rowmapper);
		  if(members == null) {
			  return null;
		  }
		  return members;
	
	}
                
}
