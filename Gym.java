package Default;

import java.util.ArrayList;

public class Gym {
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<MembershipPlan> plans = new ArrayList<>();

    public void addPlan(MembershipPlan plan) {
        plans.add(plan);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public Member getMemberById(String id) {
        for (Member m : members) {
            if (m.getMemberId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public MembershipPlan getPlanByName(String name) {
        for (MembershipPlan p : plans) {
            if (p.getPlanName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }
    
    public void showAllMembers() {
        for (Member m : members) {
            m.showDetails();
            System.out.println("------");
        }
    }
}
