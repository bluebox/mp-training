package dao;


import model.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class MemberDAO {

	public void updateMember(Member member) throws Exception {
	    String update = "UPDATE members SET Name=?, Email=?, Mobile=?, Gender=?, Address=? WHERE MemberId=?";
	    String insertLog = "INSERT INTO members_log (MemberId, Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection conn = JDBCConnection.getConnection()) {
	        conn.setAutoCommit(false);

	        // Update member
	        PreparedStatement stmt = conn.prepareStatement(update);
	        stmt.setString(1, member.getName());
	        stmt.setString(2, member.getEmail());
	        stmt.setLong(3, member.getMobile());
	        stmt.setString(4, String.valueOf(member.getGender()));
	        stmt.setString(5, member.getAddress());
	        stmt.setInt(6, member.getMemberId());
	        stmt.executeUpdate();

	        // Log member update
	        PreparedStatement logStmt = conn.prepareStatement(insertLog);
	        logStmt.setInt(1, member.getMemberId());
	        logStmt.setString(2, member.getName());
	        logStmt.setString(3, member.getEmail());
	        logStmt.setLong(4, member.getMobile());
	        logStmt.setString(5, String.valueOf(member.getGender()));
	        logStmt.setString(6, member.getAddress());
	        logStmt.executeUpdate();

	        conn.commit();
	    }
	}

	public List<Member> getAllMembers() throws Exception {
		List<Member> members = new ArrayList<>();
		String query = "SELECT * FROM members";
		try (Connection conn = JDBCConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(query);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				members.add(new Member(
					rs.getInt("MemberId"),
					rs.getString("Name"),
					rs.getString("Email"),
					rs.getLong("Mobile"),
					rs.getString("Gender").charAt(0),
					rs.getString("Address")
				));
			}
		}
		return members;
	}

}
