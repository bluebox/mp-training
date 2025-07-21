package repository;
import model.MembershipPlan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import database.DBUtil;

public class PlanRepository {
    public List<MembershipPlan> getAllPlans() {
        List<MembershipPlan> plans = new ArrayList<>();
        String sql = "Select * from membership_plans";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) 
        {
            while (rs.next()) 
            {
                plans.add(new MembershipPlan(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
            }
        } 
        catch (Exception e) {
            System.out.println("Error fetching plans");
        }
        return plans;
    }
}
