package com.gym.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.gym.dao.MemberSqlDaoImpl;
import com.gym.exceptions.InvalidDateException;
import com.gym.models.Member;
import com.gym.models.MembershipPlan;


public class GymServiceImpl implements GymService {
    
    private static ArrayList<MembershipPlan> plans ;
    private MemberSqlDaoImpl memberDao;
    
    static {
    	plans = new ArrayList<>();
        plans.add(new MembershipPlan("Basic", 3, 5000));
        plans.add(new MembershipPlan("Premium", 6, 8000));
        plans.add(new MembershipPlan("Gold", 12, 12000));
    }
    
    public GymServiceImpl() {
    	memberDao = new MemberSqlDaoImpl();
    }

    @Override
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
    
    @Override
    public boolean validateNewMember(Member newMember) {
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
    
    @Override
	public void showAllMembers() {
    	List<Member> members = memberDao.loadMembers() ;
    	if(members.isEmpty()) {
    		System.out.println("\nno members exist in the database\n");
    		return;
    	}
    	
        System.out.println("\n=========================== Current Members =============================");
        System.out.printf(
    	        "%-6s %-20s %-4s %-6s %-7s %-10s %-15s%n",
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
    
    @Override
    public void assignPlanToMember(int memberId, int planId,String date) throws InvalidDateException {
    	List<Member> members = memberDao.loadMembers();
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
            	MembershipPlan targetPlan = plans.get(planId-1);
            	MembershipPlan currentPlan = member.getMembershipPlan();
            	
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
                    LocalDate prevDate = LocalDate.parse(member.getJoinDate(), formatter);
                                     
                    if(prevDate.isAfter(currDate)) {
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
                member.setMemPlan(targetPlan);
                member.setJoinDate(date);
                System.out.println("Successfully assigned plan to user!\n");
                memberDao.updateMember(member);
                return ;
            }
        }
                System.out.println("Member not found. Please enter a valid id.\n");

    }

    public static ArrayList<MembershipPlan> getPlans() {
        return plans;
    }

    public ArrayList<Member> getMembers() {
        return memberDao.loadMembers();
    }

    public Member getMemberById(int id) {
    	List<Member> members = memberDao.loadMembers();
    	for(Member member : members) {
    		if(member.getMemberId() == id) {
    			return member;
    		}
    	}

    	return null;
    }
    
    @Override
    public boolean deleteMember(int idToDelete) {
    	return memberDao.deleteMember(idToDelete);
    }
}


