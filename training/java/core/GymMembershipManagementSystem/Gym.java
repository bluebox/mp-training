import java.util.*;
public class Gym {
	 ArrayList<Member> members=new ArrayList<>();
	 ArrayList<MembershipPlan> plans=new ArrayList<>();
	 
	 public Gym()
	 {
		 plans.add(new MembershipPlan("Basic",1,500));
		 plans.add(new MembershipPlan("Premium",3,1200));
		 plans.add(new MembershipPlan("Gold",6,2000));
		 
	 }
	 public void addMember(String id,String name,int age)
	 {
		 members.add(new Member(id,name,age));
		 System.out.println("member is added");
	 }
	public void assignPlan(String id,int planIndex)
	 {
		 for(Member m:members)
		 {
			 if(m.getMemberId().equals(id))
			 {
				 if(planIndex>=0 && planIndex<plans.size())
				 {
					 m.assignPlan(plans.get(planIndex));
					 System.out.println("plan assigned");
				 }
				 else
				 {
					 System.out.println("Invalid plan");
				 }
			 }
		 }
		 
	 }
	 public void showAllMembers()
	 {
		 if(members.isEmpty())
			 System.out.println("No members found");
		 else
		 {
			 for(Member m:members)
			 {
				 m.showDetails();
			 }
		 }
	 }
	 public void showPlans()
	 {
		 for(int i=0;i<plans.size();i++)
		 {
			 System.out.println(i+":");
			 plans.get(i).showPlanDetails();
		 }
	 }
	

}
