package controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import models.Member;
import models.MembershipPlans;
import models.PersonMembership;
import serviceImpl.MemberMebershipPlanImpl;
import serviceImpl.MemberPlanServiceImpl;
import serviceImpl.MemberServiceImpl;
import services.MemberMembershipService;
import services.MemberService;
import services.MemberShipPlanService;

public class MembershipController {

	private final MemberMembershipService membershipService = new MemberMebershipPlanImpl();
	private final MemberShipPlanService planService = new MemberPlanServiceImpl();
	private final MemberService memberService = new MemberServiceImpl();
	private final Scanner sc;

	public MembershipController(Scanner sc) {
		this.sc = sc;
	}

	public void assignPlan() {
		System.out.print("Enter Member ID to Add plan: ");
		int personId = Integer.parseInt(sc.nextLine());

		Member personDetails = memberService.getMemberById(personId);
		if (personDetails == null) {
			System.out.println("Member with ID " + personId + " not found.");
			return;
		}
		List<MembershipPlans> membershipPlans = planService.getAllPlans();
		membershipPlans.forEach(System.out::println);

		System.out.println("Enter plan id to assign");
		int planId = Integer.parseInt(sc.nextLine());

		MembershipPlans assignedPlan = planService.getPlanById(planId);

		if (assignedPlan == null) {
			System.out.println("Plan with ID " + planId + " not found");
			return;
		}

		PersonMembership membership = new PersonMembership(personId, planId, LocalDate.now(), LocalDate.now(),
				LocalDate.now().plusMonths(assignedPlan.getDurationMonths()));
		membershipService.addMembership(membership);
	}

	public void getAllMemberships() {

		List<PersonMembership> memberships = membershipService.getAllMemberships();
		if (memberships.size() == 0) {
			System.out.println("No Memberships here!");
		}
		memberships.forEach(System.out::println);

	}

	public void getActiveMemberships() {

		List<PersonMembership> activeMemberships = membershipService.getActiveMemberships();
		if (activeMemberships.size() == 0) {
			System.out.println("No Memberships here!");
		}
		activeMemberships.forEach(System.out::println);

	}

	public void deleteMembership() {

		System.out.print("Enter Member ID to Delete Membership: ");
		int MembershipId = Integer.parseInt(sc.nextLine());

		PersonMembership deleteMembership = membershipService.getMembershipById(MembershipId);

		if (deleteMembership == null) {
			System.out.println("Membership with ID " + MembershipId + " not found.");
			return;
		}

		membershipService.deleteMembership(MembershipId);
		System.out.println("Membership deleted successfully");
	}

}
