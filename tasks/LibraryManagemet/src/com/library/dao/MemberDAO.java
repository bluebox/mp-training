package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.domain.Member;
import com.library.utilities.ConnectionMaker;

public class MemberDAO {

	public void addMember(Member member) {
		try (Connection conn = ConnectionMaker.getConnection();) {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO member(name,email,mobile,gender,address) VALUES(?,?,?,?,?)");
			ps.setString(1, member.getName());
			ps.setString(2, member.getEmail());
			ps.setLong(3, member.getMobile());
			ps.setString(4, String.valueOf(member.getGender()));
			ps.setString(5, member.getAddress());
			System.out.println(ps.executeUpdate());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateMember(Member member) {
		String sql = "UPDATE member SET name=?, email=?, mobile=?, gender=?, address=? WHERE id=?";
		try (Connection conn = ConnectionMaker.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getEmail());
			stmt.setLong(3, member.getMobile());
			stmt.setString(4, String.valueOf(member.getGender()));
			stmt.setString(5, member.getAddress());
			stmt.setInt(6, member.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

public List<Member> getAllMembers() {
		
	    List<Member> members = new ArrayList<>();
	    String query = "SELECT * FROM member";
	    
	    try (Connection conn = ConnectionMaker.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);){
	    	
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