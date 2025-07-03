package GymPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GymFiles implements Interface_DAO{

	private ArrayList<Member> members = new ArrayList<>();
	 private ArrayList<MembershipPlan> plans = new ArrayList<>();
	 private static final String FILE_NAME = "members.txt";
	 public GymFiles() {
		 load();
	     plans.add(new MembershipPlan("Basic", 3, 100));
	     plans.add(new MembershipPlan("Premium", 6, 180));
	     plans.add(new MembershipPlan("Gold", 12, 300));
	 }
	    public void addMember(Member member) {
	        members.add(member);
	        save();
	        System.out.println("Member added successfully.");
	    }
	    public void save() {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
	            for (Member m : members) {
	                writer.write(m.toFileString());
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            System.out.println("Error writing to file: " + e.getMessage());
	        }
	    }
	    public void load() {
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

	    public ArrayList<MembershipPlan> getPlans() {
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
		@Override
		public void saveMembersToFile() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void loadMembersFromFile() {
			// TODO Auto-generated method stub
			
		}

}
