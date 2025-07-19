package DAO;

import GymManagement.MembershipPlan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {
    private Connection conn;

    public PlanDao(Connection conn) {
        this.conn = conn;
    }

    public List<MembershipPlan> getAllPlans() throws SQLException {
        List<MembershipPlan> plans = new ArrayList<>();
        String sql = "SELECT * FROM membership_plans";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                plans.add(new MembershipPlan(
                        rs.getString("plan_name"),
                        rs.getInt("duration_months"),
                        rs.getDouble("fee")
                ));
            }
        }
        return plans;
    }

    public MembershipPlan getPlanByIndex(int index) throws SQLException {
        List<MembershipPlan> plans = getAllPlans();
        if (index >= 0 && index < plans.size()) {
            return plans.get(index);
        }
        return null;
    }
}
