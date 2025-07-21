package dao;

import java.util.List;

import models.MembershipPlan;

public interface MembershipPlanDao {

	void addPlan(MembershipPlan plan);

	MembershipPlan getPlanById(int id);

	List<MembershipPlan> getAllPlans();

	void updatePlan(MembershipPlan plan);

	void deletePlan(int id);
}