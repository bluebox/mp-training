package Default;

import java.util.Scanner;

public class GymManageMain {
    public static void main(String[] args) {
       
        Scanner sc = new Scanner(System.in);

        Gym gym = new Gym();

        gym.addPlan(new MembershipPlan("Basic", 3, 1000));
        gym.addPlan(new MembershipPlan("Premium", 6, 1800));
        gym.addPlan(new MembershipPlan("Gold", 12, 3000));

        while (true) {
            
            System.out.println("\n===== GYM MANAGEMENT MENU OPTIONS =====");
            System.out.println("1. Add New Member");
            System.out.println("2. Assign Membership Plan");
            System.out.println("3. View All Members");
            System.out.println("4. Exit");
            System.out.println("Choose one of the options provided above:");
            int choice = sc.nextInt();
            sc.nextLine(); 
            
            switch (choice) {
            
                case 1:
                    
                    System.out.print("Enter Member ID: ");
                    String id = sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    Member newMember = new Member(id, name, age);
                    gym.addMember(newMember);
                    System.out.println("<<<< Member added successfully! >>>>");
                    break;

                case 2:
                    
                    System.out.print("Enter Member ID to assign a plan: ");
                    id = sc.nextLine();

                    Member memberExisted = gym.getMemberById(id);
                    if (memberExisted != null) {
                        
                        System.out.print("Enter Plan Name (Basic / Premium / Gold): ");
                        String planName = sc.nextLine();

                        MembershipPlan selectedPlan = gym.getPlanByName(planName);
                        if (selectedPlan != null) {
                            memberExisted.assignPlan(selectedPlan);
                            System.out.println("<<<< Plan assigned successfully! >>>>");
                        } 
                        
                        else {
                            System.out.println(" Plan not found. Please type Basic, Premium, or Gold.");
                        }
                    } 
                    
                    else {
                        System.out.println(" Member not found with the given ID.");
                    }
                    break;

                case 3:
                    
                    System.out.println("All Registered Members List : ");
                    gym.showAllMembers();
                    break;

                case 4:
                    
                    System.out.println(" Exiting the system. Goodbye!");
                    return;

                default:
                    
                    System.out.println(" Invalid choice!!!  Please enter Valid option Number.");
            }
        }
    }
}
