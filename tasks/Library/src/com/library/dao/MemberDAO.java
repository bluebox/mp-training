package com.library.dao;

import com.library.domain.Member;
import com.library.util.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

	public boolean updateMember(Member member) {
	    String sql = "UPDATE members SET Name=?, Email=?, Mobile=?, Gender=?, Address=? WHERE MemberId=?";
	    try (Connection conn = DB.getConnection()) {
	       
	        if (member.getName() == null || member.getName().trim().isEmpty()) {
	            throw new IllegalArgumentException("Name cannot be empty.");
	        }

	        if (member.getEmail() == null || !member.getEmail().matches("^\\S+@\\S+\\.\\S+$")) {
	            throw new IllegalArgumentException("Invalid email address.");
	        }

	        if (String.valueOf(member.getMobile()).length() != 10) {
	            throw new IllegalArgumentException("Mobile number must be 10 digits.");
	        }

	        String gender = String.valueOf(member.getGender()).toUpperCase();
	        if (!gender.equals("M") && !gender.equals("F")) {
	            throw new IllegalArgumentException("Gender must be 'M' or 'F'.");
	        }

	        if (member.getAddress() == null || member.getAddress().trim().isEmpty()) {
	            throw new IllegalArgumentException("Address cannot be empty.");
	        }

	        if (member.getMemberId() <= 0) {
	            throw new IllegalArgumentException("Invalid member ID.");
	        }

	   
	        String fetchSQL = "SELECT * FROM members WHERE MemberId=?";
	        try (PreparedStatement fetchStmt = conn.prepareStatement(fetchSQL)) {
	            fetchStmt.setInt(1, member.getMemberId());
	            ResultSet rs = fetchStmt.executeQuery();

	            if (rs.next()) {

	                String insertLogSQL = "INSERT INTO members_log (MemberId, Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?, ?)";
	                try (PreparedStatement logStmt = conn.prepareStatement(insertLogSQL)) {
	                    logStmt.setInt(1, rs.getInt("MemberId"));
	                    logStmt.setString(2, rs.getString("Name"));
	                    logStmt.setString(3, rs.getString("Email"));
	                    logStmt.setLong(4, rs.getLong("Mobile"));
	                    logStmt.setString(5, rs.getString("Gender"));
	                    logStmt.setString(6, rs.getString("Address"));
	                    logStmt.executeUpdate();
	                }
	            } else {
	                throw new IllegalArgumentException("Member not found.");
	            }
	        }

	       
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, member.getName());
	            stmt.setString(2, member.getEmail());
	            stmt.setLong(3, member.getMobile());
	            stmt.setString(4, gender);
	            stmt.setString(5, member.getAddress());
	            stmt.setInt(6, member.getMemberId());
	            return stmt.executeUpdate() > 0;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static boolean exists(Connection conn, int memberId) throws SQLException {
		String query = "SELECT 1 FROM members WHERE MemberId = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, memberId);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		}
	}


	public List<Member> fetchAllmembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        try (Connection conn = DB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM members")) {
            while (rs.next()) {
				Member m = new Member(rs.getInt("memberId"),  rs.getString("name"),rs.getString("email"),
						rs.getLong("mobile"), rs.getString("gender").charAt(0), rs.getString("address"));
                members.add(m);
            }
        }
        return members;
    }

}