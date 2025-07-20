package DataAccessObject;

import DBConnection.DatabaseConnection;
import Model.Member;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public void addMember(Member member) {
        String sql = "INSERT INTO members (name, join_date, membership_type) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getJoinDate());
            stmt.setString(3, member.getMembershipType());
            stmt.executeUpdate();
            System.out.println("Member added.");
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    public List<Member> getAllMembers() {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Member m = new Member();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setJoinDate(rs.getString("join_date"));
                m.setMembershipType(rs.getString("membership_type"));
                list.add(m);
            }
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
        return list;
    }

    public void updateMember(Member member) {
        String sql = "UPDATE members SET name=?, join_date=?, membership_type=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getJoinDate());
            stmt.setString(3, member.getMembershipType());
            stmt.setInt(4, member.getId());
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Member updated.");
            else System.out.println("Member ID not found.");
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    public void deleteMember(int id) {
        String sql = "DELETE FROM members WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Member deleted.");
            else System.out.println("Member ID not found.");
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }
}
