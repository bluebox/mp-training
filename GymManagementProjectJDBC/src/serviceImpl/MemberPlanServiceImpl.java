package serviceImpl;

import java.util.List;

import daoImpl.MembershipPlanDAOImpl;
import models.MembershipPlans;
import services.MemberShipPlanService;
import utils.CSVExporter;

public class MemberPlanServiceImpl implements MemberShipPlanService {

	private final MembershipPlanDAOImpl planDao = new MembershipPlanDAOImpl();

	@Override
	public void addPlan(MembershipPlans plan) {
		planDao.addPlan(plan);
	}

	@Override
	public MembershipPlans getPlanById(int id) {
		return planDao.getPlanById(id);
	}

	@Override
	public List<MembershipPlans> getAllPlans() {
		return planDao.getAllPlans();
	}

	@Override
	public void updatePlan(MembershipPlans plan) {
		planDao.updatePlan(plan);
	}

	@Override
	public void deletePlan(int id) {
		planDao.deletePlan(id);
	}

	@Override
	public void exportPlans() {
		List<MembershipPlans> members = planDao.getAllPlans();

		String[] headers = new String[] { "Id", "Plan Name", "Duration In Months", "Fee" };

		CSVExporter
				.export(members, headers,
						s -> new String[] { String.valueOf(s.getId()), s.getPlanName(),
								String.valueOf(s.getDurationMonths()), String.valueOf(s.getFee()) },
						"output/Plan Details.csv");

	}

}
