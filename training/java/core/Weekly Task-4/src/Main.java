import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Member;
import models.MembershipPlan;
import service.GymService;

public class Main {
	public static void main(String[] args) {
		GymService gymService=new GymService();
		Scanner sc=new Scanner(System.in);
		boolean flag=true;
		while(flag) {
			System.out.println("""
					-------------- GYM Membership --------------
					1 - Add a new member
					2 - Add a new membership plan
					3 - View all members
					4 - Update member details
					5 - Delete a member
					6- Exit
					""");
			
			int input;
				System.out.println("Enter an input : ");
				try {
				String temp=sc.nextLine();
				input=Integer.parseInt(temp);
				}catch(NumberFormatException e) {
					System.out.println("Invalid input type");
					continue;
				}
			
			switch(input) {
			case 1:{
				//Add a new member
				//sc.nextLine(); 
                String name;
                do {
                    System.out.print("Enter name: ");
                    name = sc.nextLine();
                    if (!isValidName(name)) {
                        System.out.println(" Invalid name ");
                    }
                } while (!isValidName(name));

                String gender;
                do {
                    System.out.print("Enter gender (M/F): ");
                    gender = sc.nextLine();
                    if (!isValidGender(gender)) {
                        System.out.println("Invalid gender ");
                    }
                } while (!isValidGender(gender));

                String ageInput;
                int age;
                do {
                    System.out.print("Enter Age: ");
                    ageInput = sc.nextLine();
                    if (!isValidAge(ageInput)) {
                        System.out.println("Invalid age");
                        age = -1;
                    } else {
                        age = Integer.parseInt(ageInput);
                    }
                } while (age == -1);

                Member member = new Member(name, gender, age);
                gymService.addMember(member);
                break;
			}
                
            
			case 2:{
				//Add a new membership plan
				System.out.println("-".repeat(35)+"\n");
			    int memberId;
			    while (true) {
			        System.out.print("Enter Member ID: ");
			        String idInput = sc.nextLine();
			        try {
			            memberId = Integer.parseInt(idInput);
			            break;
			        } catch (NumberFormatException e) {
			            System.out.println("Invalid Member ID. Please enter a valid number.");
			        }
			    }
			    Member member = gymService.getMemberById(memberId);
			    if (member == null) {
			        System.out.println("No member found with ID: " + memberId);
			        break;
			    }
			    if (member.getMembershipPlan() != null) {
			        System.out.println("Member already has a plan : " + member.getMembershipPlan().getPlanName());
			    } else {
			    	gymService.showPlans();
			        System.out.print("Enter Plan ID to assign: ");
			        int planId = -1;
			        while (true) {
			            System.out.print("Enter Plan ID: ");
			            String input1 = sc.nextLine();

			            try {
			                planId = Integer.parseInt(input1);
			                if (planId > 0) {
			                    break; 
			                } else {
			                    System.out.println("Plan ID must be a positive number.");
			                }
			            } catch (NumberFormatException e) {
			                System.out.println("Invalid input. Please enter a numeric Plan ID.");
			            }
			        }
			        gymService.assignPlanToMember(memberId, planId);
			    }
				break;
			}
				
			case 3:{
				//View all members
				System.out.println("-".repeat(35));
				List<Member> members = gymService.getAllMembers();
				if (members.isEmpty()) {
			        System.out.println("No members found.");
			    } else {
			        System.out.println("\nRegistered Members:");
			        for (Member member : members) {
			            System.out.println("ID      : " + member.getMemberId());
			            System.out.println("Name    : " + member.getName());
			            System.out.println("Gender  : " + member.getGender());
			            System.out.println("Age     : " + member.getAge());
			            if (member.getMembershipPlan() != null) {
			                System.out.println("Plan    : " + member.getMembershipPlan().getPlanName());
			                System.out.println("Cost   : " + member.getMembershipPlan().getFee());
			                System.out.println("Duration: " + member.getMembershipPlan().getDuration());
			            } else {
			                System.out.println("Plan    : Not Assigned");
			            }
						System.out.println("-".repeat(35)+"\n");
			        }
			    }
				break;
				}
				
			case 4:{
				//Update member details
				System.out.println("-".repeat(35)+"\n"); 
			    int memberId;
			    while (true) {
			        System.out.print("Enter Member ID: ");
			        String idInput = sc.nextLine();
			        try {
			            memberId = Integer.parseInt(idInput);
			            break;
			        } catch (NumberFormatException e) {
			            System.out.println("Invalid Member ID. Please enter a valid number.");
			        }
			    }
			    Member member = gymService.getMemberById(memberId);
			    if (member == null) {
			        System.out.println("No member found with ID: " + memberId);
			        break;
			    }
			    System.out.println("""
			            What would you like to update?
			            1 - Name
			            2 - Gender
			            3 - Age
			            4 - Cancel
			            """);

			        System.out.print("Enter your choice: ");
			        String choice = sc.nextLine();

			        switch (choice) {
			            case "1" :{
			                String newName;
			                do {
			                    System.out.print("Enter new name: ");
			                    newName = sc.nextLine();
			                    if (!isValidName(newName)) {
			                        System.out.println("Invalid name");
			                    }
			                } while (!isValidName(newName));
			                member.setName(newName);
			                gymService.updateMember(member);
			                System.out.println("Name updated successfully.");
			                break;
			            }
			            case "2" : {
			                String newGender;
			                do {
			                    System.out.print("Enter new gender (M/F): ");
			                    newGender = sc.nextLine();
			                    if (!isValidGender(newGender)) {
			                        System.out.println("Invalid gender. Enter 'M' or 'F'.");
			                    }
			                } while (!isValidGender(newGender));
			                member.setGender(newGender);
			                gymService.updateMember(member);
			                System.out.println("Gender updated successfully.");
			                break;
			            }
			            case "3" :{
			                int newAge;
			                String ageInput;
			                do {
			                    System.out.print("Enter new age: ");
			                    ageInput = sc.nextLine();
			                    if (!isValidAge(ageInput)) {
			                        System.out.println("Invalid age");
			                        newAge = -1;
			                    } else {
			                        newAge = Integer.parseInt(ageInput);
			                    }
			                } while (newAge == -1);
			                member.setAge(newAge);
			                gymService.updateMember(member);
			                System.out.println("Age updated successfully.");
			                break;
			            }
			            case "4" : {
			                System.out.println("Update cancelled");
			                break;
			            }

			            default :{
			                System.out.println("Invalid choice.");
			            }
			        }
				break;
			    }
			
			
			case 5:{
//				Delete a member
				System.out.println("-".repeat(35)+"\n");
			    int memberId;
			    while (true) {
			        System.out.print("Enter Member ID: ");
			        String idInput = sc.nextLine();
			        try {
			            memberId = Integer.parseInt(idInput);
			            break;
			        } catch (NumberFormatException e) {
			            System.out.println("Invalid Member ID. Please enter a valid number.");
			        }
			    }
			    Member member = gymService.getMemberById(memberId);
			    if (member == null) {
			        System.out.println("No member found with ID: " + memberId);
			        break;
			    }
			    gymService.deleteMember(member);
				break;
			}
			
			case 6:
				System.out.println("Exiting... Bye!");
				flag=false;
				break;
				
			default : {
				System.out.println("Invalid input");
			}
			}
		
				
		}
	}
		
