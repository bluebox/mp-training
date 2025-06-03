package gymManagement;

import java.util.ArrayList;

public class Trainee extends Person {
	private static int counterId=1;
	private final int memberId;
	private MembershipPlan membershipPlan;
	private ArrayList<ProgressRecord> history;
	private ArrayList<Trainer> myTrainers;
	public Trainee(String name, int age) {
		super(name, age);
		// TODO Auto-generated constructor stub
		this.memberId=counterId++;
		this.history=new ArrayList<>();
		this.myTrainers=new ArrayList<>();
	}
	
	public Trainee(String name, int age, String... list) {
		super(name, age);
		// TODO Auto-generated constructor stub
		this.memberId=counterId++;
		this.history=new ArrayList<>();
		this.myTrainers=new ArrayList<>();
		for(String trainerName:list)
		{
			for(Trainer trainer:gymManagement.Main.trainers)
			{
				if(trainer.getName().equals(trainerName))
				{
					myTrainers.add(trainer);
					trainer.assignTrainee(this);
					break;
				}
			}
		}
		System.out.println("Trainee created under given trainers list.");
	}
	public void assignTrainer(Trainer trainer)
	{
		if(!myTrainers.contains(trainer))
		{
			myTrainers.add(trainer);
		}
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
        showAssignedTrainers();
	}
	public void showAssignedTrainers() {
        System.out.println("Trainers for " + getName() + ":");
        for (Trainer t : myTrainers) {
            System.out.println("- " + t.getName());
        }
    }
	
}
