package GymManagementSystem.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import GymManagementSystem.models.Member;
import GymManagementSystem.models.MemberPlan;
import GymManagementSystem.models.MembershipPlan;

public class MemberPlanDAO {
	public void assignPlan(MemberPlan plan) {
	    String sql = "INSERT INTO member_plan_mapping (member_id, plan_id, start_date) VALUES (?, ?, ?)";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, plan.getMemberId());
	        stmt.setInt(2, plan.getPlanId());
	        stmt.setDate(3, Date.valueOf(plan.getStartDate()));
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public List<Integer> getActiveMemberIds() {
		List<Integer> activeIds = new ArrayList<>();

		String sql = """
					SELECT m.member_id
				    FROM member_plan_mapping m
				    JOIN membership_plans p ON m.plan_id = p.plan_id
				    WHERE DATE_ADD(m.start_date, INTERVAL p.duration_months MONTH) >= CURDATE()
				""";

		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				activeIds.add(rs.getInt("member_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return activeIds;
	}
	
	public List<Member> getActiveMembers() {
	    List<Member> activeMembers = new ArrayList<>();

	    String sql = """
	        SELECT mem.member_id, mem.name, mem.age
	        FROM member_plan_mapping m
	        JOIN membership_plans p ON m.plan_id = p.plan_id
	        JOIN members mem ON m.member_id = mem.member_id
	        WHERE DATE_ADD(m.start_date, INTERVAL p.duration_months MONTH) >= CURDATE()
	    """;

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            int id = rs.getInt("member_id");
	            String name = rs.getString("name");
	            int age = rs.getInt("age");

	            Member member = new Member(name, age, id);
	            activeMembers.add(member);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return activeMembers;
	}
	
	public void viewFullReport() {
		    String sql = """
		        SELECT m.member_id, m.name, m.age,
		               p.plan_id, p.plan_name, p.duration_months, p.fee,
		               mp.start_date
		        FROM member_plan_mapping mp
		        JOIN members m ON mp.member_id = m.member_id
		        JOIN membership_plans p ON mp.plan_id = p.plan_id
		        ORDER BY m.member_id
		    """;

		    try (Connection conn = DBConnection.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql);
		         ResultSet rs = stmt.executeQuery()) {

		        System.out.println("\n===== Member - Plan Report =====");
		        while (rs.next()) {
		            int memberId = rs.getInt("member_id");
		            String name = rs.getString("name");
		            int age = rs.getInt("age");

		            int planId = rs.getInt("plan_id");
		            String planName = rs.getString("plan_name");
		            int duration = rs.getInt("duration_months");
		            double price = rs.getDouble("fee");
		            LocalDate startDate = rs.getDate("start_date").toLocalDate();

		            System.out.printf("""
		                \nMember ID   : %d
		                Name        : %s
		                Age         : %d
		                Plan ID     : %d
		                Plan Name   : %s
		                Duration    : %d months
		                Price       : ₹%.2f
		                Start Date  : %s
		                -----------------------------
		                """, memberId, name, age, planId, planName, duration, price, startDate);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
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
