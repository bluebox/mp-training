package Project;

import java.util.ArrayList;

public class Gym {
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<MembershipPlan> plans = new ArrayList<>();

    public void addNewMember(Member member) {
        members.add(member);
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void addPlan(MembershipPlan plan) {
        plans.add(plan);
    }

    public ArrayList<MembershipPlan> getPlans() {
        return plans;
    }

    public Member getMemberById(int id) {
        for (Member m : members) {
            if (m.getMemberId() == id) return m;
        }
        return null;
    }
    
    public void assignMembershipPlan(int id, MembershipPlan mp) {
    	Member existingMember=this.getMemberById(id);
    	existingMember.setMembershipPlan(mp);
    }
}
