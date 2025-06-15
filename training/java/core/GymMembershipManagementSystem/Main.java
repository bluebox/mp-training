package gymSystem;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gym gym=new Gym();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("1.Add new member\n" +
                    "2.Add member with plan\n" +
                    "3.Assign membership plan\n"+
                    "4.Remove a member\n" +
                    "5.View all registered members\n" +
                    "6.Exit..");
            System.out.println("\nEnter choice..");
            int choice=scanner.nextInt();
            switch (choice) {
                case 1:
                	System.out.println("Adding new member...");
                    System.out.println("enter member id");
                    int memberId = scanner.nextInt();
                    System.out.println("enter new member name");
                    String name = scanner.next();
                    System.out.println("enter new member's age");
                    int age = scanner.nextInt();
                    Member member = new Member(memberId, name, age);
                    gym.addNewMember(member);
                    System.out.println("New Member added successfully..");
                    break;
                case 2:
                    System.out.println("enter member id");
                    memberId = scanner.nextInt();
                    System.out.println("Adding new member...");
                    System.out.println("enter new member name");
                    name = scanner.next();
                    System.out.println("enter new member's age");
                    age = scanner.nextInt();
                    System.out.println("enter membership plan from below");
                    System.out.println(gym.membershipPlans);
                    String plan = scanner.next();
                    member = new Member(memberId, name, age);
                    MembershipPlan memberPlan = gym.getPlanByName(plan);
                    if (memberPlan != null) {
                        member.assignPlan(memberPlan);
                    }else{
                        System.out.println("Plan not found");
                        break;
                    }
                    gym.addNewMember(member);
                    break;
                case 3:
                    System.out.println("enter member id to assign membership plan");
                    int id=scanner.nextInt();
                    if(!gym.idExistsOrNot(id)){
                        System.out.println("Id not found");
                       break;
                    }
                    if(gym.getPlanById(id)) {
                        System.out.println("enter plan to assign");
                        String newPlan = scanner.next();
                        memberPlan = gym.getPlanByName(newPlan);
                        if (memberPlan != null) {
                            if (gym.addPlan(id, memberPlan)) {
                                System.out.println("Plan assigned..");
                            } else {
                                System.out.print("Already same plan assigned..");
                            }
                        } else {
                            System.out.println("Plan not found");
                        }
                    }
                    break;
                case 4:
                    System.out.println("enter memberId to remove a member");
                    id=scanner.nextInt();
                    if(gym.idExistsOrNot(id)){
                        if(gym.removeMember(id)) {
                            System.out.println("Member removed...");
                        }
                    }else{
                        System.out.println("Invalid member Id");
                    }
                    break;
                case 5:
                    System.out.println("\nview registered members");
                    gym.viewMembers();
                    break;
                case 6:
                    System.out.println("Exiting...");
                     return;
                default:
                    System.out.println("Invalid choice");
            }
        }

    }
}

	


