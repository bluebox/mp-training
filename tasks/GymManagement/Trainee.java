package gymManagement;

import java.util.ArrayList;

public class Trainee extends Person {
	private static int counterId=1;
	private final int memberId;
	private MembershipPlan membershipPlan;
	private ArrayList<ProgressRecord> history;
	public Trainee(String name, int age) {
		super(name, age);
		// TODO Auto-generated constructor stub
		this.memberId=counterId++;
		this.history=new ArrayList<>();
	}
	public int getMemberId()
	{
		return memberId;
	}
	public MembershipPlan getMembershipPlan()
	{
		return this.membershipPlan;
	}
	public void assignPlan(MembershipPlan m)
	{
		this.membershipPlan=m;
	}
	public void addProgress(double weight,String date)
	{
		history.add(new ProgressRecord(weight,date));
	}

	public void showProgressHistory() {
		System.out.println("History for "+getName()+" :");
		for(ProgressRecord record:history)
		{
			System.out.println(record.displayRecord());
		}
	}
	@Override
	public void showDetails() {
		// TODO Auto-generated method stub
		System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        if (membershipPlan != null) {
            membershipPlan.displayPlanDetails();
        } else {
            System.out.println("No membership plan assigned.");
        }
	}
	
}
