import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gym g1 = new Gym();
        boolean flag = true;
        int n = 0;

        while (flag) {
            System.out.println();
            System.out.println("Enter an option :");
            System.out.println("-".repeat(20));
            System.out.println("1 : Add a new Member");
            System.out.println("2 : Assign a new Gym Plan");
            System.out.println("3 : View All members");
            System.out.println("0 : To exit");
            System.out.println();

            String s = sc.nextLine();

            try {
                n = Integer.parseInt(s);
            } catch (NumberFormatException nfe) {
            	System.out.println("Invalid input");
                continue;  
            }

            switch (n) {
                case 0:
                    System.out.println();
                    System.out.println("Exiting from the system");
                    flag = false;
                    break;
                case 1:
                    System.out.println();
                    try {
                    System.out.println("Enter the member id: ");
                    String id = sc.nextLine();
                    if(g1.MemberExist(id)) {
            			System.out.println("User exists");
            			continue;
            		}
                    System.out.println("Enter your name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter your age: ");
                    int age = sc.nextInt();
                    sc.nextLine();  
                    g1.addMember(id, name, age);
                    }
                    catch(IllegalArgumentException ee) {
                    	System.out.println("id cant be empty");
                    }
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Enter member id to assign gym plan: ");
                    String id1 = sc.nextLine();
                    if(!g1.MemberExist(id1)) {
                    	System.out.println("Member not there");}
                    else {
                    for (int i = 0; i < g1.getPlans().size(); i++) {
                        System.out.println((i + 1) + " " + g1.getPlans().get(i));
                    }
                    System.out.println("Enter the plan no: ");
                    try {
                        int planIndex = Integer.parseInt(sc.nextLine()) - 1; 
                        g1.assignMembershipPlan(id1, planIndex);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid Index");
                    }}
                    break;
                case 3:
                    System.out.println();
                    g1.viewAllMember();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
}
