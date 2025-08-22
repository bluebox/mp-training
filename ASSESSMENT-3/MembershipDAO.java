package DataAccessObject;

import DBConnection.DatabaseConnection;
import Model.Membership;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembershipDAO {

    public void addMembership(Membership membership) {
        String sql = "INSERT INTO membership (name, duration_months, price) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, membership.getName());
            stmt.setInt(2, membership.getDurationMonths());
            stmt.setDouble(3, membership.getPrice());
            stmt.executeUpdate();
            System.out.println("Membership added.");
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    public List<Membership> getAllMemberships() {
        List<Membership> list = new ArrayList<>();
        String sql = "SELECT * FROM membership";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Membership m = new Membership();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setDurationMonths(rs.getInt("duration_months"));
                m.setPrice(rs.getDouble("price"));
                list.add(m);
            }
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
        return list;
    }

    public void updateMembership(Membership membership) {
        String sql = "UPDATE membership SET name=?, duration_months=?, price=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, membership.getName());
            stmt.setInt(2, membership.getDurationMonths());
            stmt.setDouble(3, membership.getPrice());
            stmt.setInt(4, membership.getId());
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Membership updated.");
            else System.out.println("Membership ID not found.");
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    public void deleteMembership(int id) {
        String sql = "DELETE FROM membership WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Membership deleted.");
            else System.out.println("Membership ID not found.");
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }
}
