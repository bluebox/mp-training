package gymManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GymManagement {
	private String nameOfTheGym;
	private List<Member> gymMembers;
	private List<MemberShipPlan> gymPlans;
	Scanner sc;
	
	{
		gymMembers = new ArrayList<>();
		gymPlans = new ArrayList<>();
		gymPlans.add(new BasicMemberShip());
		gymPlans.add(new GoldMemberShip());
		gymPlans.add(new PremiumMemberShip());
		sc = new Scanner(System.in);
		
	}
	private MemberShipPlan askMemberToSelectMemberShip() {
		System.out.println("--------------------");
		System.out.println("Memu: (press corresponding number to choose a plan");
		System.out.println("1 . Premium Membership "+new PremiumMemberShip().toString());
		System.out.println("2 . Gold Membership "+new GoldMemberShip().toString());
		System.out.println("3 . Basic  Membership "+new BasicMemberShip().toString());
		System.out.println("any key to skip plan for now");
		System.out.println("--------------------");
		Integer input = 0;
		try {
			input = sc.nextInt(); // numberFormatException
		}catch(Exception e) {
			System.out.println("Wrong or Invalid Input pls try again !!");
		}
		// integration of payment transactions (yet to Implement)
		switch(input) {
		case 1:
			return new PremiumMemberShip();
		case 2:
			return new GoldMemberShip();
		case 3:
			return new BasicMemberShip();
		default:
			return null;

		}
	}
	public Member createNewMember(String name,int age) {
		Member newMember = new Member(name,age);
		newMember.setMemberShip(askMemberToSelectMemberShip());
		gymMembers.add(newMember);
		return newMember;
	}
	public void removeMemberById(int id) {
		try {
			for (Member m : gymMembers) {
				if (m.getMemberId() == id) {
					gymMembers.remove(m);
					return ;
				}
			}
			throw new Exception();
		}catch(Exception e){
			System.out.println("ID not Found Error");
		}
	}
	public void DisplayMembers() {
		System.out.println("Members of GYM :");
		if(gymMembers.size() ==0){
			System.out.println("empty");
			return;
		}
		for(Member m : gymMembers) {
			System.out.println(m);
		}
		System.out.println();
	}
	public void displayFunctionalities() {
		System.out.println("--------------------");
		System.out.println("Memu: (press corresponding number to choose a functionalities");
		System.out.println("Press 1 to create new Member to Gym");
		System.out.println("Press 2 to remove member by Id");
		System.out.println("Press 3 to display all the Gym members");
		System.out.println("Press any key to exit the application");
		System.out.println("--------------------");
		
		int input = 0;
		
		try {
			do {
				input = sc.nextInt(); // numberFormatException
				switch(input) {
					case 1:
						System.out.println("Enter Name and Age of the Member respectively !!");
						String name = sc.next();
						int age = sc.nextInt();
						createNewMember(name,age);
						System.out.println("Successfully Created Member, view above menu for functionalities");
						break;
					case 2:
						System.out.println("Enter ID of the Member to remove !!");
						int idx = sc.nextInt();
						removeMemberById(idx);
						System.out.println("Successfully removed Member, view above menu for functionalities");
						break;
					case 3:
						DisplayMembers();
						break;
					default:
						System.out.println("application is closing !!");	
				}
			}while(input > 0 && input < 4);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Wrong or Invalid Input pls try again !!");
		}
		
	}
	public void greet() {
		System.out.println("Welcome !!");
		System.out.println("Functionalities of Application :");
		displayFunctionalities();
	}

	
}
