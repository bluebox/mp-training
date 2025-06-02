package CaseStudy1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Gym myGym = new Gym();
		long adminPassword = 987654321L;
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Fit-Buddy Gym");
		while (true) {
			try {
				System.out.println("\n-------- Menu----------");
				System.out.println("Please enter an option from below");
				System.out.println("1. Available Plans");
				System.out.println("2. Admin Access");
				System.out.println("3. Join Gym");
				System.out.println("4. Quit");
				int option = sc.nextInt();
				if (option == 4)
					break;
				switch (option) {
					case 1:
						System.out.println("\n--- Basic Plan ---");
						System.out.println("Duration: 1 Month");
						System.out.println("Access: Gym floor only");
						System.out.println("Price: ₹999");
						System.out.println("\n--- Premium Plan ---");
						System.out.println("Duration: 3 Months");
						System.out.println("Access: Gym floor + Group Classes");
						System.out.println("Price: ₹2499");
						System.out.println("\n--- Gold Plan ---");
						System.out.println("Duration: 6 Months");
						System.out.println("Access: All facilities including Gym, Classes, Sauna");
						System.out.println("Price: ₹4499");
						break;
					case 3:
						System.out.println("Please enter your name:");
						String name = sc.next();
						System.out.println("Please enter your age:");
						int age = sc.nextInt();
						System.out.println("Please enter your Phone Number:");
						long phone = sc.nextLong();
						System.out.println("Please enter plan you want to choose:");
						String selectedPlan = sc.next();
						MembershipPlan plan;
						while (true) {
							switch (selectedPlan) {
								case "Basic":
									plan = new MembershipPlan("Basic", 1, 999);
									break;
								case "Premium":
									plan = new MembershipPlan("Premium", 3, 2499);
									break;
								case "Gold":
									plan = new MembershipPlan("Gold", 6, 4299);
									break;
								default:
									System.out.println("Please enter a valid plan (Basic, Premium, Gold):");
									selectedPlan = sc.next();
									continue;
							}
							myGym.addMember(new Member(name, age, phone, plan));
							System.out.println("Added successfully");
							break;
						}
						break;

					case 2:
						System.out.println("Please enter admin password:");
						long enteredPassword = sc.nextLong();
						sc.nextLine(); 
						if (enteredPassword == adminPassword) {
							while (true) {
								System.out.println("Please enter an option from below");
								System.out.println("1. View member's details");
								System.out.println("2. Delete member");
								System.out.println("3. Update member age");
								System.out.println("4. Back");
								int adminOption = sc.nextInt();
								sc.nextLine();
								if (adminOption == 4)
									break;
								switch (adminOption) {
									case 1:
										System.out.println("Gym Members list ");
										System.out.println("----------------- ");
										myGym.getAllMembers();
										break;
									case 2:
										System.out.println("Enter phone number of the member to delete: ");
										long phoneNum = sc.nextLong();
										myGym.deleteMemberById(phoneNum);
										break;
									case 3:
										System.out.println("Enter phone number of the member to update age: ");
										long ageId = sc.nextLong();
										System.out.println("Enter new age:");
										int updatedAge = sc.nextInt();
										myGym.updateAge(ageId, updatedAge);
										break;
									default:
										System.out.println("Enter valid option");
								}
							}
						} else {
							System.out.println("Incorrect admin password.");
						}
						break;
					default:
						System.out.println("Enter a valid option.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input type. Please enter the expected format ");
				sc.nextLine();
			} 
		}
		System.out.println("Thank You for visiting our Gym and Feel free to reach out.");
		sc.close();
	}
}
