package dao;

import model.Member;
import model.MembershipPlan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    private Connection conn;
    public MemberDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/gymmembership";
            String username = "root";
            String password = "Sreeja@03";
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public List<MembershipPlan> getAllPlans() throws SQLException {
        List<MembershipPlan> plans = new ArrayList<>();
        String sql = "SELECT * FROM plans";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("Plan_name");
                int duration = rs.getInt("Duration_In_Months");
                int fee = rs.getInt("Fee");
                plans.add(new MembershipPlan(name, duration, fee));
            }
        }
        return plans;
    }
    public void addMember(Member m) throws SQLException {
        String sql = "INSERT INTO membershipdetails(Name, Age, Plan, Duration_In_Months, Fee) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, m.getName());
            ps.setInt(2, m.getAge());
            MembershipPlan p = m.getPlan();
            if (p != null) {
                ps.setString(3, p.getPlanName());
                ps.setInt(4, p.getDuration());
                ps.setDouble(5, p.getFee());
            } else {
                ps.setNull(3, Types.VARCHAR);
                ps.setNull(4, Types.INTEGER);
                ps.setNull(5, Types.DOUBLE);
            }
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    System.out.println("Member added successfully. Member ID: " + generatedId);
                } else {
                    System.out.println("Member added successfully.");
                }
            }
        }
    }
    public void updatePlanForMember(int memberId, MembershipPlan plan) throws SQLException {
        String sql = "UPDATE membershipdetails SET Plan = ?, Duration_In_Months = ?, Fee = ? WHERE member_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, plan.getPlanName());
            ps.setInt(2, plan.getDuration());
            ps.setDouble(3, plan.getFee());
            ps.setInt(4, memberId);

            int updated = ps.executeUpdate();
            if (updated > 0) {
                System.out.println("Plan updated successfully for Member ID: " + memberId);
            } else {
                System.out.println("No member found with ID: " + memberId);
            }
        }
    }
    public void deleteMember(int memberId) throws SQLException {
        String sql = "DELETE FROM membershipdetails WHERE member_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Member with ID " + memberId + " has been removed from the gym.");
            } else {
                System.out.println("No member found with ID: " + memberId);
            }
        }
    }
    public void cancelMembership(int memberId) throws SQLException {
        String sql = "UPDATE membershipdetails SET Plan = NULL, Duration_In_Months = NULL, Fee = NULL WHERE member_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Membership cancelled for Member ID: " + memberId);
            } else {
                System.out.println("No member found with ID: " + memberId);
            }
        }
    }
    public MembershipPlan getPlanByName(String planName) throws SQLException {
        String sql = "SELECT * FROM plans WHERE Plan_name = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, planName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int duration = rs.getInt("Duration_In_Months");
                    double fee = rs.getDouble("Fee");
                    return new MembershipPlan(planName, duration, fee);
                }
            }
        }
        return null;
    }
    public List<String> viewAllMembers() throws SQLException {
        List<String> members = new ArrayList<>();
        String sql = "SELECT * FROM membershipdetails";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                StringBuilder sb = new StringBuilder();
                sb.append(rs.getInt("member_id")).append("   ")
                  .append(rs.getString("Name")).append("    ")
                  .append(rs.getInt("Age"));

                String planName = rs.getString("Plan");
                if (planName != null) {
                    sb.append("    ").append(planName)
                      .append("    ").append(rs.getInt("Duration_In_Months"))
                      .append(" months  ₹").append(rs.getDouble("Fee"));
                } else {
                    sb.append("    No Plan Assigned");
                }
                members.add(sb.toString());
            }
        }
        return members;
    }
    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
