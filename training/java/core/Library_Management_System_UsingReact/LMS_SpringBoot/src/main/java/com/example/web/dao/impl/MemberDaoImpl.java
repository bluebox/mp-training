package com.example.web.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.web.annotations.ValidateMembers;
import com.example.web.dao.MemberDao;
import com.example.web.exceptions.DBConstrainsException;
import com.example.web.model.Member;

@Repository
public class MemberDaoImpl implements MemberDao{
	private JdbcTemplate template;
	private RowMapper<Member> mapper;
	
	public JdbcTemplate getTemplate() {
		return template;
	}
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	@Override
	@ValidateMembers
	public int insertMember(Member newMember) {
		String query = "INSERT INTO members(Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";
		int rowsAffected = template.update(query,newMember.getMember_Name(),newMember.getEmail(),newMember.getMobile_No(),newMember.getGender(),newMember.getAddress());
		try {
			if(rowsAffected==0) {
				throw new SQLException("SQL ERROR: NOTHING INSERTED");
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rowsAffected;
	}

	@Override
	public List<Member> fetchAllMembers() {
		List<Member> members;
		String query = "SELECT MemberId,Name,Email,Mobile,Gender,Address FROM members";
		mapper = (rs, rowNum) -> {
			Member member = new Member(
					rs.getInt("MemberId"),
					rs.getString("Name"),
					rs.getString("Email"),
					rs.getString("Mobile"),
					rs.getString("Gender"),
					rs.getString("Address")
					);
			return member;
		};
		members = template.query(query, mapper);
		try {
			if(members == null) {
				throw new SQLException("Error In Fetching Members");
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return members;
	}

	@Override
	@ValidateMembers
	public int updateMember(Member member) {
		String query = "UPDATE members SET Name=?, Email=?, Mobile=?, Gender=?, Address=? WHERE MemberId=?";
		int rowsAffected = template.update(query
				,member.getMember_Name()
				,member.getEmail()
				,member.getMobile_No()
				,String.valueOf(member.getGender())
				,member.getAddress()
				,member.getMember_Id()
				);
		try {
			if (rowsAffected == 0) {
                throw new SQLException("Failed to update member: Member not found");
            }
            addMemberLogs(member);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
	    }
		return rowsAffected;
	}

	@Override
	@ValidateMembers
	public void addMemberLogs(Member member) {
		int id=member.getMember_Id();
		try {
			if(getMemberById(id)==null) {
				String query = "INSERT INTO members_log(MemberId,Name,Email,Mobile,Gender,Address) VALUES (?,?,?,?,?,?)";
				int rowsAffected = template.update(query,member.getMember_Id(),member.getMember_Name(),member.getEmail(),member.getMobile_No(),String.valueOf(member.getGender()),member.getAddress());
				if (rowsAffected == 0) {
					throw new SQLException("Add member log failed, no rows affected.");
				}
			}
			else {
        		throw new DBConstrainsException("Member Id already exists" +id);
        	}
		}
		catch (SQLException |DBConstrainsException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Member getMemberById(int memberId) {
		Member resultedMember=null;
		String query="SELECT MemberId,Name,Email,Mobile,Gender,Address FROM members WHERE MemberId=?";
		mapper = (rs, rowNum) -> {
			Member member = new Member(
					rs.getInt("MemberId"),
					rs.getString("Name"),
					rs.getString("Email"),
					rs.getString("Mobile"),
					rs.getString("Gender"),
					rs.getString("Address")
					);
			return member;
		};
		resultedMember = template.queryForObject(query, mapper,memberId);
		try {
			 if (resultedMember == null) {
				 throw new SQLException("Member Not Exists");
		     }
	    }
		catch (SQLException e) {
	    	 System.out.println(e.getMessage());
	    }
		return resultedMember;
	}

}
