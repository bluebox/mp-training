package gymCaseStudyWithJdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gymCaseStudyWithJdbc.dao.GymDao;

public class Gym {
	private GymDao gymDao;

	public Gym() {
		this.gymDao = new GymDao();
	}

	public void addMember(Member member) {
		try {
			if (gymDao.findMemberById(member.getMemberId()) != null) {
				System.out.println("Error: Member with ID " + member.getMemberId() + " already exists.");
				return;
			}
			gymDao.addMember(member);
			System.out.println("Member " + member.getName() + " added successfully to the database.");
		} catch (SQLException e) {
			System.err.println("Database error while adding member: " + e.getMessage());
		}

	}

	public Member findMemberById(int memberId) {
		try {
			return gymDao.findMemberById(memberId);
		} catch (SQLException e) {
			System.err.println("Database error while finding member: " + e.getMessage());
			return null;
		}
	}

	public void displayAllMembers() {
		try {
			List<Member> members = gymDao.getAllMember();
			if (members.isEmpty()) {
				System.out.println("No members registered in the database.");
				return;
			}
			System.out.println("\n--- All Registered Members ---");
			for (Member member : members) {
				member.showDetails();
			}
		} catch (SQLException e) {
			System.err.println("Database error while displaying members: " + e.getMessage());
		}
	}

	public List<MembershipPlan> getAvailablePlans() {
		try {
			return gymDao.getAllPlans();
		} catch (SQLException e) {
			System.err.println("Database error while fetching plans: " + e.getMessage());
			return new ArrayList<>();
		}
	}

	public void assignPlanToMember(int memberId, int planId) {
		try {
			gymDao.assignPlanToMember(memberId, planId);
			System.out.println("Plan assigned successfully.");
		} catch (SQLException e) {
			System.err.println("Database error while assigning plan: " + e.getMessage());
		}
	}

	public void closeConnection() {
		try {
			gymDao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}