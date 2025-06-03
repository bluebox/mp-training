package com.example;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Gym gym = new Gym(new HashMap<>());
		List<String> uniqueId=new ArrayList<>();
		Member mem = null;
		boolean x=true;
		do {
			System.out.println("1.Add new Gym member\n2.Add Membership Plan\n3.View All registered members\n4.Exit\n");
			System.out.println("Enter the choice : ");
			switch(sc.nextInt()) {
			case 1->{
				System.out.println("Enter the Gym ID :");
				String gymId=sc.next();
				if(uniqueId.contains(gymId)) {
					System.out.println("Member with "+gymId+" is already existed");
					break;
				}
				else {
					uniqueId.add(gymId);
				}
				System.out.println("Enter name :");
				String name=sc.next();
				System.out.println("Enter age :");
				int age=sc.nextInt();
				System.out.println("Health Condition");
				System.out.println("1.Perfectly Alright\n2.Allowed By Doctor\n,Not Suitable\n");
				HealthCondition[] h=HealthCondition.values();
				HealthCondition healthCondition = h[sc.nextInt()-1];
				System.out.println("Enter the experience in Gym :");
				int experience=sc.nextInt();
				List<String> foodHabits=new ArrayList<>();
				List<String> goals=new ArrayList<>();
				System.out.println("What are your timings\n");
				sc.next();
				String timings=sc.nextLine();
				System.out.println("The type of plans available are :");
				System.out.println("1.Basic\n2.Premium\n3.Gold\n");
				System.out.println("Enter the type of plan :");
				Plan plan=switch(sc.nextInt()) {
				case 1->Plan.Basic;
				case 2->Plan.Premium;
				case 3->Plan.Gold;
				default -> throw new IllegalArgumentException("Unexpected value: " + sc.nextInt());
				};
				int duration;
				double fee;
				if(plan.equals(Plan.Basic)) {
					duration=3;
					fee=6000;
				}
				else if(plan.equals(Plan.Premium)) {
					duration=6;
					fee=11000;
				}
				else {
					duration=12;
					fee=20000;
				}
				mem=new Member(gymId, name, age, healthCondition, experience, foodHabits, goals, timings, plan, duration, fee);
				gym.setGym(gymId,mem);
				System.out.println("Members are added successfully");
			}
			case 2->{
				System.out.println("The type of plans available are :");
				System.out.println("1.Basic\n2.Premium\n3.Gold\n");
				System.out.println("Enter the type of plan :");
				Plan plan=switch(sc.nextInt()) {
				case 1->Plan.Basic;
				case 2->Plan.Premium;
				case 3->Plan.Gold;
				default -> throw new IllegalArgumentException("Unexpected value: " + sc.nextInt());
				};
				int duration;
				double fee;
				if(plan.equals(Plan.Basic)) {
					duration=3;
					fee=6000;
				}
				else if(plan.equals(Plan.Premium)) {
					duration=6;
					fee=11000;
				}
				else {
					duration=12;
					fee=20000;
				}
				MembershipPlan mPlan=new MembershipPlan(plan,duration,fee);
				mem.setMembershipPlan(mPlan);
				System.out.println("Membership plan is added to the customer "+mem.getName());
			}
			case 3->gym.display();
			case 4->x=false;
			default->System.out.println("Please enter the correct choice");
			}
		}while(x);
	}
}
