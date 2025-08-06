import java.util.ArrayList;

public class Gym {
	
	private ArrayList<Member> members;
	private ArrayList<MembershipPlan> plans;
	
	public Gym() {
		members = new ArrayList<>();
		plans = new ArrayList<>();
		
		plans.add(new MembershipPlan("Basic", 6, 10000));
        plans.add(new MembershipPlan("Premium", 9, 20000));
        plans.add(new MembershipPlan("Gold", 12, 25000));
		
	}
	
	public void showAvailablePlans() {
        for (int i = 0; i < plans.size(); i++) {
            System.out.println((i + 1) + ". " + plans.get(i).toString());
        }
    }
	
	public void addMember(Member member) {
		members.add(member);
		System.out.println("Member added successfully!");
	}
	
	public void viewAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered yet.");
        } else {
            for (Member m : members) {
                m.showDetails();
            }
        }
    }
	
	public void assignPlanToMember(String memberId, int planChoice) {
        Member member = findMemberById(memberId);
        if (member != null) {
            if (planChoice >= 1 && planChoice <= plans.size()) {
                member.setPlan(plans.get(planChoice - 1));
                System.out.println("Plan assigned successfully!");
            } else {
                System.out.println("Invalid plan choice.");
            }
        } else {
            System.out.println("Member not found.");
        }
    }
	
	public Member findMemberById(String memberId) {
		
        for (Member m : members) {
            if (m.getMemberId().equals(memberId)) {
                return m;
            }
        }
        return null;
    }
	
}