		public static boolean isValidName(String str) {
	        if (str == null || str.trim().isEmpty())
	        	return false;
	        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$", Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(str);
	        return matcher.matches();
	    }

	    public static boolean isValidGender(String gender) {
	        if (gender == null || gender.trim().isEmpty())
	        	return false;
	        return gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F");
	    }

	    public static boolean isValidAge(String age) {
	        if (age == null || age.trim().isEmpty())
	        	return false;
	        try {
	            int result = Integer.parseInt(age);
	            return result > 18 && result < 70;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	
}












//6 - Upgrade membership plan

//
//case 6:{
//	//Update membership plan
//	System.out.println("-".repeat(35)+"\n");
//	sc.nextLine(); 
//    int memberId;
//    while (true) {
//        System.out.print("Enter Member ID: ");
//        String idInput = sc.nextLine();
//        try {
//            memberId = Integer.parseInt(idInput);
//            break;
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid Member ID. Please enter a valid number.");
//        }
//    }
//    Member member = gymService.getMemberById(memberId);
//    if (member == null) {
//        System.out.println("No member found with ID: " + memberId);
//        break;
//    }
//    MembershipPlan currentPlan = member.getMembershipPlan();
//    List<MembershipPlan> allPlans = gymService.getAllPlans();
//
//    if (currentPlan == null) {
//        System.out.println("Member has no assigned plan.");
//        System.out.println("Get a membership first!!");
//        break; 
//    }
//
//    int currentPlanId = currentPlan.getPlanId();
//    List<MembershipPlan> upgrades = allPlans.stream()
//        .filter(plan -> plan.getPlanId() > currentPlanId)
//        .toList();
//
//    if (upgrades.isEmpty()) {
//        System.out.println("Member already has the highest available plan: " + currentPlan.getPlanName());
//        break;
//    }
//
//    System.out.println("Available Upgrades in the plans :");
//    for (MembershipPlan plan : upgrades) {
//        System.out.printf("ID : %d   Name : %s  Cost : %.2f Duration : %s  \n",
//            plan.getPlanId(), plan.getPlanName(), plan.getFee(), plan.getDuration());
//    }
//
//    System.out.print("Enter Plan ID to upgrade: ");
//    int planId = -1;
//    while (true) {
//        System.out.print("Enter Plan ID: ");
//        String input1 = sc.nextLine();
//
//        try {
//            planId = Integer.parseInt(input1);
//            if (planId > 0 && planId>currentPlanId) {
//                break; 
//            } else {
//                System.out.println("Plan ID must be a positive number. Can't assign lower plan");
//            }
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid input. Please enter a numeric Plan ID.");
//        }
//    }
//
//    gymService.assignPlanToMember(memberId, planId);
//    break;
//
//}