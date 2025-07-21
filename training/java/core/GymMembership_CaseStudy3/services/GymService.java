package services;

import java.sql.SQLException;
import java.util.List;

import dao.MemberDao;
import dao.MemberPlanDao;
import dao.PlanDao;
import model.Member;
import model.MembershipPlan;

public class GymService {

	MemberDao memberDao=new MemberDao();
	PlanDao planDao=new PlanDao();
	MemberPlanDao memberplanDao=new MemberPlanDao();
	
	public void addMember(String id,String name,int age) throws SQLException
	{
		if(memberDao.memberExists(id))
		{
			System.out.println("Member already exists");
			return;
		}
	   memberDao.insertMember(new Member(id,name,age));
	   System.out.println("Memebr added");
	}
	
	
	 public List<MembershipPlan> getAllPlans() throws SQLException {
	        return planDao.getAllPlans();
	    }
	 
	 public boolean isMemberPresent(String id) throws SQLException {
	        return memberDao.memberExists(id);
	    }
	 
	 public void assignPlan(String memberId,int planIndex) throws SQLException
	 {
		 List<MembershipPlan> plans=planDao.getAllPlans();
		 
		 if(planIndex>=0 && planIndex<plans.size())
		 {
			 MembershipPlan plan=plans.get(planIndex);
			 memberplanDao.assignPlan(memberId,plan.getPlanId(),plan.getPlanName());
			 System.out.println("Plan Assigned");
			 
		 }
		 else
		 {
			 System.out.println("please enter correct plan index");
		 }
	 }
	 
	 public List<Member> getAllMembers() throws SQLException
	 {
		 return memberDao.getAllMembers();
	 }
	 
	 public void showMemberDetails(Member member) throws SQLException {
		 System.out.println("Memeber ID: "+member.getMemberId());
		 System.out.println("Memeber name: "+member.getName());
		 System.out.println("Memeber Age: "+member.getAge());
	        List<String> plans=memberplanDao.getPlansForMember(member.getMemberId());
	        if (plans.isEmpty()) 
	        {
	            System.out.println("No Plan Assigned To This Member.");
	        } else {
	            System.out.println("Assigned Plan:");
	            for (String p : plans) {
	                System.out.println(" - " + p);
	            }
	        }
	        System.out.println();
	 }

	 public void updateplan(String memberId,int newplanIndex) throws SQLException
	 {
		 List<MembershipPlan> plans=planDao.getAllPlans();
		 if(newplanIndex>=0 && newplanIndex<plans.size())
		 {
			 MembershipPlan newPlan=plans.get(newplanIndex);
			 memberplanDao.updatePlan(memberId, newPlan.getPlanId(),newPlan.getPlanName());
		 }
		 else
		 {
			 System.out.println("Invalid plan index");
		 }
		 
	 }


	
}
