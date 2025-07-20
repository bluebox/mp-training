package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.MembershipPlanDAO;
import model.MembershipPlan;
import util.DBConnectionManager;

public class MembershipPlanDAOImpl implements MembershipPlanDAO {

	@Override
	public void addPlan(MembershipPlan plan) {
		String sql = "INSERT INTO membership_plans (plan_name, duration_months, fee) VALUES (?, ?, ?)";

		try (Connection conn = DBConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, plan.getPlanName());
			pstmt.setInt(2, plan.getDurationMonths());
			pstmt.setDouble(3, plan.getFee());
			pstmt.executeUpdate();
			System.out.println("DB: Plan '" + plan.getPlanName() + "' inserted.");
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				System.out.println("DB Warning: Plan '" + plan.getPlanName() + "' already exists. Skipping insertion.");
			} else {
				System.err.println("DB Error adding plan: " + e.getMessage());
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public MembershipPlan getPlanByName(String planName) {
		String sql = "SELECT plan_id, plan_name, duration_months, fee FROM membership_plans WHERE plan_name = ?";

		try (Connection conn = DBConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, planName);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new MembershipPlan(rs.getString("plan_name"), rs.getInt("duration_months"),
							rs.getDouble("fee"));
				}
			}
		} catch (SQLException e) {
			System.err.println("DB Error getting plan by name: " + e.getMessage());
			throw new RuntimeException("Database operation failed: getPlanByName", e);
		}
		return null;
	}

	@Override
	public List<MembershipPlan> getAllPlans() {
		List<MembershipPlan> plans = new ArrayList<>();
		String sql = "SELECT plan_id, plan_name, duration_months, fee FROM membership_plans ORDER BY plan_id";

		try (Connection conn = DBConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				plans.add(new MembershipPlan(rs.getString("plan_name"), rs.getInt("duration_months"),
						rs.getDouble("fee")));
			}
		} catch (SQLException e) {
			System.err.println("DB Error getting all plans: " + e.getMessage());
			throw new RuntimeException(e);
		}
		return plans;
	}

	@Override
	public boolean planExists(String planName) {
		String sql = "SELECT COUNT(*) FROM membership_plans WHERE plan_name = ?";

		try (Connection conn = DBConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, planName);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException e) {
			System.err.println("DB Error checking plan existence: " + e.getMessage());
			throw new RuntimeException(e);
		}
		return false;
	}

	@Override
	public int getPlanIdByName(String planName) {
		String sql = "SELECT plan_id FROM membership_plans WHERE plan_name = ?";

		try (Connection conn = DBConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, planName);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("plan_id");
				}
			}
		} catch (SQLException e) {
			System.err.println("DB Error getting plan ID by name: " + e.getMessage());
			throw new RuntimeException(e);
		}
		return -1;
	}
}