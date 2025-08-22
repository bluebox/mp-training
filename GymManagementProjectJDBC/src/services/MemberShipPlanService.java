package services;

import java.util.List;

import models.MembershipPlans;

public interface MemberShipPlanService {

	void addPlan(MembershipPlans plan);

	MembershipPlans getPlanById(int id);

	List<MembershipPlans> getAllPlans();

	void updatePlan(MembershipPlans plan);

	void deletePlan(int id);

	void exportPlans();
}