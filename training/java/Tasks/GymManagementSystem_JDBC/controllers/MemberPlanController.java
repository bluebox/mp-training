package GymManagementSystem.controllers;

import java.time.LocalDate;
import java.util.List;

import GymManagementSystem.models.Member;
import GymManagementSystem.models.MemberPlan;
import GymManagementSystem.service.MemberPlanService;
import GymManagementSystem.service.Impl.MemberPlanServiceImpl;
import GymManagementSystem.utils.InputValidation;

public class MemberPlanController {
	private MemberPlanService memberPlanService = new MemberPlanServiceImpl();
	private InputValidation val = new InputValidation();

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
	    memberPlanService.mapMemberToPlan(mapping);
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
		memberPlanService.updateMembership(plan);
	}

	public void deleteMembership() {
		int memberId = val.getIntInput("Enter member ID to delete: ");
		if (!val.isValidMemberPlanId(memberId)) {
			System.out.println("Member ID not found. Please enter a valid ID.");
			return;
		}
		memberPlanService.deleteMembership(memberId);
	}
	
	public void viewActiveMembers() {
		List<Member> activeMembers = memberPlanService.viewActiveMembers();
		if (activeMembers.isEmpty()) {
			System.out.println("No active members.");
		} else {
			activeMembers.forEach(System.out::println);
		}
	}

	public void viewMembership() {
		List<MemberPlan> activeMembers = memberPlanService.viewMemberships();
		if (activeMembers.isEmpty()) {
			System.out.println("No active members.");
		} else {
			activeMembers.forEach(System.out::println);
		}
	}
	
}
