        import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gym gym = new Gym();
        int choice;
        int memberIdCounter = 1;

        while (true) {
            System.out.println(" Gym Membership System ");
            System.out.println("1. Add New Member");
            System.out.println("2. Assign Membership Plan");
            System.out.println("3. View All Members");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter Member Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Member Age: ");
                        int age = Integer.parseInt(scanner.nextLine());

                        Member member = new Member(memberIdCounter++, name, age);
                        gym.addMember(member);
                        System.out.println("Member added successfully.");
                        break;

                    case 2:
                        System.out.print("Enter Member ID to assign plan: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Member foundMember = gym.findMemberById(id);
                        if (foundMember == null) {
                            System.out.println("Member not found");
                            break;
                        }

                        System.out.println("Available Plans:");
                        ArrayList<MembershipPlan> plans = gym.getPlans();
                        for (int i = 0; i < plans.size(); i++) {
                            System.out.println((i + 1) + ". " + plans.get(i));
                        }

                        System.out.print("Select a plan number: ");
                        int planChoice = Integer.parseInt(scanner.nextLine());

                        if (planChoice < 1 || planChoice > plans.size()) {
                            System.out.println("Invalid plan selection.");
                        } else {
                            foundMember.assignPlan(plans.get(planChoice - 1));
                            System.out.println("Plan assigned successfully.");
                        }
                        break;

                    case 3:
                        gym.showAllMembers();
                        break;

                    case 4:
                        System.out.println("Exiting system...");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter numbers only.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}

