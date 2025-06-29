package com.gym.classes;

import java.util.ArrayList;
import java.util.Scanner;

public class Gym {
	private ArrayList<Member> members;
	private static ArrayList<MembershipPlan> plans = new ArrayList<>();;
	private int lastId = 0; // using this to auto-generate Member id's
	
	static {
	    plans.add(new MembershipPlan("Basic", 3, 5000));
	    plans.add(new MembershipPlan("Premium", 6, 8000));
	    plans.add(new MembershipPlan("Gold", 12, 12000));
	}
	
	public Gym() {
		members = new ArrayList<Member>();
		
	}
	
	
	public void addNewMember( String name ,int age , int height , int weight) {
		lastId += 1;
		Member mem = new Member(lastId , name , age , height , weight);
		members.add(mem);
		System.out.println("User registered!\n");
		
	}
	
	public void showAllMembers() {
	    System.out.println("\n======================= Current Members =========================");
	    for (Member x : members) {
	        System.out.printf(
	            "ID:%d | Age: %d | Name: %s | Age: %d | Plan: %s\n", 
	            x.memberId,
	            x.age,
	            x.name, 
	            x.age, 
	            (x.memPlan == null ? "No Plan Assigned" : x.memPlan)         
	        );
	    }
	    System.out.println("=================================================================\n");
	}
	
	public void assignPlanToMember(int memberId) {
		boolean memberFound = false;
		System.out.println("Enter the plan id you want to assign:");
		for(int i=0; i<plans.size(); i++) {
			System.out.printf("%d.%s\n",i+1 , plans.get(i).planName);
		}
		Scanner sc = new Scanner(System.in);
		int planId=sc.nextInt()-1;
		for(Member x : members) {
			if(x.memberId == memberId) {
				x.memPlan = plans.get(planId);
				memberFound = true;
				System.out.println("Succefully assigned plan to user!\n");
			}
		}
		if(!memberFound) {
			System.out.println("Member not found please enter a valid id");
			System.out.println("\n");
		}
	}

}
