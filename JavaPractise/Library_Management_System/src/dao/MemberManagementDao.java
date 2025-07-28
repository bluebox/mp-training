package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import domain.Gender;
import domain.Member;
import util.DBUtil;

public class MemberManagementDao implements MemberManagementDaoInterface{
	public boolean registerNewMember(Member m) throws Exception {
	    String sql = "INSERT INTO members(Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";
	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        ps.setString(1, m.getName());
	        ps.setString(2, m.getEmail());
	        ps.setString(3, m.getMobile());
	        ps.setString(4, m.getGender().name());
	        ps.setString(5, m.getAddress());
	        int rows = ps.executeUpdate();
	        if (rows > 0) {
	            try (ResultSet rs = ps.getGeneratedKeys()) {
	                if (rs.next()) {
	                    int generatedId = rs.getInt(1);
	                    System.out.println("Member added successfully. Member ID: " + generatedId);
	                } else {
	                    System.out.println("Member was not added");
	                }
	            }
	            return true;
	        } else {
	            return false; 	      
	            }
	    }
	} 
	 public boolean updatermemberDetails(int memberId, Member m) throws Exception {
			    String fetchSql = "SELECT * FROM members WHERE MemberId = ?";
			    String logSql = "INSERT INTO members_log (MemberId, Name, Email, Mobile, Gender, Address, Operation, OperationDate) VALUES (?, ?, ?, ?, ?, ?, 'UPDATE',CURRENT_TIMESTAMP)";
			    String updateSql = "UPDATE members SET Name = ?, Email = ?, Mobile = ?, Address = ? WHERE MemberId = ?";
			    try (Connection conn = DBUtil.getConnection()) {
			        conn.setAutoCommit(false);
			        try (PreparedStatement fetchPs = conn.prepareStatement(fetchSql)) {
			            fetchPs.setInt(1, memberId);
			            try (ResultSet rs = fetchPs.executeQuery()) {
			                if (rs.next()) {
			                    try (PreparedStatement logPs = conn.prepareStatement(logSql)) {
			                        logPs.setInt(1, rs.getInt("MemberId"));
			                        logPs.setString(2, rs.getString("Name"));
			                        logPs.setString(3, rs.getString("Email"));
			                        logPs.setString(4, rs.getString("Mobile"));
			                        logPs.setString(5, rs.getString("Gender"));
			                        logPs.setString(6, rs.getString("Address"));
			                        logPs.executeUpdate();
			                    }
			                    try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
			                    	updatePs.setString(1, m.getName());
			                        updatePs.setString(2, m.getEmail());
			                        updatePs.setString(3, m.getMobile());
			                        updatePs.setString(4, m.getAddress());
			                        updatePs.setInt(5, memberId);
			                        int updated = updatePs.executeUpdate();
			                        if (updated > 0) {
			                        	conn.commit();
			                            System.out.println("Member Details updated successfully for Member ID: " + memberId);
			                            return true;
			                        }
			                    }
			                    conn.commit();
			                } else {
			                    System.out.println("No member found with ID: " + memberId);
			                }
			            }
			        } 
			    }
			    return false;
			}
	 public List<Member> viewAllMembers() throws Exception {
			List<Member> members = new ArrayList<>();
			String query = "SELECT * FROM members";
			try (Connection conn = DBUtil.getConnection(); 
					PreparedStatement ps = conn.prepareStatement(query)) {
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Member member = new Member(rs.getInt("memberId"), rs.getString("name"), rs.getString("email"),
							rs.getString("mobile"), Gender.valueOf(rs.getString("gender")), rs.getString("address"));
					members.add(member);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return members;
		}	
}