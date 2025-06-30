import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gym gym = new Gym();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Gym Management System ===");
            System.out.println("1. Add New Member");
            System.out.println("2. Assign Membership Plan");
            System.out.println("3. View All Members");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter Member ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Age: ");
                        int age = Integer.parseInt(scanner.nextLine());
                        gym.addMember(id, name, age);
                        break;

                    case 2:
                        System.out.print("Enter Member ID to assign plan: ");
                        String memberId = scanner.nextLine();
                        System.out.println("Available Plans:");
                        gym.showPlans();
                        System.out.print("Enter plan index to assign: ");
                        int planIndex = Integer.parseInt(scanner.nextLine());
                        gym.assignPlan(memberId, planIndex);
                        break;

                    case 3:
                        gym.viewAllMembers();
                        break;

                    case 4:
                        running = false;
                        System.out.println("Exiting system...");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
