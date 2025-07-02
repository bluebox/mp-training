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
            System.out.println("menu for geting Options");
            System.out.print("Enter your choice: ");
		
		try {
			int choice = Integer.parseInt(sc.nextLine());
			
			switch (choice) {
            case 1:
                System.out.print("Enter Member ID: ");
                String memberId = sc.nextLine();
                
                if(check(memberId)) {
                	break;
                }
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                if(check(name)) {
                	break;
                }
                System.out.print("Enter Age: ");
                try {
                	int age = Integer.parseInt(sc.nextLine());
                
                	if (age <= 0) {
                		System.out.println("Age must be positive.");
                		break;
                	}
                	Member newMember = new Member(memberId, name, age);
                    gym.addMember(newMember);
                    break;
                }catch(NumberFormatException e){
                	break;
                }

            case 2:
                System.out.print("Enter Member ID to assign plan: ");
                String idForPlan = sc.nextLine();
                if(check(idForPlan)) {
                	break;
                }
                System.out.println("Available Plans:");
                gym.showAvailablePlans();
                System.out.print("Select plan number: ");
                try {
	                int planChoice = Integer.parseInt(sc.nextLine());
	                gym.assignPlanToMember(idForPlan, planChoice);
	                break;
                }catch(NumberFormatException e){
                	break;
                }
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
			System.out.println("Please enter numbers Choice.");
        }catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
		}
		sc.close();
	}
	
	public static boolean check(String option) {
		if(option.toLowerCase().equals("menu")) {
			return true;
		}
		return false;
		
	}
}
