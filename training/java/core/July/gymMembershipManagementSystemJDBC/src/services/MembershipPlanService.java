package services;

import java.util.List;

import models.MembershipPlan;

public interface MembershipPlanService {

	void addPlan(MembershipPlan plan);

	MembershipPlan getPlanById(int id);

	List<MembershipPlan> getAllPlans();

	void updatePlan(MembershipPlan plan);

	void deletePlan(int id);

	void exportPlans();
}