package com.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.dao.MemberDAO;
import com.library.dao.queries.MemberSQLQueries;
import com.library.domain.Member;

public class MemberDAOImpl extends MemberSQLQueries implements MemberDAO{


	public boolean addMember(Member member,Connection conn) {
		int check = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(insertMember);
			ps.setString(1, member.getName());
			ps.setString(2, member.getEmail());
			ps.setLong(3, member.getMobile());
			ps.setString(4, String.valueOf(member.getGender()));
			ps.setString(5, member.getAddress());
			check = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check == 1;
	}


	public boolean isMemberExists(int memberId,Connection conn) {
    	try(PreparedStatement ps =conn.prepareStatement(isMemberExists);){
    		ps.setInt(1, memberId);
    		ResultSet rs = ps.executeQuery();
    		return rs.next();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
	
	public List<Member> getAllMembers(Connection conn) {
		
	    List<Member> members = new ArrayList<>();
	    
	    try {	
	    	PreparedStatement ps = conn.prepareStatement(viewAllMembers);
	         ResultSet rs = ps.executeQuery();
	        		 
	        while (rs.next()) {
	            Member member = new Member(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getString("email"),
	                rs.getLong("mobile"),
	                rs.getString("gender").charAt(0),
	                rs.getString("address")
	            );
	            members.add(member);
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		}

	    return members;
	}
}
