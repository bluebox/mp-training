package validators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import data.MemberDBOpearations;
import data.PersonDBOperations;
import models.Gym;
import models.Member;
import models.MembershipPlan;

public class InputValidators {
	
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
	
	public static int inputAge(Scanner scanner) {
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
	
	public static String inputPhone(Gym gym,Scanner scanner) {
		String phone;
        while (true) {
            System.out.print("Enter Phone Number: ");
            phone = scanner.nextLine();
            if(phone.length()!=10) {
            	System.out.println("Phone number must be of 10 digits only...");
            	continue;
            }
            if(!phone.matches("^[0-9]{10}$")) {
            	System.out.println("Phone number should contain only digits...");
            	continue;
            }
            break;
        }
        return phone;
	}
	
	public static void inputExistingMembershipPlan(Gym gym, Scanner scanner) {
		while (true) {
        	System.out.print("Enter existing member phone number to switch membership plan : ");
            String phone = scanner.nextLine();
            if(phone.length()!=10) {
            	System.out.println("Phone number must be of 10 digits only...");
            	continue;
            }
            if(!phone.matches("^[0-9]{10}$")) {
            	System.out.println("Phone number should contain only digits...");
            	continue;
            }
            try {
                Member existingMember=gym.getMemberByPhone(phone);
                if(existingMember!=null) {
                	MembershipPlan currentMp=existingMember.getMembershipPlan();
                	int newMpChoice=InputValidators.inputMembershipPlan(gym, scanner);
                	MembershipPlan newMp = gym.getPlans().get(newMpChoice - 1);
                    if(currentMp.getName()==newMp.getName()) {
                    	System.out.println("You already have the same plan...");
                    }
                    else {
                    	existingMember.setMembershipPlan(newMp);
                    	MemberDBOpearations.updateMembershipPlan(existingMember.getMemberId(),newMp.getId());
                    	System.out.println("Plan updated succesfully...");
                    }
                	break;
                }
                System.out.println("Given member id does not exist, Please add the member first...");
                return;
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter a valid integer.");
            }
        }
	}
	
	public static void inputMain(Scanner scanner, Gym gym) {
		while (true) {
            System.out.println("\n=== Gym Membership Management ===");
            System.out.println("1. Add New Member (with Plan)");
            System.out.println("2. View All Members");
            System.out.println("3. Switch Membership Plan");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");

            int choice = -1;
            while (true) {
                System.out.print("Choose an option: ");
                String input = scanner.nextLine();
                try {
                    choice = Integer.parseInt(input);
                    if (choice >= 1 && choice <= 5) break;
                    else System.out.println("Please enter a number between 1 and 5.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    String phone = InputValidators.inputPhone(gym,scanner);
                    Member existingMember=gym.getMemberByPhone(phone);
                    if(existingMember!=null) {
                    	System.out.println("Phone number already exists give different one...");
                    	continue;
                    }
                    int age=InputValidators.inputAge(scanner);
                    int planChoice=InputValidators.inputMembershipPlan(gym,scanner);
                    MembershipPlan selectedPlan = gym.getPlans().get(planChoice - 1);
                    PersonDBOperations.addPerson(phone, name, age);
                    int memberId=MemberDBOpearations.addMember(phone, planChoice, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    Member m = new Member(phone, name, age, memberId, selectedPlan, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
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
                	inputExistingMembershipPlan(gym,scanner);
                    break;
                case 4:
                	gym.generateReport();
                	break;
                case 5:
                	gym.editUserDetails();
                	break;
                case 6:
                	System.out.println("Exiting system...");
                    scanner.close();
                    return;
            }
        }
	}
}
