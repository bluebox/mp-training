
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gym gym = new Gym();
        int idCounter = 1;

        while (true) {
            System.out.println("\n1. Add Member");
            System.out.println("2. Assign Plan");
            System.out.println("3. View All Members");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        sc.nextLine(); // consume newline
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter age: ");
                        int age = sc.nextInt();
                        gym.addMember(new Member(idCounter++, name, age));
                        break;
                    case 2:
                        gym.showPlans();
                        System.out.print("Enter Member ID: ");
                        int memberId = sc.nextInt();
                        System.out.print("Enter Plan Index: ");
                        int planIndex = sc.nextInt();
                        gym.assignPlanToMember(memberId, planIndex);
                        break;
                    case 3:
                        gym.showAllMembers();
                        break;
                    case 4:
                        System.out.println("Exiting system. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong. Try again.");
                sc.nextLine(); // clear input buffer
            }
        }
    }
}
