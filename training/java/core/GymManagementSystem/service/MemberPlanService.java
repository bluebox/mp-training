package GymManagementSystem.service;

import java.time.LocalDate;
import java.util.List;

import GymManagementSystem.DAO.MemberPlanDAO;
import GymManagementSystem.models.Member;
import GymManagementSystem.models.MemberPlan;

public class MemberPlanService {
	private MemberPlanDAO memberPlanDAO;
	private InputValidation val;

	public MemberPlanService() {
		memberPlanDAO = new MemberPlanDAO();
		val = new InputValidation();
	}

	public void mapMemberToPlan() {
		int memberId = val.getIntInput("Enter member ID: ");
		if (!val.isValidMemberId(memberId)) {
	        System.out.println("Member ID not found. Please enter a valid ID.");
	        return;
	    }
		int planId = val.getIntInput("Enter plan ID: ");
		if (!val.isValidPlanId(planId)) {
	        System.out.println("Plan ID not found. Please enter a valid ID.");
	        return;
	    }
	    LocalDate startDate = val.getDateInput("Enter start date (yyyy-mm-dd): ");
	    MemberPlan mapping = new MemberPlan(memberId, planId, startDate);
	    memberPlanDAO.assignPlan(mapping);
	}

	public void updateMembership() {
		int memberId = val.getIntInput("Enter member ID to update: ");
		if (!val.isValidMemberPlanId(memberId)) {
			System.out.println("Member ID not found. Please enter a valid ID.");
			return;
		}
		int planId = val.getIntInput("Enter new plan Id: ");
		LocalDate date = val.getDateInput("Enter new date: ");
		MemberPlan plan = new MemberPlan(memberId, planId, date);
		memberPlanDAO.updateMembership(plan);
	}

	public void deleteMembership() {
		int memberId = val.getIntInput("Enter member ID to delete: ");
		if (!val.isValidMemberPlanId(memberId)) {
			System.out.println("Member ID not found. Please enter a valid ID.");
			return;
		}
		memberPlanDAO.deleteMembership(memberId);
	}
	
	public void viewActiveMembers() {
		List<Member> activeMembers = memberPlanDAO.getActiveMembers();
		if (activeMembers.isEmpty()) {
			System.out.println("No active members.");
		} else {
			activeMembers.forEach(System.out::println);
		}
	}
	
	public void getReport() {
		memberPlanDAO.viewFullReport();
	}
	
	public void exportReport() {
		memberPlanDAO.ExportFullReport();
	}
}
