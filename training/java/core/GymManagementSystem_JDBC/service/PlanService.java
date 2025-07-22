package GymManagementSystem.service;

import java.util.List;

import GymManagementSystem.models.MembershipPlan;

public interface PlanService {

	void addPlan(MembershipPlan plan);
	void updatePlan(MembershipPlan plan);
	void deletePlan(int memberId);
	List<MembershipPlan> viewPlans();
}
