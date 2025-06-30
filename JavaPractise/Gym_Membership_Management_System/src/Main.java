import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gym gym = new Gym();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Gym Membership System ---");
            System.out.println("1. Add New Member");
            System.out.println("2. Assign Membership Plan");
            System.out.println("3. View All Members");
            System.out.println("4. Update Member");
            System.out.println("5. Delete Member");
            System.out.println("6. Save Data");
            System.out.println("7. Load Data");
            System.out.println("8. Exit");
            System.out.println("9. Update Membership Plan");
            System.out.println("10. Display Member Details");

            System.out.print("Choose option: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Member ID: ");
                        String id = sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Age: ");
                        int age = Integer.parseInt(sc.nextLine());

                        gym.addMember(new Member(id, name, age));
                    }

                    case 2 -> {
                        System.out.print("Enter Member ID to assign plan: ");
                        String id = sc.nextLine();
                        gym.showPlans();
                        System.out.print("Choose plan index: ");
                        int planIdx = Integer.parseInt(sc.nextLine());
                        gym.assignPlan(id, planIdx);
                    }

                    case 3 -> gym.viewAllMembers();

                    case 4 -> {
                        System.out.print("Enter Member ID to update: ");
                        String id = sc.nextLine();
                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter New Age: ");
                        int newAge = Integer.parseInt(sc.nextLine());
                        gym.updateMember(id, newName, newAge);
                    }

                    case 5 -> {
                        System.out.print("Enter Member ID to delete: ");
                        String id = sc.nextLine();
                        gym.deleteMember(id);
                    }

                    case 6 -> gym.saveToFile("members.txt");

                    case 7 -> gym.loadFromFile("members.txt");		

                    case 8 -> {
                        System.out.println("Exiting system. Goodbye!");
                        running = false;
                    }
                    case 9 -> {
                        System.out.print("Enter Member ID to update plan: ");
                        String id = sc.nextLine();
                        gym.showPlans();
                        System.out.print("Enter new plan index: ");
                        int newPlanIndex = Integer.parseInt(sc.nextLine());
                        gym.updateMemberPlan(id, newPlanIndex);
                    }
                    case 10 -> {
                        System.out.print("Enter Member ID to display: ");
                        String id = sc.nextLine();
                        gym.displayMemberDetails(id);
                    }


                    default -> System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values where required.");
            }
        }

        sc.close();
    }
}
