package gym.membership_Management;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Gym myGym=new Gym();
		System.out.println("Hello, Welcome to our Gym Membership Management System.");
		System.out.println();
		int option;
		do {
			System.out.println("-------------------------------------------MENU-----------------------------------------------");
			System.out.println("\t\t1. View Membership Plans");
			System.out.println("\t\t2. Register a new Member");
			System.out.println("\t\t3. Show details of Gym Members");
			System.out.println("\t\t4. Remove a Gym Member");
			System.out.println("\t\t5. Exit");
			System.out.println("\tPlease Enter Your Choice");
			option=sc.nextInt();
			
			switch(option) {
			case 1:myGym.plansAvailable();
			break;
			case 2:
				System.out.println("Enter your Full Name: ");
				String name=sc.next();
				System.out.println("Enter your age: ");
				int age=sc.nextInt();
				System.out.println("Enter plan name(Basic/Gold/Premium)");
				String planName=sc.next();
				MembershipPlan plan=myGym.getPlanByName(planName);
				if(plan!=null) {
					Member m=new Member(name,age,plan);
					myGym.addMember(m);
					System.out.println("Registration Successfull");
				}
				else {
					System.out.println("Entered plan is invalid. Please register again");
				}
				break;
			case 3:myGym.registeredMembers();
				break;
			case 4:
				System.out.println("Enter Member Id to remove");
				int id=sc.nextInt();
				myGym.exitGymMember(id);
				break;
			case 5:
				System.out.println("----------------Thank You!!!-----------------");
				break;
			default:System.out.println("You entered an invalid option. Please try again.");
			}
		}while(option!=5);
		
		
		sc.close();
	}

}
