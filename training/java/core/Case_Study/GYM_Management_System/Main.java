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
                    String name = sc.nextLine();
                    System.out.println("Enter Age: ");
                    int age;
                    while(true) {
                        System.out.println("Enter Age(Age should be positive): ");

                        age = Integer.parseInt(sc.nextLine());

                    	if(age>0) {
                    		break;
                    	}
                    }
                    String id = MemberIdGenerator.generateId();

                    Member m = new Member(id, name, age);
                    gym.addMember(m);
                    System.out.println("Generated Member Id: " + id);

                    System.out.println("Do you want assign plan Now?? (Press Y for YES;other char for SKIP.)");
                    String assignNow = sc.nextLine().trim().toLowerCase();
                    if (assignNow.equals("yes") || assignNow.equals("y")) {
                        gym.viewPlans();
                        System.out.println("Enter plan name to assign");
                        String plan = sc.nextLine();
                        gym.assignPlan(id, plan);
                    } else {
                        System.out.println("You can assign plan later from main menu.");
                    }
                    break;
                case "2":
                    System.out.println("\n-----Registered Members ------");
                    gym.viewAllMembers();

                    System.out.println("Enter Member Id(Case sensitive): ");
                    String memberId = sc.nextLine();
                    gym.viewPlans();

                    System.out.print("Enter Plan Name to Assign: ");
                    String planName = sc.nextLine();

                    gym.assignPlan(memberId, planName);

                    break;
                case "3":
                    System.out.println("\n------Registered  Members ---------");
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
