package Gym_Membership_Management_System;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GymDAO {

	 private static final String FILE_NAME = "members.txt";
	 public GymDAO(ArrayList<Member> members) {
		 loadMembersFromFile(members);
	 }
	    public void addMember(ArrayList<Member> members) {
	        saveMembersToFile(members);
	        System.out.println("Member added successfully.");
	    }
	    public void saveMembersToFile(ArrayList<Member> members) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
	            for (Member m : members) {
	                writer.write(m.toFileString());
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            System.out.println("Error writing to file: " + e.getMessage());
	        }
	    }
	    public ArrayList<Member> loadMembersFromFile(ArrayList<Member> defaultMembers) {
	        File file = new File(FILE_NAME);
	        if (!file.exists()) return null;

	        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                Member m = Member.fromFileString(line);
	                defaultMembers.add(m);
	            }
	            
	        } catch (IOException e) {
	            System.out.println("Error reading from file: " + e.getMessage());
	        }
	        return defaultMembers;
	    }
	
}
