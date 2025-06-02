import java.util.*;

public class Main {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        Gym gym = new Gym();
        System.out.println("\t\t\t\t############# Boult GYM #############");

        while (true) {
            System.out.println("1.Add New Member\n2.Assign Membership plan\n3.View All\n4.Exit");

            String option = sc.nextLine();
            switch (option) {
                case "1":
                    System.out.println("Collect the  details of New member.");
                    System.out.println("Enter Name: ");
                    String name=sc.nextLine();
                    System.out.println("Enter Age: ");
                    int age=Integer.parseInt(sc.nextLine());
                    System.out.print("Enter Member ID: ");
                    String id = sc.nextLine();

                    Member m=new Member(id,name,age);
                    gym.addMember(m);
                    break;
                case "2":
                    System.out.println("Assigning the plan");
                    System.out.println("Enter Member ID: ");
                    String memberId=sc.nextLine();
                    gym.viewPlans();

                    System.out.println("Enter plan name: ");
                    String planName=sc.nextLine();

                    gym.assignPlan(memberId, planName);
                    break;
                case "3":
                    System.out.println("View All members");
                    gym.viewAllMembers();
                    break;
                case "4":
                    System.out.println("Thank You");
                    return;
                default:
                    System.out.println("Invalid option.Try Again");
                    break;
            }
        }
    }
}