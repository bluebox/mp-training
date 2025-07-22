package controller;

import java.util.Scanner;

import daoImplementation.MemberDAOImplementation;
import serviceImplementation.GymServiceImplementation;
import serviceImplementation.MemberServiceImplementation;
import serviceImplementation.MembershipPlanServiceImplementation;
import utilities.InvalidInputException;

public class MembershipPlanController {
	public static int inputMembershipPlan(GymServiceImplementation gym, Scanner scanner) {
		int planChoice;
        while (true) {
            System.out.println("Available Plans:");
            int index = 1;
            for (MembershipPlanServiceImplementation p : gym.getPlans()) {
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
	
	public static void inputExistingMembershipPlan(GymServiceImplementation gym, Scanner scanner) {
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
                MemberServiceImplementation existingMember=gym.getMemberByPhone(phone);
                if(existingMember!=null) {
                	MembershipPlanServiceImplementation currentMp=existingMember.getMembershipPlan();
                	int newMpChoice=MembershipPlanController.inputMembershipPlan(gym, scanner);
                	MembershipPlanServiceImplementation newMp = gym.getPlans().get(newMpChoice - 1);
                    if(currentMp.getName()==newMp.getName()) {
                    	throw new InvalidInputException("You already have the same plan...");
                    }
                    else {
                    	existingMember.setMembershipPlan(newMp);
                    	new MemberDAOImplementation().updateMembershipPlan(existingMember.getMemberId(),newMp.getId());
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
}
