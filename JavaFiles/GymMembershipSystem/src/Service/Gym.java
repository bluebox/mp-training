package Service;

import java.util.List;

import dao.impl.MemberDAOImpl;
import dao.impl.MembershipPlanDAOImpl;
import dao.interfaces.MemberDAO;
import dao.interfaces.MembershipPlanDAO;
import model.Member;
import model.MembershipPlan;

public class Gym {
	private MemberDAO memberDAO;
	private MembershipPlanDAO planDAO;

	public Gym() {
		this.memberDAO = new MemberDAOImpl();
		this.planDAO = new MembershipPlanDAOImpl();

		initializePredefinedPlans();
	}

	private void initializePredefinedPlans() {
		planDAO.addPlan(new MembershipPlan("Basic", 1, 3000));
		planDAO.addPlan(new MembershipPlan("Silver", 3, 6000));
		planDAO.addPlan(new MembershipPlan("Premium", 6, 9000));
		planDAO.addPlan(new MembershipPlan("Gold", 12, 14000));
	}

	public void addMember(int memberId, String name, int age) throws IllegalArgumentException {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty.");
		}
		if (age <= 0 || age > 120) {
			throw new IllegalArgumentException("Invalid age. Age must be between 1 and 120.");
		}

		if (memberDAO.memberExists(memberId)) {
			throw new IllegalArgumentException("Member ID " + memberId + " already exists.");
		}

		Member newMember = new Member(memberId, name, age);
		memberDAO.addMember(newMember);
		System.out.println("Member added successfully!");
	}

	public void assignPlanToMember(int memberId, String planName) throws IllegalArgumentException {
		Member member = memberDAO.getMemberById(memberId);
		if (member == null) {
			throw new IllegalArgumentException("Member with ID " + memberId + " not found.");
		}

		MembershipPlan plan = planDAO.getPlanByName(planName);
		if (plan == null) {
			throw new IllegalArgumentException("Plan '" + planName + "' not found.");
		}

		int planDbId = planDAO.getPlanIdByName(planName);
		if (planDbId == -1) {
			throw new IllegalArgumentException("Internal error: Could not retrieve ID for plan '" + planName + "'.");
		}

		memberDAO.assignPlanToMember(memberId, planDbId);
		System.out.println("Plan '" + planName + "' assigned to member ID " + memberId);
	}

	public void viewAllMembers() {
		List<Member> members = memberDAO.getAllMembers();
		if (members.isEmpty()) {
			System.out.println("No members registered.");
			return;
		}
		System.out.println("\n--- Registered Members ---");
		for (Member member : members) {
			member.showDetails();
			System.out.println("-------------------");
		}
	}

	public void displayAvailablePlans() {
		List<MembershipPlan> plans = planDAO.getAllPlans();
		if (plans.isEmpty()) {
			System.out.println("No plans available.");
			return;
		}
		System.out.println("\n--- Available Plans ---");
		for (MembershipPlan plan : plans) {
			System.out.println("Plan: " + plan.getPlanName() + ", Duration: " + plan.getDurationMonths()
					+ " months, Fee: " + plan.getFee());
		}
	}
}