package GymPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
public class Gym {
	
	 public static final boolean files = true;
	 Interface_DAO fileHandling;
	 public Gym() {
		 this.fileHandling=files?new GymFiles():new GymCollections();
	 }
	    public void addMember(Member member) {
	        fileHandling.addMember(member);
	    }
	    public void saveMembersToFile() {
	    		fileHandling.save();
	    }
	    public void loadMembersFromFile() {
	        fileHandling.load();
	    }

	    public ArrayList<Member> getMembers() {
	        return fileHandling.getMembers();
	    }

	    public ArrayList<MembershipPlan> getPlans() {
	        return fileHandling.getPlans();
	    }

	    public Member getMemberById(int id) {
	        return fileHandling.getMemberById(id);
	    }
	
}
