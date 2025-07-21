package GymManagementSystem.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import GymManagementSystem.models.Member;

public class MemberDAO {
	public Member getMemberById(int memberId) {
	    String query = "SELECT * FROM members WHERE member_id = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setInt(1, memberId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            String name = rs.getString("name");
	            int age = rs.getInt("age");
	            return new Member(name, age, memberId);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public void addMember(Member member) {
		String sql = "INSERT INTO members (name, age) VALUES (?, ?)";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, member.getName());
	        stmt.setInt(2, member.getAge());
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public List<Member> getAllMembers() {
		List<Member> list = new ArrayList<>();
		String sql = "SELECT * FROM members";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				list.add(new Member(rs.getString("name"), rs.getInt("age"), rs.getInt("member_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	

	public void deleteMember(int id) {
		String sql = "DELETE FROM members WHERE member_id = ?";
		try (Connection conn = DBConnection.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
//			stmt.executeUpdate();
			int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Member deleted successfully.");
	        } else {
	            System.out.println("No member found with ID: " + id);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateMember(Member member) {
		String sql = "UPDATE members SET name = ?, age = ? WHERE member_id = ?";
		try (Connection conn = DBConnection.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, member.getName());
			stmt.setInt(2, member.getAge());
			stmt.setInt(3, member.getMemberId());
//			stmt.executeUpdate();
			int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Member updated successfully.");
	        } else {
	            System.out.println("No member found with ID: " + member.getMemberId());
	        }

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
