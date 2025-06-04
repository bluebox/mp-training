package gym;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Gym gym = new Gym();
		sampleData(gym);

		do {
			System.out.println();
			System.out.println("1.Create new user \n2.Update user\n3.remove an user\n4"
					+ ".search user \n5.Print Details\n6.add Plan\n7.exit");

			System.out.println("Please select ");
			String sch = sc.next();
			try {
				int ch = Integer.valueOf(sch);
				switch (ch) {
				case 1 -> createUser(gym);
				case 2-> updateUser(gym);
				case 3 -> deleteUser(gym);
				case 4->searchUser(gym);
				case 5 -> gym.printDetails();
				case 6 -> addPlan(gym);
				case 7 -> System.exit(0);
				default -> System.out.println("Invalid input please select from options");
				}
				;
			} catch (Exception e) {
				System.out.println("Please Enter a number " + e);
			}
		} while (true);
	}
	public static void updateUser(Gym gym)
	{
		System.out.println("Enter the user id");
		long id;
		try {
			id=sc.nextLong();
			Member member=gym.getMember(id);
			if(member==null)
			{
				System.out.println("User Not found");
				return;
			}
			System.out.println("Enter new name");
			member.setName(sc.nextLine());
			sc.nextLine();
			System.out.println("Enter new age");
			
			member.setAge(sc.nextInt());
			System.out.println("User updated succesfully");
			
		}catch(Exception e){
			System.out.println("Enter correct details "+e);
		}
		
		
	}
	
	public static void createUser(Gym gym) {
		System.out.println("Enter your Name");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.println("Enter your Age");
		int age = sc.nextInt();
		Member member = new Member(name, age);
		System.out.println("User Created Sucessfully");
		System.out.println("Do want to take plan\n1.yes\n2.no");
		gym.addMember(member);
		int planOption;
		try {
			planOption = sc.nextInt();
			if (planOption != 1 && planOption != 2) {
				throw new Exception("Input miss match");
			}

		} catch (Exception e) {
			System.out.println("enter 1 or 2 " + e);
			return;
		}

		if (planOption == 2) {
			return;
		}
		addPlan(gym, member);
	}

	public static void deleteUser(Gym gym) {
		System.out.println("Enter memberId:");
		long id;
		try {
			id= sc.nextLong();
			gym.deleteUser(id);
			
			
		}catch(Exception e){
			System.out.println("Please Enter id "+e);
			
		}
		

	}

	public static void addPlan(Gym gym) {
		System.out.println("Enter the Id of the user");
		long id;
		try {
			id= sc.nextLong();
			
		}catch(Exception e){
			System.out.println("Please Enter id "+e);
			return;
			
		}
		Member member = gym.getMember(id);
		if (member == null) {
			System.out.println("User Not found");
			return;
		}
		if (member.isSubscribed) {
			System.out.println("You Already have plan");
			return;
		}
		addPlan(gym, member);
	}

	public static void addPlan(Gym gym, Member member) {
		System.out.println("Select Plan");
		ArrayList<PlanDetails> details = new ArrayList<>(List.of(PlanDetails.values()));
		int count = 1;
		for (PlanDetails p : details) {
			System.out.println(count++ + ". " + p.name()+"  price-> "+p.price);

		}
		int opiton = sc.nextInt();
		System.out.println("Enter the duration");
		int duration = sc.nextInt();
		member.addPlan(details.get(opiton - 1), duration);
		System.out.println("Plan sucessfull added to the user with id "+member.getMemberId());
		
	}
	public static void searchUser(Gym gym)
	{
		System.out.println("Enter the user id");
		long id;
		try {
			id=sc.nextLong();
			gym.getMember(id).getDetails();
		}catch(Exception e)
		{
			System.out.println("Enter correct user id "+e);
		}
	}
	
	public static void sampleData(Gym gym) {
		Member member = new Member("mani", 20);
		member.addPlan(PlanDetails.BASIC, 9);
		gym.addMember(member);
		member = new Member("Ram", 22);
		member.addPlan(PlanDetails.GOLD, 6);
		gym.addMember(member);
		member = new Member("sai", 21);
		member.addPlan(PlanDetails.PREMINUM, 12);
		gym.addMember(member);

		member = new Member("Rakesh", 19);
		member.addPlan(PlanDetails.BASIC, 7);
		gym.addMember(member);
		member = new Member("rakesh ram", 19);
		member.addPlan(PlanDetails.BASIC, 7);
		gym.addMember(member);
		member = new Member("Bhanu", 19);
		gym.addMember(member);

	}

}
