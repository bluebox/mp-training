package dao;

import java.util.List;

import models.MembershipPlans;

public interface MembershipPlanDAO {

	void addPlan(MembershipPlans plan);

	MembershipPlans getPlanById(int id);

	List<MembershipPlans> getAllPlans();

	void updatePlan(MembershipPlans plan);

	void deletePlan(int id);
}