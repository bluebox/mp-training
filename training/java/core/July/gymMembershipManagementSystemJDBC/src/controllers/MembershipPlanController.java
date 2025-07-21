package controllers;

import java.util.List;
import java.util.Scanner;

import models.MembershipPlan;
import services.MembershipPlanService;

public class MembershipPlanController {
	private final MembershipPlanService planService;
//	private final Scanner sc;

	public MembershipPlanController(MembershipPlanService planService, Scanner sc) {
		this.planService = planService;
//		this.sc = sc;
	}

	public void getAllMembers() {

		List<MembershipPlan> plans = planService.getAllPlans();
		if (plans.size() == 0) {
			System.out.println("No members here!");
		}
		plans.forEach(System.out::println);

	}

}
