package controller;

import java.util.List;

import models.MembershipPlans;
import serviceImpl.MemberPlanServiceImpl;
import services.MemberShipPlanService;

public class MembershipPlanController {

	private final MemberShipPlanService planService = new MemberPlanServiceImpl();

	public void getAllMembers() {

		List<MembershipPlans> plans = planService.getAllPlans();
		if (plans.size() == 0) {
			System.out.println("No members here!");
		}
		plans.forEach(System.out::println);

	}

}
