import model.Member;
import model.MembershipPlan;
import service.Gym;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gym gym = new Gym();
        while (true) {
            System.out.println("\n====== Gym Membership Management ======");
            System.out.println("1. Add Member");
            System.out.println("2. View All Members");
            System.out.println("3. View Available Plans");
            System.out.println("4. Update Member Plan");
            System.out.println("5. Cancel Membership");
            System.out.println("6. Delete Member");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter member name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.println("user want to select the plan");
                    gym.showAvailablePlans();
                    System.out.print("Enter the plan name you want to choose: ");
                    String planName = sc.nextLine();
                    MembershipPlan plan = gym.getPlanByName(planName);
                    if (plan == null) {
                        System.out.println("Invalid plan name.");
                        break;
                    }
                    Member newMember = new Member(name, age);
                    newMember.setPlan(plan);
                    gym.addMember(newMember);
                    break;
                case 2:
                    gym.viewAllMembers();
                    break;
                case 3:
                    gym.showAvailablePlans();
                    break;
                case 4:
                    System.out.print("Enter member ID to update plan: ");
                    int memberId = sc.nextInt();
                    sc.nextLine();
                    gym.showAvailablePlans();
                    System.out.print("Enter new plan name: ");
                    String newPlanName = sc.nextLine();
                    MembershipPlan newPlan = gym.getPlanByName(newPlanName);
                    if (newPlan != null) {
                        gym.updatePlan(memberId, newPlan);
                    } else {
                        System.out.println("Invalid plan.");
                    }
                    break;
                case 5:
                    System.out.print("Enter member ID to cancel membership: ");
                    int cancelId = sc.nextInt();
                    gym.cancelMembership(cancelId);
                    break;
                case 6:
                    System.out.print("Enter member ID to delete: ");
                    int deleteId = sc.nextInt();
                    gym.deleteMember(deleteId);
                    break;
                case 7:
                    gym.close();
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
