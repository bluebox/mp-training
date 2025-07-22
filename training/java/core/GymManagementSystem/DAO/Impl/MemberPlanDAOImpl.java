package GymManagementSystem.DAO.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import GymManagementSystem.DAO.DBConnection;
import GymManagementSystem.DAO.MemberPlanDAO;
import GymManagementSystem.models.Member;
import GymManagementSystem.models.MemberPlan;
import GymManagementSystem.utils.CSVExport;

public class MemberPlanDAOImpl implements MemberPlanDAO{
	
	@Override
	public void assignPlan(MemberPlan plan) {
		String sql = "INSERT INTO member_plan_mapping (member_id, plan_id, start_date) VALUES (?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, plan.getMemberId());
			stmt.setInt(2, plan.getPlanId());
			stmt.setDate(3, Date.valueOf(plan.getStartDate()));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MemberPlan getMemberPlanById(int memberId) {
	    String query = "SELECT * FROM member_plan_mapping WHERE member_id = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setInt(1, memberId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	           Date date = rs.getDate("start_date");
	            int planId = rs.getInt("plan_id");
	            return new MemberPlan(memberId, planId, date.toLocalDate());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
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

	@Override
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
	
	@Override
	public void deleteMembership(int id) {
		String sql = "DELETE FROM member_plan_mapping WHERE member_id = ?";
		try (Connection conn = DBConnection.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
//			stmt.executeUpdate();
			int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Membership deleted successfully.");
	        } else {
	            System.out.println("No membership found with ID: " + id);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateMembership(MemberPlan plan) {
		String sql = "UPDATE member_plan_mapping SET plan_id = ?, start_date = ? WHERE member_id = ?";
		try (Connection conn = DBConnection.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, plan.getPlanId());
			stmt.setDate(2, Date.valueOf(plan.getStartDate()));
			stmt.setInt(3, plan.getMemberId());
//			stmt.executeUpdate();
			int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Membership updated successfully.");
	        } else {
	            System.out.println("No membership found with ID: " + plan.getMemberId());
	        }

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
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
	
	@Override
	public void ExportFullReport() {
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
			List<String[]> output = new ArrayList<>();
			output.add(new String[]{"memberId", "name", "age", "planId", "planName", "duration", "price", "startDate"});
			while (rs.next()) {
				int memberId = rs.getInt("member_id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				int planId = rs.getInt("plan_id");
				String planName = rs.getString("plan_name");
				int duration = rs.getInt("duration_months");
				double price = rs.getDouble("fee");
				LocalDate startDate = rs.getDate("start_date").toLocalDate();				
				output.add(new String[]{
						String.valueOf(memberId), name, String.valueOf(age), String.valueOf(planId), planName, String.valueOf(duration), String.valueOf(price), startDate.toString()
	                });
			}
		    CSVExport.exportToCSV("C:\\Users\\DELL\\OneDrive\\Desktop\\Medplus\\Java\\Assignment\\src\\GymManagementSystem\\gym_report.csv", output);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MemberPlan> getAllMemberships() {
		List<MemberPlan> memberships = new ArrayList<>();

		String sql = "SELECT * FROM member_plan_mapping";

		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				int member_id = rs.getInt("member_id");
				int plan_id = rs.getInt("plan_id");
				LocalDate date = rs.getDate("start_date").toLocalDate();

				MemberPlan member = new MemberPlan(member_id, plan_id, date);
				memberships.add(member);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return memberships;
	}

}
