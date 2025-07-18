package com.gym.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.gym.dao.MemberDao;
import com.gym.dao.MemberFileDao;
import com.gym.models.Member;
import com.gym.models.MembershipPlan;

//add object validation in service
//file handling exception handling in main (throw error from dao handle in service)


//TODO : make a gym service interface and implement on top of it

public class Gym {
    
    private static ArrayList<MembershipPlan> plans ;
    private MemberDao memberDao;
    
    static {
    	plans = new ArrayList<>();
        plans.add(new MembershipPlan("Basic", 3, 5000));
        plans.add(new MembershipPlan("Premium", 6, 8000));
        plans.add(new MembershipPlan("Gold", 12, 12000));
    }

    public Gym() {
    	memberDao = new MemberFileDao();
    }

    public void addNewMember(Member newMember) {
    	//if addNewMember is being accessed externally ,from anywhere other than our main this is the failsafe check
    	if(!validateNewMember(newMember)) {
    		System.out.println("Member data is not valid!\n");
    		return ;
    	}
    	
        if(memberDao.saveNewMember(newMember))
        	System.out.println("User registered!\n");
        else
        	System.out.println("Couldn't Register user :( \n");
    }

    private boolean validateNewMember(Member newMember) {
		if(newMember.getAge() < 12 || newMember.getAge() > 120) {
			return false;
		}
		if(newMember.getWeight() < 20 || newMember.getAge() > 200) {
			return false;
		}
		if(newMember.getHeight() < 100 || newMember.getHeight() > 250) {
			return false;
		}
		return true;
	}
    
	public void showAllMembers() {
    	List<Member> members = memberDao.loadFromFile();
    	if(members.isEmpty()) {
    		System.out.println("\nno members exist in the database\n");
    		return;
    	}
    	
        System.out.println("\n=========================== Current Members =============================");
        System.out.printf(
    	        "%-6s %-20s %-4s %-6s %-7s %-20s %-15s%n",
    	        "ID", "Name", "Age", "Height", "Weight", "Plan", "Joining Date"
    	    );
        for (Member member : members) {
        	member.showDetails();
        }
        System.out.println("=========================================================================\n");
    }
    
    public void displayPlans() {
    	for (int i = 0; i < plans.size(); i++) {
    		MembershipPlan currPlan = plans.get(i);
            System.out.printf("%d. %s - %d Months - %d Rupees\n", i + 1, currPlan.planName,currPlan.getDurationMonths(),currPlan.getFee());
        }
    }
    
    public void assignPlanToMember(int memberId, int planId,String date) throws InvalidDateException {
    	List<Member> members = memberDao.loadFromFile();
        for (Member x : members) {
            if (x.getMemberId() == memberId) {
            	MembershipPlan targetPlan = plans.get(planId-1);
            	MembershipPlan currentPlan = x.getMembershipPlan();
            	
            	if(currentPlan != null) {
            	if(currentPlan.getFee() > targetPlan.getFee()) {
            			System.out.println("Cannot downgrade your plan!\n");
            			return;
            		}
            		if(currentPlan.getFee() == targetPlan.getFee()) {
            			System.out.println("You already have that plan assigned!\n");
            			return;
            		}
            		
            		//calculate the perDay for the current plan 
            		//calculate the remaining days from user's registered day
            		double perMonth = currentPlan.getFee()/(currentPlan.getDurationMonths());

            		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            		            		
                    LocalDate currDate = LocalDate.parse(date, formatter);
                    LocalDate prevDate = LocalDate.parse(x.getJoinDate(), formatter);
                                     
                    if(prevDate.isAfter(currDate)) {
                    	System.out.println("Cannot enter date earlier than the previous date\n");
                    	throw new InvalidDateException("The date you entered is invalid");
                    }
                    
                    long MonthsSinceJoining = ChronoUnit.MONTHS.between(prevDate,currDate);
                    long MonthsRemaining = currentPlan.getDurationMonths()-MonthsSinceJoining;
                    
                    if(MonthsRemaining >= 0) {
                    	double payable = targetPlan.getFee() - (MonthsRemaining*perMonth);
                        System.out.printf("Member only had to pay %.2f\n",payable);
                    }
                    else {
                    	System.out.println("Your current membership ends sooner than your selected date for next plan!\n");
                    	return;
                    }
            	}
                x.setMemPlan(targetPlan);
                x.setJoinDate(date);
                System.out.println("Successfully assigned plan to user!\n");
                memberDao.saveToFile((ArrayList<Member>) members);
                return ;
            }
        }
                System.out.println("Member not found. Please enter a valid id.\n");

    }

    public static ArrayList<MembershipPlan> getPlans() {
        return plans;
    }

    public ArrayList<Member> getMembers() {
        return memberDao.loadFromFile();
    }

    public Member getMemberById(int id) {
    	List<Member> members = memberDao.loadFromFile();
    	for(Member member : members) {
    		if(member.getMemberId() == id) {
    			return member;
    		}
    	}

    	return null;
    }
    
    
    
    public void deleteMember(int idToDelete) {
    	List<Member> members = memberDao.loadFromFile();
    	
        if (members.isEmpty()) {
            System.out.println("No members to delete.\n");
            return;
        }

       
        boolean found = false;

        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getMemberId() == idToDelete) {
                members.remove(i);
                found = true;
                System.out.println("Member deleted successfully!\n");
                memberDao.saveToFile(members); //Save after deleting
                break;
            }
        }
        
        if (!found) {
            System.out.println("Member with ID " + idToDelete + " not found.\n");
        }
    }
}


class InvalidDateException extends Exception{
	private static final long serialVersionUID = 1L;

	public InvalidDateException(String message) {
		super(message);
	}
}