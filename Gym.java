import java.util.*;

public class Gym {
	private ArrayList<Member> members=new ArrayList<>();
	private static ArrayList<MembershipPlan> plans=new ArrayList<>();
	Scanner sc=new Scanner(System.in);
	public void addMembers(String memberId,String name,int age)
	{
		members.add(new Member(memberId,name,age));
		System.out.println("Members are added Succesfully");
	}
	static
	{
		plans.add(new MembershipPlan("Basic", 3, 3000));
        plans.add(new MembershipPlan("Premium", 6, 5800));
        plans.add(new MembershipPlan("Gold", 12, 10800));
	}
	public void assignPlanToMember(String id,String planName)
	{
		for(Member m:members)
		{
			if(m.getMemberId().equals(id))
			{
				for(MembershipPlan p:plans)
				{
					if(p.getplanName().equalsIgnoreCase(planName))
					{
						m.assignPlan(p);
						System.out.println("Plan assigned succesfully");
						return;
					}
				}
				System.out.println("Plan doesn't exists");
	            return;	
			}
			System.out.println("Member is not found with that id "+id);	
		}
	}
	public void showPlans()
	{
		for(int i=0;i<plans.size();i++)
		{
			plans.get(i).showPlanDetails();
		}
	}
	public void viewMembers()
	{
		if(members.isEmpty())
		{
			System.out.println("No members registered");
		}
		else
		{
			for(Member m:members)
			{
				m.showDetails();
				System.out.println("");
				
			}
		}
	}
}