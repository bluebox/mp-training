package services.implimentations;

import java.util.List;

import dao.MembershipPlanDao;
import dao.implimentions.MembershipPlanDaoImpl;
import models.MembershipPlan;
import services.MembershipPlanService;
import utils.CSVExporter;

public class MembershipPlanServiceImpl implements MembershipPlanService {

	private final MembershipPlanDao planDao = new MembershipPlanDaoImpl();

	@Override
	public void addPlan(MembershipPlan plan) {
		planDao.addPlan(plan);
	}

	@Override
	public MembershipPlan getPlanById(int id) {
		return planDao.getPlanById(id);
	}

	@Override
	public List<MembershipPlan> getAllPlans() {
		return planDao.getAllPlans();
	}

	@Override
	public void updatePlan(MembershipPlan plan) {
		planDao.updatePlan(plan);
	}

	@Override
	public void deletePlan(int id) {
		planDao.deletePlan(id);
	}

	@Override
	public void exportPlans() {
		List<MembershipPlan> members = planDao.getAllPlans();

		String[] headers = new String[] { "Id", "Plan Name", "Duration In Months", "Fee" };

		CSVExporter
				.export(members, headers,
						s -> new String[] { String.valueOf(s.getId()), s.getPlanName(),
								String.valueOf(s.getDurationMonths()), String.valueOf(s.getFee()) },
						"output/Plan Details.csv");

	}
}
