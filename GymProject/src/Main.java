
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gym gym = new Gym();
        boolean running = true;

        while (running) {
            try {
                System.out.println("\n Gym Membership Management  ");
                System.out.println("1. Add New Member");
                System.out.println("2. Assign Membership Plan");
                System.out.println("3. View All Members");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter Member ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Age: ");
                        int age = Integer.parseInt(scanner.nextLine());
                        if (age <= 0) {
                            System.out.println("Invalid age. Must be positive.");
                            break;
                        }
                        gym.addMember(id, name, age);
                        break;

                    case 2:
                        System.out.print("Enter Member ID to assign plan: ");
                        int memberId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Available Plans:");
                        gym.showPlans();
                        System.out.print("Choose Plan (1-3): ");
                        int planIndex = Integer.parseInt(scanner.nextLine());
                        gym.assignPlan(memberId, planIndex);
                        break;

                    case 3:
                        gym.displayMembers();
                        break;

                    case 4:
                        running = false;
                        System.out.println("Exiting system.");
                        break;

                    default:
                        System.out.println("Invalid option....Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values.");
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
