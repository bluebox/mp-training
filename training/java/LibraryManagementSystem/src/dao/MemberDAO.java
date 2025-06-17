package dao;


import model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
import Exception.DatabaseException;

public class MemberDAO {
	
	public void addMember(Member member) throws DatabaseException {
		String insert = "INSERT INTO members (name, Email, mobile, gender, address) VALUES (?, ?, ?, ?, ?)";

	    Connection conn = null;
	    try {
	        conn = JDBCConnection.getConnection();
	        conn.setAutoCommit(false);

	        PreparedStatement stmt = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
	        stmt.setString(1, member.getName());
	        stmt.setString(2, member.getEmail());
	        stmt.setLong(3, member.getMobile());
	        stmt.setString(4, String.valueOf(member.getGender()));
	        stmt.setString(5, member.getAddress());
	        stmt.executeUpdate();

	        conn.commit();
	    } catch (Exception e) {
	        throw new DatabaseException("Error adding member", e);
	    } finally {
			if (conn != null)
				try {
					conn.close();
					throw new Exception("Error in closing connection");
				} catch (Exception ignore) {
					System.err.println(ignore.getMessage());
				}
	    }
	}

	public void updateMember(Member member) throws Exception {
	    String update = "UPDATE members SET name=?, email=?, mobile=?, gender=?, address=? WHERE memberId=?";
	    String insertLog = "INSERT INTO members_log (memberId, name, email, mobile, gender, address) VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection conn = JDBCConnection.getConnection()) {
	        conn.setAutoCommit(false);

	        PreparedStatement logStmt = conn.prepareStatement(insertLog);
	        logStmt.setInt(1, member.getMemberId());
	        logStmt.setString(2, member.getName());
	        logStmt.setString(3, member.getEmail());
	        logStmt.setLong(4, member.getMobile());
	        logStmt.setString(5, String.valueOf(member.getGender()));
	        logStmt.setString(6, member.getAddress());
	        logStmt.executeUpdate();
	        
	        Member getMember = getMemberById(member.getMemberId());
	        if (isEqual(member,getMember)) {
	        	throw new Exception("No change in values");
	        }
	        PreparedStatement stmt = conn.prepareStatement(update);
	        stmt.setString(1, member.getName());
	        stmt.setString(2, member.getEmail());
	        stmt.setLong(3, member.getMobile());
	        stmt.setString(4, String.valueOf(member.getGender()));
	        stmt.setString(5, member.getAddress());
	        stmt.setInt(6, member.getMemberId());
	        stmt.executeUpdate();

	        conn.commit();
	    }
	}

	private boolean isEqual(Member member, Member newMember) {
		return (member.getName()==newMember.getName() || member.getEmail()==newMember.getEmail());
	}

	public List<Member> getAllMembers() throws Exception {
		List<Member> members = new ArrayList<>();
		String query = "SELECT * FROM members";
		try (Connection conn = JDBCConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(query);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				members.add(new Member(
					rs.getInt("memberId"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getLong("mobile"),
					rs.getString("gender").charAt(0),
					rs.getString("address")
				));
			}
		}
		return members;
	}
	
	public Member getMemberById(int memberId) throws Exception {
		String query = "SELECT * FROM members where memberId=?";
		try (Connection conn = JDBCConnection.getConnection()){
			 PreparedStatement stmt = conn.prepareStatement(query);
			 stmt.setString(1, Integer.toString(memberId));
			 ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Member(
					rs.getInt("memberId"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getLong("mobile"),
					rs.getString("gender").charAt(0),
					rs.getString("address"));
			}
		}
		return null;
	}

}
