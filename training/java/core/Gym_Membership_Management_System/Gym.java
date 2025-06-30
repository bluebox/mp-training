package Gym_Membership_Management_System;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
public class Gym {
	 private ArrayList<Member> members = new ArrayList<>();
	 private ArrayList<MembershiPlan> plans = new ArrayList<>();
	 private static final String FILE_NAME = "members.txt";
	 public Gym() {
		 loadMembersFromFile();
	     plans.add(new MembershiPlan("Basic", 3, 100));
	     plans.add(new MembershiPlan("Premium", 6, 180));
	     plans.add(new MembershiPlan("Gold", 12, 300));
	 }
	    public void addMember(Member member) {
	        members.add(member);
	        saveMembersToFile();
	        System.out.println("Member added successfully.");
	    }
	    public void saveMembersToFile() {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
	            for (Member m : members) {
	                writer.write(m.toFileString());
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            System.out.println("Error writing to file: " + e.getMessage());
	        }
	    }
	    public void loadMembersFromFile() {
	        File file = new File(FILE_NAME);
	        if (!file.exists()) return;

	        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                Member m = Member.fromFileString(line);
	                members.add(m);
	            }
	        } catch (IOException e) {
	            System.out.println("Error reading from file: " + e.getMessage());
	        }
	    }

	    public ArrayList<Member> getMembers() {
	        return members;
	    }

	    public ArrayList<MembershiPlan> getPlans() {
	        return plans;
	    }

	    public Member getMemberById(int id) {
	        for (Member m : members) {
	            if (m.getMemberId() == id) {
	                return m;
	            }
	        }
	        return null;
	    }
	
}
