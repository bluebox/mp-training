package GymMembershipManagementSystem;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

public class Gym {
    public static Map<String,Member> members = new HashMap<>();
    private ArrayList<MembershipPlan> plans = new ArrayList<>();
    
    public Gym() {
        // Predefined plans for member ship 
        plans.add(new MembershipPlan("Monthly", 1, 400.00));
        plans.add(new MembershipPlan("Basic", 3, 1200.00));
        plans.add(new MembershipPlan("Premium", 6, 2300.00));
        plans.add(new MembershipPlan("Gold", 12, 4500.00));
    }

    public void addMember(String id, String name,String gender,LocalDate dob,double weight,LocalDate dateOfJoining) {
    	members.keySet().stream().forEach(s->{
    		if (s.equals(id)) {
            	System.out.println("	Already there is a member with the ID "+id+" please create with another ID...");
            	return;
            }
    	});
        members.put(id,new Member(id,name,gender,dob,weight,dateOfJoining));
        System.out.println("	"+name + " added successfully.Add his/her membership plan...");
    }

    public void assignPlanToMember(String memberId, int planIndex) {
        members.keySet().stream().filter(s->s.equals(memberId))
        .findFirst()
        .ifPresentOrElse(s->{
        	if (planIndex >= 0 && planIndex < plans.size()) {
                members.get(s).assignPlan(plans.get(planIndex));
                System.out.println("	Plan assigned successfully to "+members.get(s).getName());
            } else {
                System.out.println("	Invalid plan index,Please choose the plane (0-3) only..");
            }
        }, ()->{
        	System.out.println("	Member not found on this ID "+memberId+".Please register first...");
        });
    }
    public void showAllPlans() {
    	IntStream.range(0, plans.size()).forEach(i->System.out.println(i+" "+plans.get(i)));
    }
    
    public void viewAllMembers() {
        
        if (members.isEmpty()) {
            System.out.println("--------------------------------------------------------------");

            System.out.println("	No members registered.");
            
            System.out.println("--------------------------------------------------------------");
        } 
        else {
            members.values().stream().forEach(Member::showDetails);
        }

    }

}