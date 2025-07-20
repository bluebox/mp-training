package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.MemberDAO;
import model.Member;
import model.MembershipPlan;
import util.DBConnectionManager;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public void addMember(Member member) {
		String sql = "INSERT INTO members (member_id, name, age) VALUES (?, ?, ?)";
		try (Connection conn = DBConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, member.getMemberId());
			pstmt.setString(2, member.getName());
			pstmt.setInt(3, member.getAge());
			pstmt.executeUpdate();
			System.out.println("DB: Member " + member.getName() + " inserted.");
		} catch (SQLException e) {
			System.err.println("DB Error adding member: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public Member getMemberById(int memberId) {
		String sql = """
				SELECT m.member_id, m.name, m.age, p.plan_name, p.duration_months, p.fee
				FROM members m  LEFT JOIN membership_plans p ON m.plan_id = p.plan_id
				WHERE m.member_id = ?
				""";

		try (Connection conn = DBConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, memberId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					MembershipPlan plan = null;
					if (rs.getString("plan_name") != null) {
						plan = new MembershipPlan(rs.getString("plan_name"), rs.getInt("duration_months"),
								rs.getDouble("fee"));
					}
					return new Member(rs.getInt("member_id"), rs.getString("name"), rs.getInt("age"), plan);
				}
			}
		} catch (SQLException e) {
			System.err.println("DB Error getting member by ID: " + e.getMessage());
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public List<Member> getAllMembers() {
		List<Member> members = new ArrayList<>();
		String sql = """
				SELECT m.member_id, m.name, m.age, p.plan_name, p.duration_months, p.fee
				FROM members m  LEFT JOIN membership_plans p ON m.plan_id = p.plan_id
				""";
		try (Connection conn = DBConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				MembershipPlan plan = null;
				if (rs.getString("plan_name") != null) {
					plan = new MembershipPlan(rs.getString("plan_name"), rs.getInt("duration_months"),
							rs.getDouble("fee"));
				}
				members.add(new Member(rs.getInt("member_id"), rs.getString("name"), rs.getInt("age"), plan));
			}
		} catch (SQLException e) {
			System.err.println("DB Error getting all members: " + e.getMessage());
			throw new RuntimeException("Database operation failed: getAllMembers", e);
		}
		return members;
	}

	@Override
	public void assignPlanToMember(int memberId, int planId) {
		String sql = "UPDATE members SET plan_id = ? WHERE member_id = ?";
		try (Connection conn = DBConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, planId);
			pstmt.setInt(2, memberId);
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected == 0) {
				System.out.println("DB: No member found with ID " + memberId + " to assign plan.");
			} else {
				System.out.println("DB: Plan assigned for member ID " + memberId + ".");
			}
		} catch (SQLException e) {
			System.err.println("DB Error assigning plan to member: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean memberExists(int memberId) {
		String sql = "SELECT COUNT(*) FROM members WHERE member_id = ?";

		try (Connection conn = DBConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, memberId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException e) {
			System.err.println("DB Error checking member existence: " + e.getMessage());
			throw new RuntimeException("Database operation failed: memberExists", e);
		}
		return false;
	}
}