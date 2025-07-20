package dao;

import model.Member;
import model.MembershipPlan;
import util.DBConnection;

import java.sql.*;
import java.util.*;

public class MemberDAO {

    public void addMember(Member member) throws SQLException {
        String sql = "INSERT INTO members (name, age) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, member.getName());
            ps.setInt(2, member.getAge());
            ps.executeUpdate();
        }
    }

    public void assignPlan(int memberId, int planId) throws SQLException {
        String sql = "UPDATE members SET plan_id = ? WHERE member_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, planId);
            ps.setInt(2, memberId);
            ps.executeUpdate();
        }
    }

    public boolean deleteMember(int memberId) throws Exception {
        String query = "DELETE FROM members WHERE member_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, memberId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public List<Member> getAllMembers() throws SQLException {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT m.member_id, m.name, m.age, p.plan_id, p.plan_name, p.duration_months, p.fee " +
                     "FROM members m LEFT JOIN membership_plans p ON m.plan_id = p.plan_id";

        try ( Connection con = DBConnection.getConnection();
        		PreparedStatement ps = con.prepareStatement(sql);
        		ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Member member = new Member(rs.getInt("member_id"), rs.getString("name"), rs.getInt("age"));
                if (rs.getInt("plan_id") > 0) {
                    MembershipPlan plan = new MembershipPlan(
                        rs.getInt("plan_id"),
                        rs.getString("plan_name"),
                        rs.getInt("duration_months"),
                        rs.getDouble("fee")
                    );
                    member.setPlan(plan);
                }
                list.add(member);
            }
        }
        return list;
    }
}
