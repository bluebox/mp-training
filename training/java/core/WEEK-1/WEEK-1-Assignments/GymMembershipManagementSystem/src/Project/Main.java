package Project;

import java.util.Scanner;

public class Main {
	
	public static int inputMemberId(Gym gym, Scanner scanner) {
		int id;
        while (true) {
            System.out.print("Enter Member ID: ");
            String input = scanner.nextLine();
            try {
                id = Integer.parseInt(input);
                Member existingMember=gym.getMemberById(id);
                if(existingMember!=null) {
                	System.out.println("Given member id already exists, Enter unique id...");
                	continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter a valid integer.");
            }
        }
        return id;
	}
	
	public static int inputAge(Gym gym, Scanner scanner) {
		int age;
        while (true) {
            System.out.print("Enter Age: ");
            String input = scanner.nextLine();
            try {
                age = Integer.parseInt(input);
                if (age > 0 && age<=100) break;
                else System.out.println("Age must be positive and must below 100...");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid age.");
            }
        }
        return age;
	}
	
	public static int inputMembershipPlan(Gym gym, Scanner scanner) {
		int planChoice;
        while (true) {
            System.out.println("Available Plans:");
            int index = 1;
            for (MembershipPlan p : gym.getPlans()) {
                System.out.println(index++ + ". " + p);
            }

            System.out.print("Choose a plan number: ");
            String input = scanner.nextLine();
            try {
                planChoice = Integer.parseInt(input);
                if (planChoice >= 1 && planChoice <= gym.getPlans().size()) break;
                else System.out.println("Please choose a valid option.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return planChoice;
	}
	
	public static void assignMembershipPlan(Gym gym, Scanner scanner) {
		while (true) {
        	System.out.print("Enter existing member id for which you want to assign plan : ");
            String input = scanner.nextLine();
            try {
                int id = Integer.parseInt(input);
                Member existingMember=gym.getMemberById(id);
                if(existingMember!=null) {
                	MembershipPlan currentMp=existingMember.getMembershipPlan();
                	int newMpChoice=inputMembershipPlan(gym, scanner);
                	MembershipPlan newMp = gym.getPlans().get(newMpChoice - 1);
                    if(currentMp.getPlanName()==newMp.getPlanName()) {
                    	System.out.println("You already have the same plan...");
                    	continue;
                    }
                    else {
                    	existingMember.setMembershipPlan(newMp);
                    	System.out.println("Plan updated succesfully...");
                    }
                	break;
                }
                System.out.println("Given member id does not exist, Give an existing id to assign plan...");
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter a valid integer.");
            }
        }
	}
	
    public static void main(String[] args) {
        Gym gym = new Gym();

        // Predefined plans
        gym.addPlan(new MembershipPlan("Basic", 1, 999));
        gym.addPlan(new MembershipPlan("Premium", 3, 2499));
        gym.addPlan(new MembershipPlan("Gold", 6, 4499));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Gym Membership Management ===");
            System.out.println("1. Add New Member (with Plan)");
            System.out.println("2. View All Members");
            System.out.println("3. Assign Membership Plan");
            System.out.println("4. Exit");

            int choice = -1;
            while (true) {
                System.out.print("Choose an option: ");
                String input = scanner.nextLine();
                try {
                    choice = Integer.parseInt(input);
                    if (choice >= 1 && choice <= 4) break;
                    else System.out.println("Please enter a number between 1 and 4.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }

            switch (choice) {
                case 1:
                    int id=inputMemberId(gym,scanner);
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    int age=inputAge(gym,scanner);
                    int planChoice=inputMembershipPlan(gym,scanner);
                    MembershipPlan selectedPlan = gym.getPlans().get(planChoice - 1);
                    Member m = new Member(name, age, id);
                    m.setMembershipPlan(selectedPlan);
                    gym.addNewMember(m);
                    System.out.println("Member added with plan successfully!");
                    break;

                case 2:
                    if (gym.getMembers().isEmpty()) {
                        System.out.println("No members registered yet.");
                    } else {
                        for (Member member : gym.getMembers()) {
                            member.showDetails();
                        }
                    }
                    break;

                case 3:
                    assignMembershipPlan(gym,scanner);
                    break;
                case 4:
                	System.out.println("Exiting system...");
                    scanner.close();
                    return;
            }
        }
    }
}
