package utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import data.MemberDBOpearations;
import data.PersonDBOperations;
import models.Gym;
import models.Member;
import models.MembershipPlan;

public class InputUtilities {
	
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
                else {
                	throw new InvalidInputException("Please choose a valid option.");
                }
            } catch (NumberFormatException|InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        return planChoice;
	}
	
	public static int inputAge(Scanner scanner) {
		int age;
        while (true) {
            System.out.print("Enter Age: ");
            String input = scanner.nextLine();
            try {
                age = Integer.parseInt(input);
                if (age > 0 && age<=100) break;
                else throw new InvalidInputException("Age must be positive and must below 100, Enter valid Age...");
            } catch (NumberFormatException|InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        return age;
	}
	
	public static String inputPhone(Gym gym,Scanner scanner) {
		String phone;
        while (true) {
            System.out.print("Enter Phone Number: ");
            try {
            	phone = scanner.nextLine();
                if(phone.length()!=10) {
                	throw new InvalidInputException("Phone number must be of 10 digits only...");
                }
                if(!phone.matches("^[0-9]{10}$")) {
                	throw new InvalidInputException("Phone number should contain only digits...");
                }
                break;
            }catch(InvalidInputException e) {
            	System.out.println(e.getMessage());
            }
        }
        return phone;
	}
	
	public static void inputExistingMembershipPlan(Gym gym, Scanner scanner) {
		while (true) {
        	System.out.print("Enter existing member phone number to switch membership plan : ");
            String phone = scanner.nextLine();
            try {
            	if(phone.length()!=10) {
                	throw new InvalidInputException("Phone number must be of 10 digits only...");
                }
                if(!phone.matches("^[0-9]{10}$")) {
                	throw new InvalidInputException("Phone number should contain only digits...");
                }
                Member existingMember=gym.getMemberByPhone(phone);
                if(existingMember!=null) {
                	MembershipPlan currentMp=existingMember.getMembershipPlan();
                	int newMpChoice=InputUtilities.inputMembershipPlan(gym, scanner);
                	MembershipPlan newMp = gym.getPlans().get(newMpChoice - 1);
                    if(currentMp.getName()==newMp.getName()) {
                    	throw new InvalidInputException("You already have the same plan...");
                    }
                    else {
                    	existingMember.setMembershipPlan(newMp);
                    	MemberDBOpearations.updateMembershipPlan(existingMember.getMemberId(),newMp.getId());
                    	System.out.println("Plan updated succesfully...");
                    	break;
                    }
                }
                
                throw new InvalidInputException("Given phone number does not exist, Please add the member first...");
            }catch(InvalidInputException e) {
            	System.out.println(e.getMessage());
            	break;
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
            System.out.println("5. Update User Details");
            System.out.println("6. Delete User");
            System.out.println("7. Exit");

            int choice = -1;
            while (true) {
                System.out.print("Choose an option: ");
                String input = scanner.nextLine();
                try {
                    choice = Integer.parseInt(input);
                    if (choice >= 1 && choice <= 7) break;
                    else throw new InvalidInputException("Please enter a number between 1 and 7.");
                } catch (NumberFormatException|InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    String phone = InputUtilities.inputPhone(gym,scanner);
                    Member existingMember=gym.getMemberByPhone(phone);
                    if(existingMember!=null) {
                    	throw new InvalidInputException("Phone number already exists give different one...");
                    }
                    int age=InputUtilities.inputAge(scanner);
                    int planChoice=InputUtilities.inputMembershipPlan(gym,scanner);
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
                	System.out.print("Enter existing member phone number to edit details : ");
                    String existingPhone = scanner.nextLine();
                    try {
                    	if(existingPhone.length()!=10) {
                        	throw new InvalidInputException("Phone number must be of 10 digits only...");
                        }
                        if(!existingPhone.matches("^[0-9]{10}$")) {
                        	throw new InvalidInputException("Phone number should contain only digits...");
                        }
                        Member existing=gym.getMemberByPhone(existingPhone);
                        if(existing==null) {
                        	throw new InvalidInputException("Given phone number does not exist, Please add the member first...");
                        }
                        gym.editUserDetails(existing,scanner);
                    }catch(InvalidInputException e) {
                    	System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                	System.out.print("Enter existing member phone number to edit details : ");
                    existingPhone = scanner.nextLine();
                    try {
                    	if(existingPhone.length()!=10) {
                        	throw new InvalidInputException("Phone number must be of 10 digits only...");
                        }
                        if(!existingPhone.matches("^[0-9]{10}$")) {
                        	throw new InvalidInputException("Phone number should contain only digits...");
                        }
                        Member existing=gym.getMemberByPhone(existingPhone);
                        if(existing==null) {
                        	throw new InvalidInputException("Given phone number does not exist, Please add the member first...");
                        }
                        gym.deleteMemberByPhone(existingPhone);
                    }catch(InvalidInputException e) {
                    	System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                	System.out.println("Exiting system...");
                    scanner.close();
                    return;
            }
        }
	}
}
