package gym;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Gym gym=new Gym();
		sampleData(gym);

		
//		Member a=new Member("Sai", 45);
//		MemberShipPlan.PlanDetails pd=MemberShipPlan.PlanDetails.GOLD;
//		MemberShipPlan mp=new MemberShipPlan(pd,5);
//		a.addPlan(mp);
//		
//		
//		gym.addMember(a);
//		gym.printDetails();
//		
//		a=new Member("Manoj", 22);
//		 pd=MemberShipPlan.PlanDetails.PREMINUM;
//		mp=new MemberShipPlan(pd,30);
//		a.addPlan(mp);
		
//		
//		gym.addMember(a);
//		gym.printDetails();
	Scanner sc=new Scanner(System.in);
		
		do {
			System.out.println("1.Create new user \n2.remove an user\n3.Print Details");
			
			System.out.println("Please select ");
			int ch=sc.nextInt();
			switch(ch)
			{
			case 1->createUser(gym);
			case 2->deleteUser(gym);
			case 3->gym.printDetails();
			case 4->System.exit(0);
			default-> System.out.println("Invalid input please select from options");
	
			
			};
		}while(true);
		
		
		
		
	}
	public static void deleteUser(Gym gym)
	{
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter memberId:");
		long id=sc.nextLong();
		gym.deleteUser(id);
		
	}
	public static void createUser(Gym gym)
	{
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter your Name");
		String name=sc.next();
		System.out.println("Enter your Age");
		int age=sc.nextInt();
		Member member=new Member(name,age);
		System.out.println("Select Plan");
		ArrayList<PlanDetails> details=new ArrayList<>( List.of(PlanDetails.values()));
		int count=1;
		for(PlanDetails p: details)
		{
			System.out.println(count++ + ". "+ p.name());
			
		}
		int opiton= sc.nextInt();
		System.out.println("Enter the duration");
		int duration=sc.nextInt();
		member.addPlan(details.get(opiton-1),duration);
		gym.addMember(member);
		
	}
	public static void sampleData(Gym gym)
	{
		Member member=new Member("mani",20);
		member.addPlan(PlanDetails.BASIC, 9);
		gym.addMember(member);
		member=new Member("Ram",22);
		member.addPlan(PlanDetails.GOLD, 6);
		gym.addMember(member);
		member=new Member("sai",21);
		member.addPlan(PlanDetails.PREMINUM, 12);
		gym.addMember(member);
		member=new Member("Rakesh",19);
		member.addPlan(PlanDetails.BASIC, 7);
		gym.addMember(member);
		member=new Member("rakesh ram",19);
		member.addPlan(PlanDetails.BASIC, 7);
		gym.addMember(member);
		member=new Member("Bhanu",19);
		member.addPlan(PlanDetails.BASIC, 7);
		gym.addMember(member);
		System.out.println("Sample");
		
		
		
	}

}
