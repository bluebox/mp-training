package GymManagementSystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import GymManagementSystem.DAO.MemberDAO;
import GymManagementSystem.DAO.MemberPlanDAO;
import GymManagementSystem.DAO.PlanDAO;
import GymManagementSystem.models.Member;
import GymManagementSystem.models.MemberPlan;
import GymManagementSystem.models.MembershipPlan;

public class GymService {
	private MemberDAO memberDAO;
	private PlanDAO planDAO;
	private MemberPlanDAO memberPlanDAO;
	private Scanner scanner;
	private InputValidation val;

	public GymService() {
		memberDAO = new MemberDAO();
		planDAO = new PlanDAO();
		memberPlanDAO = new MemberPlanDAO();
		scanner = new Scanner(System.in);
		val = new InputValidation();
	}
	
	private boolean isValidMemberId(int memberId) {
	    return memberDAO.getMemberById(memberId) != null;
	}
	
	private boolean isValidPlanId(int planId) {
	    return planDAO.getPlanById(planId) != null;
	}

	public void addMember() {
		String name = val.getStringInput("Enter member name: ");
		int age = val.getPositiveIntInput("Enter age: ");
		Member member = new Member(name, age); 
		memberDAO.addMember(member);
	}

	public void viewMembers() {
		List<Member> members = memberDAO.getAllMembers();
		if (members.isEmpty()) {
			System.out.println("No members found.");
		} else {
			members.forEach(System.out::println);
		}
	}

	public void addPlan() {
		String planName = val.getStringInput("Enter plan name: ");
		int duration = val.getIntInput("Enter duration in months: ");
		double price = val.getDoubleInput(("Enter price: "));
		MembershipPlan plan = new MembershipPlan(planName, duration, price);
		planDAO.addPlan(plan);
	}

	public void viewPlans() {
		List<MembershipPlan> plans = planDAO.getAllPlans();
		if (plans.isEmpty()) {
			System.out.println("No plans found.");
		} else {
			plans.forEach(System.out::println);
		}
	}

	public void mapMemberToPlan() {
		int memberId = val.getIntInput("Enter member ID: ");
		if (!isValidMemberId(memberId)) {
	        System.out.println("Member ID not found. Please enter a valid ID.");
	        return;
	    }
		int planId = val.getIntInput("Enter plan ID: ");
		if (!isValidPlanId(planId)) {
	        System.out.println("Plan ID not found. Please enter a valid ID.");
	        return;
	    }
		System.out.print("Enter start date (yyyy-mm-dd): ");
		String dateStr = scanner.next();
	    LocalDate startDate;
	    try {
	        startDate = LocalDate.parse(dateStr); // Validates format
	        if (startDate.isAfter(LocalDate.now())) {
	            System.out.println("Start date cannot be in the future.");
	            return;
	        }
	    } catch (Exception e) {
	        System.out.println("Invalid date format. Please enter date as yyyy-mm-dd.");
	        return;
	    }
	    MemberPlan mapping = new MemberPlan(memberId, planId, startDate);
	    memberPlanDAO.assignPlan(mapping);
	}

	public void viewActiveMembers() {
		List<Member> activeMembers = memberPlanDAO.getActiveMembers();
		if (activeMembers.isEmpty()) {
			System.out.println("No active members.");
		} else {
			activeMembers.forEach(System.out::println);
		}
	}

	public void updateMember() {
		int memberId = val.getIntInput("Enter member ID to update: ");
		if (!isValidMemberId(memberId)) {
			System.out.println("Member ID not found. Please enter a valid ID.");
			return;
		}
		String name = val.getStringInput("Enter new name: ");
		int age = val.getIntInput("Enter new age: ");
		Member member = new Member(name, age, memberId);
		memberDAO.updateMember(member);
	}

	public void deleteMember() {
		int memberId = val.getIntInput("Enter member ID to delete: ");
		if (!isValidMemberId(memberId)) {
			System.out.println("Member ID not found. Please enter a valid ID.");
			return;
		}
		memberDAO.deleteMember(memberId);
	}
	
	public void updatePlan() {
		int planId = val.getIntInput("Enter plan ID to update: ");
		if (!isValidPlanId(planId)) {
			System.out.println("Plan ID not found. Please enter a valid ID.");
			return;
		}
		String name = val.getStringInput("Enter new name: ");
		int duration = val.getIntInput("Enter new duration: ");
		double fee = val.getDoubleInput("Enter new fee: ");
		MembershipPlan plan = new MembershipPlan(planId, name, duration, fee);
		memberPlanDAO.updatePlan(plan);
	}

	public void deletePlan() {
		int memberId = val.getIntInput("Enter plan ID to delete: ");
		if (!isValidPlanId(memberId)) {
			System.out.println("Plan ID not found. Please enter a valid ID.");
			return;
		}
		memberPlanDAO.deletePlan(memberId);
	}
	
	public void getReport() {
		memberPlanDAO.viewFullReport();
	}
}
