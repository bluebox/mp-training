package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import daoImplementation.MemberDAOImplementation;
import daoImplementation.PersonDAOImplementation;
import serviceImplementation.GymServiceImplementation;
import serviceImplementation.MemberServiceImplementation;
import serviceImplementation.MembershipPlanServiceImplementation;
import utilities.InvalidInputException;

public class GymController {
	public static void inputMain(Scanner scanner, GymServiceImplementation gym) {
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
                    try {
                    	System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        if(name.length()>50) {
                        	throw new InvalidInputException("Name cannot be greater than 50 chracters...");
                        }
                        if(name.equals("") || name.strip().equals("")) {
                        	throw new InvalidInputException("Name must be given...");
                        }
                        String phone = PersonController.inputPhone(gym,scanner);
                        MemberServiceImplementation existingMember=gym.getMemberByPhone(phone);
                        if(existingMember!=null) {
                        	throw new InvalidInputException("Phone number already exists give different one...");
                        }
                        int age=PersonController.inputAge(scanner);
                        int planChoice=MembershipPlanController.inputMembershipPlan(gym,scanner);
                        MembershipPlanServiceImplementation selectedPlan = gym.getPlans().get(planChoice - 1);
                        new PersonDAOImplementation().addPerson(phone, name, age);
                        int memberId=new MemberDAOImplementation().addMember(phone, planChoice, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        MemberServiceImplementation m = new MemberServiceImplementation(phone, name, age, memberId, selectedPlan, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        gym.addNewMember(m);
                        System.out.println("Member added with plan successfully!");
                    }catch(InvalidInputException e) {
                    	System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    if (gym.getMembers().isEmpty()) {
                        System.out.println("No members registered yet.");
                    } else {
                        for (MemberServiceImplementation member : gym.getMembers()) {
                            member.showDetails();
                        }
                    }
                    break;
                case 3:
                	MembershipPlanController.inputExistingMembershipPlan(gym,scanner);
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
                        MemberServiceImplementation existing=gym.getMemberByPhone(existingPhone);
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
                        MemberServiceImplementation existing=gym.getMemberByPhone(existingPhone);
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
