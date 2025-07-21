package GymManagementSystem.service;

import java.util.List;

import GymManagementSystem.DAO.PlanDAO;
import GymManagementSystem.models.MembershipPlan;

public class PlanService {
	private PlanDAO planDAO;
	private InputValidation val;

	public PlanService() {
		planDAO = new PlanDAO();
		val = new InputValidation();
	}
	
	public void addPlan() {
		String planName = val.getStringInput("Enter plan name: ");
		int duration = val.getIntInput("Enter duration in months: ");
		double price = val.getDoubleInput(("Enter price: "));
		MembershipPlan plan = new MembershipPlan(planName, duration, price);
		planDAO.addPlan(plan);
	}

	public void updatePlan() {
		int planId = val.getIntInput("Enter plan ID to update: ");
		if (!val.isValidPlanId(planId)) {
			System.out.println("Plan ID not found. Please enter a valid ID.");
			return;
		}
		String name = val.getStringInput("Enter new name: ");
		int duration = val.getIntInput("Enter new duration: ");
		double fee = val.getDoubleInput("Enter new fee: ");
		MembershipPlan plan = new MembershipPlan(planId, name, duration, fee);
		planDAO.updatePlan(plan);
	}

	public void deletePlan() {
		int memberId = val.getIntInput("Enter plan ID to delete: ");
		if (!val.isValidPlanId(memberId)) {
			System.out.println("Plan ID not found. Please enter a valid ID.");
			return;
		}
		planDAO.deletePlan(memberId);
	}
	
	public void viewPlans() {
		List<MembershipPlan> plans = planDAO.getAllPlans();
		if (plans.isEmpty()) {
			System.out.println("No plans found.");
		} else {
			plans.forEach(System.out::println);
		}
	}
}
