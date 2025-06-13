package CaseStudy_Gym;

import java.util.ArrayList;

public class Gym {
	private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<MembershipPlan> MembershipPlans = new ArrayList<>();

    public Gym() {
        MembershipPlans.add(new MembershipPlan("Basic", 1, 30.0));
        MembershipPlans.add(new MembershipPlan("Premium", 3, 75.0));
        MembershipPlans.add(new MembershipPlan("Gold", 6, 120.0));
    }
    public void addMember(Member member) {
        members.add(member);
    }
    public Member findMemberById(String memberId) {
        for (Member m : members) {
            if (m.getMemberId().equalsIgnoreCase(memberId)) {
                return m;
            }
        }
        return null;
    }
    public void assignPlan(String memberId, int MembershipPlanIndex) {
        Member member = findMemberById(memberId);
        if (member != null && MembershipPlanIndex >= 0 && MembershipPlanIndex < MembershipPlans.size()) {
            member.setMembershipPlan(MembershipPlans.get(MembershipPlanIndex));
            System.out.println("MembershipPlan assigned successfully.");
        } else {
            System.out.println("Invalid member ID or MembershipPlan index.");
        }
    }
    public void showAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
        } else {
            for (Member m : members) {
                System.out.println("------------------------");
                m.showDetails();
            }
        }
    }
    public void showAvailablePlans() {
        for (int i = 0; i < MembershipPlans.size(); i++) {
            System.out.println(i + ". " + MembershipPlans.get(i));
        }
    }
}
