package gym.membership_Management.service;

import gym.membership_Management.controller.InvalidAgeException;
import gym.membership_Management.controller.InvalidNameException;
import gym.membership_Management.controller.PlanNotFoundException;
import gym.membership_Management.dao.MemberDao;
import gym.membership_Management.dao.MembershipPlanDao;
import gym.membership_Management.model.Member;
import gym.membership_Management.model.MemberStatus; 
import gym.membership_Management.model.MembershipPlan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GymService {
	private MemberDao memberDao;
    private MembershipPlanDao planDao;

    public GymService(MemberDao memberDao, MembershipPlanDao planDao) {
        this.memberDao = memberDao;
        this.planDao = planDao;
    }
    Scanner sc=new Scanner(System.in);

    public Member enrollNewMember(String name, int age, String planName) {
    	if(name.equals("null")||name.isEmpty()) {
    		System.err.println("enter valid name cause it is invalid value");
    		return null;
    	}
        MembershipPlan plan = planDao.getPlanByName(planName);
        if (plan == null) {
        	System.out.println("Please enter a valid plan");
            return null;
        }
        Member newMember = new Member(name, age, plan);
        return memberDao.addMember(newMember);
    }

    public boolean cancelMemberMembership(int memberId, String reason) {
        return memberDao.updateMemberStatus(memberId, MemberStatus.REMOVED, reason);
    }

    public List<Member> retrieveAllMembers() {
        return memberDao.getAllMembers();
    }

    public List<Member> retrieveActiveMembers() {
        return memberDao.getAllMembers().stream()
                .filter(member -> member.getStatus() == MemberStatus.ACTIVE)
                .collect(Collectors.toList());
    }

    public List<MembershipPlan> retrieveAllMembershipPlans() {
        return planDao.getAllPlans();
    }

    public MembershipPlan findPlanByName(String planName) {
        return planDao.getPlanByName(planName);
    }
    
    public Member getMemberById(int id) {
        return memberDao.getMemberById(id);
    }

    public List<Member> getMembersByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return List.of();
        }
        String searchNameLower = name.trim().toLowerCase();
        return memberDao.getAllMembers().stream()
                .filter(member -> member.getName().toLowerCase().contains(searchNameLower))
                .collect(Collectors.toList());
    }
    
    public int importMembersFromFile(String filePath) throws IOException {
        int importedCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) 
                	continue;
                String[] parts = line.split(",");
                if (parts.length <=3 ) {
                    try {
                      
                        String name = parts[0].trim();
                        System.out.println(name);
                        if(name.trim().isEmpty() || name.length() > 50) {
                   		 throw new InvalidNameException("Entered Name is Invalid!");
                   	 	}
                        int age = Integer.parseInt(parts[1].trim());
                        if(age<18 || age>100) {
                   		 throw new InvalidAgeException("Members age should be inbetween(18-100)");
                   	 }
                        String planName = parts[2].trim();

                        MembershipPlan plan = planDao.getPlanByName(planName);
                        if (plan == null) {
                            System.err.println("Skipping member: '" + name + "' from file. Plan '" + planName + "' not found.");
                            continue; 
                        }

                        Member newMember = new Member(name, age, plan);
                        memberDao.addMember(newMember); 
                        importedCount++;

                    } catch (InvalidNameException | InvalidAgeException e) {
                        System.err.println("Error parsing line from file: " + line + " - " + e.getMessage());
                    }
                } else {
                    System.err.println("Invalid format in file line: " + line);
                }
            }
        } 
        return importedCount;
    }
    
    public int getUserChoice() {
        while (!sc.hasNextInt()) {
            System.out.println("Input error. Please enter a valid number.");
            sc.next();
            System.out.print("Your choice: ");
        }
        int option = sc.nextInt();
        sc.nextLine();
        return option;
    }
    
    public void enrollNewMemberProcedure() {
    	try {
    		System.out.println("--- Enroll New Member ---");
    		System.out.print("Enter member's full name: ");
        
        	 String name = sc.nextLine();
        	 if(name.trim().isEmpty() || name.trim().equals("null")||name.trim().equals("Null")) {
        		 throw new InvalidNameException("Name Not Found");
        		 
        	 }
        	 else if(name.length() > 50) {
        		 throw new InvalidNameException("Entered Name is too long");
        	 }
        
        	 System.out.print("Enter member's age: ");
           	 int age = getUserChoice();
        	 if(age<18 || age>100) {
        		 throw new InvalidAgeException("Members with age(18-100) are allowed");
        	 }
        	 System.out.println("Available Membership Plans:");
        	 showAvailableMembershipPlans();
        	 System.out.print("Enter the exact plan name you wish to enroll in (e.g., Basic, Gold, Premium): ");
        	 String planName = sc.nextLine();
        	 Member newMember = this.enrollNewMember(name, age, planName);
        	 if (newMember == null) {
        		 throw new PlanNotFoundException("The specified membership plan"+planName+"was not found");
        	 }
             System.out.println("Member enrolled successfully!");
        	 System.out.println("New Member Details:");
       		 System.out.println("  Member ID: " + newMember.getMembershipId());
       		 System.out.println("  Name: " + newMember.getName());
       		 System.out.println("  Age: " + newMember.getAge());
       		 System.out.println("  Plan: " + newMember.getPlan().getNameOfPlan());
       		 System.out.println("  Status: " + newMember.getStatus());      	 
    	}
        catch (InvalidNameException | InvalidAgeException |PlanNotFoundException e) {
        	System.out.println("Validation Error: " + e.getMessage() + " Please try again.");
        }
    }
    
    public void cancelMemberProcedure() {
        System.out.println("--- Cancel Membership ---");
        System.out.print("Enter the Member ID to cancel their membership: ");
        int memberId = getUserChoice();
        System.out.print("Enter reason for removal (e.g., Moved, Financial, Health): ");
        try {
        	String reason = sc.next();
        	if(reason.trim().isEmpty()) {
        		throw new Exception();
        	}
        	if (cancelMemberMembership(memberId, reason)) {
                System.out.println("Membership for Member ID " + memberId + " has been successfully marked as 'REMOVED'.");
            } else {
                System.out.println("Cancellation failed. Member with ID " + memberId + " not found.");
            }
        }
        catch(Exception e) {
        	System.out.println("Please enter a valid reason. Cancellation failed");
        }
        
    }

    public void showAllRegisteredMembers() {
        List<Member> members = retrieveAllMembers();
        if (members.isEmpty()) {
            System.out.println("No members are currently registered in the system.");
        } else {
            System.out.println("--- All Registered Members ---");
            for (Member member : members) {
                System.out.println("Member ID: " + member.getMembershipId());
                System.out.println("  Name: " + member.getName());
                System.out.println("  Age: " + member.getAge());
                if (member.getPlan() != null) {
                    System.out.println("  Membership Plan: " + member.getPlan().getNameOfPlan() +
                                       " (Duration: " + member.getPlan().getDurationInDays() + " days, " +
                                       "Fee: $" + member.getPlan().getFee() + ")");
                } else {
                    System.out.println("  Membership Plan: Not assigned");
                }
                System.out.println("  Status: " + member.getStatus());
                if (member.getStatus() == MemberStatus.REMOVED && member.getRemovalReason() != null && !member.getRemovalReason().isEmpty()) {
                    System.out.println("  Removal Reason: " + member.getRemovalReason());
                }
                System.out.println("----------------------------------");
            }
        }
    }

    public void showAvailableMembershipPlans() {
        List<MembershipPlan> plans = retrieveAllMembershipPlans();
        if (plans.isEmpty()) {
            System.out.println("No membership plans are currently available.");
        } else {
            System.out.println("--- Available Membership Plans ---");
            for (MembershipPlan plan : plans) {
                System.out.println("Plan Name: " + plan.getNameOfPlan() +
                                   ", Duration: " + plan.getDurationInDays() + " days" +
                                   ", Fee: $" + plan.getFee());
            }
            System.out.println("----------------------------------");
        }
    }
    
    public void viewIndividualMemberDetailsProcedure() {
        System.out.println("--- View Individual Member Details ---");
        System.out.println("Search by:");
        System.out.println("1. Member ID");
        System.out.println("2. Member Name");
        System.out.print("Enter your choice (1 or 2): ");
        int searchChoice = getUserChoice();

        if (searchChoice == 1) {
            System.out.print("Enter Member ID: ");
            int memberId = getUserChoice();
            Member member = getMemberById(memberId);
            if (member != null) {
                System.out.println("\nMember Found:");
                displayMemberDetails(member);
            } else {
                System.out.println("\nNo member found with ID: " + memberId);
            }
        } else if (searchChoice == 2) {
            System.out.print("Enter Member Name (partial or full): ");
            String searchName = sc.nextLine();
            List<Member> foundMembers =getMembersByName(searchName);
            if (foundMembers.isEmpty()) {
                System.out.println("\nNo members found matching the name: '" + searchName + "'");
            } else {
                System.out.println("\n--- Members matching '" + searchName + "' ---");
                for (Member member : foundMembers) {
                    displayMemberDetails(member);
                    System.out.println("----------------------------------");
                }
            }
        } else {
            System.out.println("Invalid search choice. Please enter 1 or 2.");
        }
    }
    
    public void displayMemberDetails(Member member) {
        System.out.println("  Member ID: " + member.getMembershipId());
        System.out.println("  Name: " + member.getName());
        System.out.println("  Age: " + member.getAge());
        if (member.getPlan() != null) {
            System.out.println("  Membership Plan: " + member.getPlan().getNameOfPlan() +
                               " (Duration: " + member.getPlan().getDurationInDays() + " days, " +
                               "Fee: $" + member.getPlan().getFee() + ")");
        } else {
            System.out.println("  Membership Plan: Not assigned");
        }
        System.out.println("  Status: " + member.getStatus());
        if (member.getStatus() == MemberStatus.REMOVED && member.getRemovalReason() != null && !member.getRemovalReason().isEmpty()) {
            System.out.println("  Removal Reason: " + member.getRemovalReason());
        }
    }
    
    public void addNewMembershipPlan() {
    	System.out.println("Enter The Name Of Your New Plan: ");
    	String name=sc.next();
    	System.out.println("Enter The Validity Of The Plan(In Days)");
    	int duration=sc.nextInt();
    	System.out.println("Enter Price Of The New Plan");
    	double price=sc.nextDouble();
    	MembershipPlan plan=new MembershipPlan(name,duration,price);
    	planDao.addPlan(plan);
    }
    
    public void removeMembershipPlan() {
    	System.out.println("Enter The Plan Name To Remove");
    	String planName=sc.nextLine();
    	MembershipPlan plan=null;
    	if((!planName.trim().isEmpty()) && planName!=null) {
    		plan=planDao.getPlanByName(planName.trim());
    		if(plan!=null) {
    			planDao.removePlanByName(plan.getNameOfPlan());
    		}
    	}
    	else {
    		System.out.println("Invalid Data For Plan Name");
    	}
    }
