package gym.membership_Management;

import java.util.ArrayList;
import java.util.List;

public class Gym {
	private List<Member> m;
	private List<MembershipPlan> p;
	public Gym() {
		m=new ArrayList<>();
		p=new ArrayList<>();
		p.add(new MembershipPlan("Basic", 30, 999));
	    p.add(new MembershipPlan("Gold", 90, 2499));
	    p.add(new MembershipPlan("Premium", 180, 4999));	
	}
	
	public MembershipPlan getPlanByName(String name) {
		for(MembershipPlan plan:p) {
			if(plan.getNameOfPlan().equalsIgnoreCase(name)) {
				return plan;
			}
		}
		System.out.println("No plan found with the given name "+name);
		return null;
	}
	
	public void addMember(Member member) {
		m.add(member);
	}
	
	public void exitGymMember(int Id) {
		m.removeIf(member -> member.getMembershipId() == Id);
	}
	
	public void registeredMembers() {
		if(m.isEmpty()) {
			System.out.println("Registrations not found!!");
		}
		else {
			for(Member person:m) {
				person.showDetails();
			}
		}
	}
	
	public void plansAvailable() {
		for(MembershipPlan plan:p) {
			System.out.println("plan: "+plan.getNameOfPlan()+", Duration: "+plan.getDurationInDays()+", fee: "+plan.getFee()+"$");
		}
	}
	
}
