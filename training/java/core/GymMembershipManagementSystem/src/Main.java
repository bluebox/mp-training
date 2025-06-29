import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Gym gym = new Gym();
		boolean exit = false;
		
		while(!exit) {
			System.out.println("\n--- Gym Membership Management System ---");
            System.out.println("1. Add New Member");
            System.out.println("2. Assign Membership Plan to Member");
            System.out.println("3. View All Members and Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
		
		try {
			int choice = Integer.parseInt(sc.nextLine());
			
			switch (choice) {
            case 1:
                System.out.print("Enter Member ID: ");
                String memberId = sc.nextLine();
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Age: ");
                int age = Integer.parseInt(sc.nextLine());

                if (age <= 0) {
                    System.out.println("Age must be positive.");
                    break;
                }

                Member newMember = new Member(memberId, name, age);
                gym.addMember(newMember);
                break;

            case 2:
                System.out.print("Enter Member ID to assign plan: ");
                String idForPlan = sc.nextLine();
                System.out.println("Available Plans:");
                gym.showAvailablePlans();
                System.out.print("Select plan number: ");
                int planChoice = Integer.parseInt(sc.nextLine());
                gym.assignPlanToMember(idForPlan, planChoice);
                break;

            case 3:
                gym.viewAllMembers();
                break;

            case 4:
                exit = true;
                System.out.println("Exiting system. Goodbye!");
                break;

            default:
                System.out.println("Invalid choice. Try again.");
        }
		}catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter numbers only.");
        }catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
		}
		sc.close();
	}
}
