import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
        Gym gym = new Gym();
        Scanner scanner = new Scanner(System.in);
        boolean flag= true;

        while (flag) {
            System.out.println("\n=== Gym Membership Management System ===");
            System.out.println("1. Add New Member");
            System.out.println("2. Assign Membership Plan");
            System.out.println("3. View All Members");
            System.out.println("4. Display Available Plans");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter Member ID: ");
                        int memberId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Age: ");
                        int age = Integer.parseInt(scanner.nextLine());
                        gym.addMember(memberId, name, age);
                        break;

                    case 2:
                        System.out.print("Enter Member ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        gym.displayAvailablePlans();
                        System.out.print("Enter Plan Name (Basic/Silver/Premium/Gold): ");
                        String planName = scanner.nextLine();
                        gym.assignPlanToMember(id, planName);
                        break;

                    case 3:
                        gym.viewAllMembers();
                        break;

                    case 4:
                        gym.displayAvailablePlans();
                        break;

                    case 5:
                        flag = false;
                        System.out.println("Exiting system. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

}
