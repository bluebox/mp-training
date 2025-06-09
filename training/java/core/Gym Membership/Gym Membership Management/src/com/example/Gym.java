package com.example;

import java.util.*;

public class Gym {
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<MembershipPlan> plans = new ArrayList<>();
    
    public Gym() {
        // Predefined plans for member ship 
        plans.add(new MembershipPlan("Monthly", 1, 400.00));
        plans.add(new MembershipPlan("Basic", 3, 1200.00));
        plans.add(new MembershipPlan("Premium", 6, 2300.00));
        plans.add(new MembershipPlan("Gold", 12, 4500.00));
    }

    public void addMember(String id, String name,String gender,String dob,int age,double weight,String dateOfJoining) {
    	for (Member member : members) {
            if (member.getMemberId().equals(id)) {
            	System.out.println("	Already there is a member with the ID "+id+" please create with another ID...");
            	return;
            }
           
    	}
         members.add(new Member(id,name,gender,dob,age,weight,dateOfJoining));
          System.out.println("	"+name + " added successfully.Add his/her membership plan...");
    }

    public void assignPlanToMember(String memberId, int planIndex) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                if (planIndex >= 0 && planIndex < plans.size()) {
                    member.assignPlan(plans.get(planIndex));
                    System.out.println("	Plan assigned successfully to "+member.getName());
                } else {
                    System.out.println("	Invalid plan index,Please choose the plane (0-3) only..");
                }
                return;
            }
        }
        System.out.println("	Member not found on this ID "+memberId+".Please register first...");
    }

    public void showAllPlans() {
    	for (int i = 0; i < plans.size(); i++) {
    		System.out.println(i + ". " + plans.get(i));
    	}
    }
    
    public void viewAllMembers() {
        
        if (members.isEmpty()) {
            System.out.println("--------------------------------------------------------------");

            System.out.println("	No members registered.");
            
            System.out.println("--------------------------------------------------------------");
        } 
        else {
            
            for (Member member : members) {
                member.showDetails();
            }
        }

    }

}
