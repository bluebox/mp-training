//import java.util.ArrayList;
//import java.util.List;
package GymManagement;

import java.util.ArrayList;
import java.util.List;
public class Gym {
     private List<Member>members;
     private List<MembershipPlan>plans;
     public Gym()
     {
    	 this.members=new ArrayList<>();
    	 this.plans=new ArrayList<>();
    	 plans.add(new MembershipPlan("Basic",1,300.00));
    	 plans.add(new MembershipPlan("Premium",6,600.00));
    	 plans.add(new MembershipPlan("Gold",12,900.00));
     }

	public void addMember(String name,int age) {
    	 Member newMember=new Member(name,age);
    	 members.add(newMember);
    	 System.out.println("new member added successfully : "+newMember.getMemberId());
     }
     public void assignPlanToMember(int memberId, String planName)
     {
    	 try {
    		 Member member= findMemberById(memberId);
    		 if(member==null)
    		 {
    			 throw new IllegalArgumentException("Member id not found");
    		 }
    		 MembershipPlan plan=findPlanByName(planName);
    		 if(plan==null)
    		 {
    			 throw new IllegalArgumentException("plan not found");
    		 }
    		 member.setAssignerPlan(plan);
    		 System.out.println("plan "+plan.getPlanName()+" assigned to "+member.getName());
    	 }catch(IllegalArgumentException e)
    	 {
    		 System.err.println("Error : "+e.getMessage());
    	 }
     }

	public Member findMemberById(int memberId)
	{
		for(Member member:members) {
			if(member.getMemberId()==memberId)
			{
				return member;
			}
		}
		return null;
	}
	public MembershipPlan findPlanByName(String planName) {
    	 for(MembershipPlan plan:plans) {
    		 if(plan.getPlanName().equalsIgnoreCase(planName))
    		 {
    			 return plan;
    		 }
    	 }
    	 return null;
     }
     public void viewAllMembers()
     {
    	 if(members.isEmpty()) {
    		 System.out.println("no members registred yet");
    		 return;
    	 }
    	 System.out.println("all members are");
    	 for(Member member:members) {
    		 member.showDetails();
    	 }
    	 System.out.println();
     }
     public void viewAllPlans()
     {
    	 System.out.println("available plans");
    	 for(MembershipPlan plan:plans)
    	 {
    		 System.out.println("-"+plan);
    	 }
    	 System.out.println();
     }
}
