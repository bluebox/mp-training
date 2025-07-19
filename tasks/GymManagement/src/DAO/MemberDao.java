package DAO;

import GymManagement.Member;
import GymManagement.MembershipPlan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    private Connection conn;

    public MemberDao(Connection conn) {
        this.conn = conn;
    }

    public void insertMember(Member member) throws SQLException {
        String sql = "INSERT INTO members (member_id, name, age, plan_name) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, member.getMemberId());
            pstmt.setString(2, member.getName());
            pstmt.setInt(3, member.getAge());
            pstmt.setString(4, member.getPlan() != null ? member.getPlan().getPlanName() : null);
            pstmt.executeUpdate();
        }
    }

    public Member findById(String memberId) throws SQLException {
        String sql = "SELECT * FROM members WHERE member_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, memberId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Member(
                        rs.getString("member_id"),
                        rs.getString("name"),
                        rs.getInt("age")
                );
            }
        }
        return null;
    }

    public void updateMemberPlan(String memberId, String planName) throws SQLException {
        String sql = "UPDATE members SET plan_name = ? WHERE member_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, planName);
            pstmt.setString(2, memberId);
            pstmt.executeUpdate();
        }
    }

    public List<Member> getAllMembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Member member = new Member(
                        rs.getString("member_id"),
                        rs.getString("name"),
                        rs.getInt("age")
                );
                String planName = rs.getString("plan_name");
                if (planName != null) {
                    MembershipPlan plan = getPlanByName(planName);
                    member.assignPlan(plan);
                }
                members.add(member);
            }
        }
        return members;
    }
    private MembershipPlan getPlanByName(String planName) throws SQLException {
        String sql = "SELECT * FROM membership_plans WHERE plan_name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, planName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new MembershipPlan(
                        rs.getString("plan_name"),
                        rs.getInt("duration_months"),
                        rs.getDouble("fee")
                );
            }
        }
        return null;
    }
}
