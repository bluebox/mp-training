package GymManagementSystem.service.Impl;

import java.util.List;

import GymManagementSystem.DAO.MemberPlanDAO;
import GymManagementSystem.DAO.Impl.MemberPlanDAOImpl;
import GymManagementSystem.models.Member;
import GymManagementSystem.models.MemberPlan;
import GymManagementSystem.service.MemberPlanService;

public class MemberPlanServiceImpl implements MemberPlanService{
	private MemberPlanDAO memberPlanDAO = new MemberPlanDAOImpl();

	@Override
	public void mapMemberToPlan(MemberPlan mapping) {
	    memberPlanDAO.assignPlan(mapping);
	}

	@Override
	public void updateMembership(MemberPlan plan) {
		memberPlanDAO.updateMembership(plan);
	}
	
	@Override
	public void deleteMembership(int memberId) {
		memberPlanDAO.deleteMembership(memberId);
	}
	
	@Override
	public List<MemberPlan> viewMemberships() {
		return memberPlanDAO.getAllMemberships();
	}
	
	@Override
	public List<Member> viewActiveMembers() {
		return memberPlanDAO.getActiveMembers();
	}
	
	@Override
	public void getReport() {
		memberPlanDAO.viewFullReport();
	}
	
	public void exportReport() {
		memberPlanDAO.ExportFullReport();
	}
}
