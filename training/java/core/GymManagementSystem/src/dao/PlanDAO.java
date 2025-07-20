package dao;

import model.MembershipPlan;
import util.DBConnection;

import java.sql.*;
import java.util.*;

public class PlanDAO {

    public List<MembershipPlan> getAllPlans() throws SQLException {
        List<MembershipPlan> list = new ArrayList<>();
        String sql = "SELECT * FROM membership_plans";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);
        		ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new MembershipPlan(
                    rs.getInt("plan_id"),
                    rs.getString("plan_name"),
                    rs.getInt("duration_months"),
                    rs.getDouble("fee")
                ));
            }
        }
        return list;
    }
    
}

