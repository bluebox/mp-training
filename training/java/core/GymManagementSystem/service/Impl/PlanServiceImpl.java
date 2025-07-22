package GymManagementSystem.service.Impl;

import java.util.List;

import GymManagementSystem.DAO.PlanDAO;
import GymManagementSystem.DAO.Impl.PlanDAOImpl;
import GymManagementSystem.models.MembershipPlan;
import GymManagementSystem.service.PlanService;

public class PlanServiceImpl implements PlanService{
	private PlanDAO planDAO = new PlanDAOImpl();
	
	@Override
	public void addPlan(MembershipPlan plan) {
		planDAO.addPlan(plan);
	}

	@Override
	public void updatePlan(MembershipPlan plan) {
		planDAO.updatePlan(plan);
	}

	@Override
	public void deletePlan(int memberId) {
		planDAO.deletePlan(memberId);
	}
	
	@Override
	public List<MembershipPlan> viewPlans() {
		return planDAO.getAllPlans();
	}
}
