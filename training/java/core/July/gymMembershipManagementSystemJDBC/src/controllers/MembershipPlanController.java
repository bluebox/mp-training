package controllers;

import java.util.List;

import models.MembershipPlan;
import services.MembershipPlanService;
import services.implimentations.MembershipPlanServiceImpl;

public class MembershipPlanController {

	private final MembershipPlanService planService = new MembershipPlanServiceImpl();

	public void getAllMembers() {

		List<MembershipPlan> plans = planService.getAllPlans();
		if (plans.size() == 0) {
			System.out.println("No members here!");
		}
		plans.forEach(System.out::println);

	}

}
