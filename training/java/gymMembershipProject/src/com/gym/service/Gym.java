package com.gym.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import com.gym.classes.Member;
import com.gym.classes.MembershipPlan;
import com.gym.dao.MemberDao;
import com.gym.dao.MemberFileDao;


//add plans in file and fetch
//add object validation in service
//change auto id to customer constructor
//file handling exception handling in main (throw error from dao handle in service)
//send only one member to save not the entire list

public class Gym {
    private ArrayList<Member> members;
    private static ArrayList<MembershipPlan> plans = new ArrayList<>();
    private MemberDao memberDao = new MemberFileDao();
    private int lastId = 0;
    
    //private static final String FILE_NAME = "members.txt";

    static {
        plans.add(new MembershipPlan("Basic", 3, 5000));
        plans.add(new MembershipPlan("Premium", 6, 8000));
        plans.add(new MembershipPlan("Gold", 12, 12000));
    }

    public Gym() {
        members = new ArrayList<>();
        loadDataFromFile();
    }

    public void addNewMember(String name, int age, int height, int weight) {
    	loadDataFromFile();
        lastId += 1;
        Member mem = new Member(lastId, name, age, height, weight);
        members.add(mem);
        memberDao.saveToFile(members);
        System.out.println("User registered!\n");
    }

    public ArrayList<Member> showAllMembers() {
    	loadDataFromFile();
    	return members;
    }
    
    public void displayPlans() {
    	for (int i = 0; i < plans.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, plans.get(i).planName);
        }
    }
    
    public void assignPlanToMember(int memberId, int planId,String date) throws InvalidDateException {
    	members = memberDao.loadFromFile();
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
            		double perDay = currentPlan.getFee()/(currentPlan.getDurationMonths()*30.00d);

            		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            		            		
                    LocalDate currDate = LocalDate.parse(date, formatter);
                    LocalDate prevDate = LocalDate.parse(x.getJoinDate(), formatter);
                                     
                    if(prevDate.isAfter(currDate)) {
                    	System.out.println("Cannot enter date earlier than the previous date\n");
                    	throw new InvalidDateException("The date you entered is invalid");
                    	
                    }
                    
                    long daysBetween = ChronoUnit.DAYS.between(prevDate,currDate);
                    long daysRemaining = currentPlan.getDurationMonths()*30 - daysBetween;
                    
                    if(daysRemaining > 0) {
                    	double payable = targetPlan.getFee() - (daysRemaining*perDay);
                        System.out.printf("Member only had to pay %.2f\n",payable);
                    }
            	}
                x.setMemPlan(targetPlan);
                x.setJoinDate(date);
                System.out.println("Successfully assigned plan to user!\n");
                memberDao.saveToFile(members);
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
    	for(Member member : members) {
    		if(member.getMemberId() == id) {
    			return member;
    		}
    	}

    	return null;
    }
    
    private void loadDataFromFile() {
    	members = memberDao.loadFromFile();
    	//get back the max id
    	int maxId = lastId;
    	for(Member member : members) {
    		if(member.getMemberId() > maxId) {
    			maxId = member.getMemberId();
    		}
    	}
    	lastId = maxId;
    }
    
    public void deleteMember(int idToDelete) {
    	
    	loadDataFromFile();
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




