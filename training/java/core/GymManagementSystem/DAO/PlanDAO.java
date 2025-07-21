package GymManagementSystem.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import GymManagementSystem.models.MembershipPlan;

public class PlanDAO {
	public MembershipPlan getPlanById(int planId) {
	    String query = "SELECT * FROM membership_plans WHERE plan_id = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setInt(1, planId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            String name = rs.getString("plan_name");
	            int duration = rs.getInt("duration_months");
	            double price = rs.getDouble("fee");
	            return new MembershipPlan(planId, name, duration, price);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public List<MembershipPlan> getAllPlans() {
		List<MembershipPlan> plans = new ArrayList<>();
		String sql = "SELECT * FROM membership_plans";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				plans.add(new MembershipPlan(rs.getInt("plan_id"), rs.getString("plan_name"),
						rs.getInt("duration_months"), rs.getDouble("fee")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return plans;
	}

	public boolean addPlan(MembershipPlan plan) {
		String sql = "INSERT INTO membership_plans (plan_name, duration_months, fee) VALUES (?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, plan.getPlanName());
			stmt.setInt(2, plan.getDurationMonths());
			stmt.setDouble(3, plan.getFee());
			int rowsInserted = stmt.executeUpdate();
			return rowsInserted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void deletePlan(int id) {
		String sql = "DELETE FROM membership_plans WHERE plan_id = ?";
		try (Connection conn = DBConnection.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
//			stmt.executeUpdate();
			int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Plan deleted successfully.");
	        } else {
	            System.out.println("No plan found with ID: " + id);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePlan(MembershipPlan plan) {
		String sql = "UPDATE membership_plans SET plan_name = ?, duration_months = ?, fee = ? WHERE plan_id = ?";
		try (Connection conn = DBConnection.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, plan.getPlanName());
			stmt.setInt(2, plan.getDurationMonths());
			stmt.setDouble(3, plan.getFee());
			stmt.setInt(4, plan.getPlanId());
//			stmt.executeUpdate();
			int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Plan updated successfully.");
	        } else {
	            System.out.println("No plan found with ID: " + plan.getPlanId());
	        }

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
