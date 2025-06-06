package com.gym;

import java.lang.invoke.StringConcatFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gym.domain.Member;
import com.gym.domain.MemberShipPlan;
import com.gym.domain.PlanDetails;
import com.gym.service.GymService;

public class Main {
	private static long idGenerator = 100;
	private final static Scanner sc = new Scanner(System.in);
	private static GymService gym = new GymService();

	public static void main(String[] args) {

		sampleData(gym);

		do {
			System.out.println();
			System.out.println("1.Create new member \n2.Update member\n3.Remove member\n"
					+ "4.Search member \n5.View all registred members\n6.Assign membership plan\n7.Exit");

			System.out.print("Please select from above options :");
			String optionInChar = sc.next();
			try {
				int option = Integer.valueOf(optionInChar);
				switch (option) {
				case 1 -> createUser(gym);
				case 2 -> updateUser(gym);
				case 3 -> deleteUser(gym);
				case 4 -> searchUser(gym);
				case 5 -> gym.printDetails();
				case 6 -> addPlan(gym);
				case 7 -> System.exit(0);
				default -> System.out.println("Invalid input, Please select from options");
				}
				;
			} catch (Exception e) {
				System.out.println("You have entered a non listed option,Please select from options");
			}
		} while (true);
	}

	public static void updateUser(GymService gym) {
		System.out.print("Enter the user id:");
		long id;
		try {
			id = sc.nextLong();
			Member member = gym.getMember(id);
			if (member == null) {
				System.out.println("User not found");
				return;
			}
			sc.nextLine();
			System.out.print("Enter new name:");
			String name= sc.nextLine();
			member.setName(name);
			
			System.out.print("Enter new age:");

			member.setAge(sc.nextInt());
			System.out.println("User updated succesfully!");
			System.out.println(member);

		} catch (Exception e) {
			System.out.println("Please enter a valid user details");
		}

	}

	public static void createUser(GymService gym) {

		System.out.print("Enter your name:");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.print("Enter your Age:");
		int age = sc.nextInt();
		Member member = new Member(idGenerator++, name, age);
		System.out.println("User created sucessfully!");
		System.out.println(member);
		System.out.println( member.getMemberShip() !=null ?member.getMemberShip():"memeber has no plan");
		gym.addMember(member);
		planAssignment(gym, member);

	}

	public static void planAssignment(GymService gym, Member member) {
		System.out.println("Do you want to take plan\n1.yes\n2.no");
		int planOption;
		try {
			planOption = sc.nextInt();
			if (planOption != 1 && planOption != 2) {
				throw new Exception("Input miss match");
			}
			if (planOption == 2) {
				return;
			}
			addPlan(gym, member);

		} catch (Exception e) {
			System.out.println("Please, enter 1 or 2 ");
			sc.nextLine();
			planAssignment(gym, member);
		}

	}

	public static void deleteUser(GymService gym) {
		System.out.println("Enter memberId:");
		long id;
		try {
			id = sc.nextLong();
			gym.deleteUser(id);
		} catch (Exception e) {
			sc.nextLine();
			System.out.println("Please enter the correct user id");

		}

	}

	public static void addPlan(GymService gym) {
		System.out.print("Enter the Id of the user:");
		long id;
		try {
			id = sc.nextLong();

		} catch (Exception e) {
			System.out.println("Please enter the correct user id");
			return;

		}
		Member member = gym.getMember(id);
		if (member == null) {
			System.out.println("User Not found");
			return;
		}
		if (member.isSubscribed()) {
			System.out.println("You already have a plan");
			return;
		}
		addPlan(gym, member);
	}

	public static void addPlan(GymService gym, Member member) {
		System.out.println("Select Plan from below plans");
		ArrayList<PlanDetails> details = new ArrayList<>(List.of(PlanDetails.values()));
		int count = 1;
		System.out.println("+-------------+----------------+-----------+");
		System.out.println("Option | Plan Name  | Price  |  Duration   |");
		System.out.println("+-------------+----------------+-----------+");

		for (PlanDetails p : details) {
			System.out.printf("%-7d| %-10s | %-6d | %d month(s) |\n", count++, p.name(), p.getPrice(), p.getDuration());
		}

		System.out.println("+------------+------------------+-----------+");
		System.out.print("Enter the plan option:");
		int opiton = sc.nextInt();
		System.out.print("Enter the duration:");
		int duration = sc.nextInt();
		System.out.println("Total amount= " + duration * details.get(opiton - 1).getPrice());
		System.out.println("Do want to take this plan\n1.yes\n2.no");
		System.out.print("Enter the option:");
		int planOption;
		while (true) {
			try {
				planOption = sc.nextInt();
				if (planOption != 1 && planOption != 2) {
					throw new Exception();
				}
				if (planOption == 2) {
					System.out.println("Plan not assigned");
					return;
				}
				MemberShipPlan plan = new MemberShipPlan(details.get(opiton - 1), duration);
				member.setMemberShip(plan);
				break;
			} catch (Exception e) {
				System.out.print("Please, enter option 1 or 2 :");
				sc.nextLine();
				// return;
			}
			// System.out.println();
		}

		System.out.println("Plan sucessfull added to the user with id " + member.getId());
		System.out.println(member);
		System.out.println( member.getMemberShip() !=null ?member.getMemberShip():"memeber has no plan");

	}

	public static void searchUser(GymService gym) {
		System.out.println("Enter the user id");
		long id;
		try {
			id = sc.nextLong();
			Member member = gym.getMember(id);
			if (member == null) {
				System.out.println("user not found");
			}

			else {
				System.out.println(member);
			
				System.out.println( member.getMemberShip() !=null ?member.getMemberShip():"memeber has no plan");
			}
		} catch (Exception e) {
			System.out.println("Enter correct user id ");
		}
	}

	public static void sampleData(GymService gym) {
		Member member = new Member(idGenerator++, "mani", 20);
		MemberShipPlan plan = new MemberShipPlan(PlanDetails.BASIC, 7);
		member.setMemberShip(plan);
		gym.addMember(member);

		member = new Member(idGenerator++, "Ram", 22);
		plan = new MemberShipPlan(PlanDetails.GOLD, 6);
		member.setMemberShip(plan);
		gym.addMember(member);

		member = new Member(idGenerator++, "sai", 21);
		plan = new MemberShipPlan(PlanDetails.PREMINUM, 12);
		member.setMemberShip(plan);
		gym.addMember(member);

		member = new Member(idGenerator++, "Rakesh", 19);
		plan = new MemberShipPlan(PlanDetails.GOLD, 7);
		member.setMemberShip(plan);
		gym.addMember(member);

		member = new Member(idGenerator++, "rakesh ram", 19);
		plan = new MemberShipPlan(PlanDetails.BASIC, 7);
		member.setMemberShip(plan);
		gym.addMember(member);

		member = new Member(idGenerator++, "Bhanu", 19);
		gym.addMember(member);

	}

}
