package com.gym.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.gym.models.Member;
import com.gym.models.MembershipPlan;

public class MemberFileDao implements MemberDao{
	private static ArrayList<MembershipPlan> plans = new ArrayList<>();
	
	private int lastId;
	
	public boolean saveNewMember(Member member) {
		List<Member> members = loadFromFile();
		member.setMemberId(++lastId);
		members.add(member);
		//tryFileWriter fw = new FileWriter("members.txt",true);
		
		
		if(!saveToFile((ArrayList<Member>) members)) {
			return false;
		};
		return true;
	}
	
	@Override
	public boolean saveToFile(List<Member> members) {
		try (PrintWriter pw = new PrintWriter(new FileWriter("members.txt"))) {
            for (Member m : members) {
                pw.println(
                        m.getMemberId() + "," + m.getMemberName() + "," + m.getMemberAge() + "," + m.getMemberHeight() + "," + m.getMemberWeight() + "," +
                                (m.getMembershipPlan() != null ? m.getMembershipPlan().planName : "NoPlan")+ "," + m.getJoinDate()
                );
            }
            return true;
            
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
            return false;
        }
	}
	
	static {
        plans.add(new MembershipPlan("Basic", 3, 5000));
        plans.add(new MembershipPlan("Premium", 6, 8000));
        plans.add(new MembershipPlan("Gold", 12, 12000));
    }
	
	public ArrayList<Member> loadFromFile(){
        File file = new File("members.txt");
        ArrayList<Member> members = new ArrayList<Member>();
        if (!file.exists()) return null;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        	
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    int height =	 Integer.parseInt(parts[3]);
                    int weight = Integer.parseInt(parts[4]);
                    String planName = parts[5];
                    String dateOfJoining = parts[6];

                    Member m = new Member(id, name, age, height, weight,dateOfJoining);
                    if (!planName.equals("NoPlan")) {
                        for (MembershipPlan p : plans) {
                            if (p.planName.equalsIgnoreCase(planName)) {
                                m.setMemPlan(p);
                                break;
                            }
                        }
                    }
                    members.add(m);
                    if (id > lastId) lastId = id; // Update lastId to avoid duplicate IDs
                }
            }
            //System.out.println("return number of members "+members.size());
            return members;
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
		return members;

	}
	
}
