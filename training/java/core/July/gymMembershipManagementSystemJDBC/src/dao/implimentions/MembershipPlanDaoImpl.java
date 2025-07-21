package dao.implimentions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MembershipPlanDao;
import models.MembershipPlan;
import utils.PreparedStatementManager;
import utils.SQLQueries;

public class MembershipPlanDaoImpl implements MembershipPlanDao {

	@Override
	public void addPlan(MembershipPlan plan) {
		try {
			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.INSERT_MEMBERSHIP_PLAN);

			stmt.setString(1, plan.getPlanName());
			stmt.setInt(2, plan.getDurationMonths());
			stmt.setDouble(3, plan.getFee());

			stmt.executeUpdate();
			System.out.println("Plan added successfully.");
		} catch (SQLException e) {
			System.out.println("Error adding plan: " + e.getMessage());
		}

	}

	@Override
	public MembershipPlan getPlanById(int id) {
		try {

			PreparedStatement stmt = PreparedStatementManager
					.getPreparedStatement(SQLQueries.SELECT_MEMBERSHIP_PLAN_BY_ID);

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new MembershipPlan(rs.getInt("id"), rs.getString("plan_name"), rs.getInt("duration_months"),
						rs.getDouble("fee"));
			}
		} catch (SQLException e) {
			System.out.println("Error fetching plan: " + e.getMessage());
		}

		return null;
	}

	@Override
	public List<MembershipPlan> getAllPlans() {
		List<MembershipPlan> plans = new ArrayList<>();
		try {

			PreparedStatement stmt = PreparedStatementManager
					.getPreparedStatement(SQLQueries.SELECT_ALL_MEMBERSHIP_PLANS);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				plans.add(new MembershipPlan(rs.getInt("id"), rs.getString("plan_name"), rs.getInt("duration_months"),
						rs.getDouble("fee")));
			}

		} catch (SQLException e) {

			System.out.println("Error fetching all plans: " + e.getMessage());

		}
		return plans;
	}

	@Override
	public void updatePlan(MembershipPlan plan) {
		try {

			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.UPDATE_MEMBERSHIP_PLAN);

			stmt.setString(1, plan.getPlanName());
			stmt.setInt(2, plan.getDurationMonths());
			stmt.setDouble(3, plan.getFee());
			stmt.setInt(4, plan.getId());

			stmt.executeUpdate();
			System.out.println("Plan updated successfully.");
		} catch (SQLException e) {
			System.out.println("Error updating plan: " + e.getMessage());
		}
	}

	@Override
	public void deletePlan(int id) {
		try {

			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.DELETE_MEMBERSHIP_PLAN);

			stmt.setInt(1, id);
			stmt.executeUpdate();
			System.out.println("Plan deleted successfully.");

		} catch (SQLException e) {
			System.out.println("Error deleting plan: " + e.getMessage());
		}

	}

}
