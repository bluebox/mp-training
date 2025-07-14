package GymMembershipManagementSystem;

import java.util.ArrayList;

public class Gym {
	private ArrayList<Member> members;
    private ArrayList<MembershipPlan> plans;

    public Gym() {
        members = new ArrayList<>();
        plans = new ArrayList<>();
        plans.add(new MembershipPlan("Basic", 1, 500));
        plans.add(new MembershipPlan("Premium", 3, 1200));
        plans.add(new MembershipPlan("Gold", 6, 2000));
    }

    public void addMember(int memberId, String name, int age) {
        members.add(new Member(name, age, memberId));
        System.out.println("\nMember added successfully!");
    }

    public void assignPlanToMember(int memberId, int planIndex) {
        try {
            Member member = findMemberById(memberId);
            if (member != null && planIndex >= 0 && planIndex < plans.size()) {
                member.setPlan(plans.get(planIndex));
                System.out.println("Plan assigned successfully!");
            } else {
                System.out.println("Invalid member ID or plan index.");
            }
        } catch (Exception e) {
            System.out.println("Error while assigning plan: " + e.getMessage());
        }
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

    public void listPlans() {
        for (int i = 0; i < plans.size(); i++) {
            System.out.println(i + ": " + plans.get(i).toString());
        }
    }

    public Member findMemberById(int id) {
        for (Member m : members) {
            if (m.getMemberId() == id) return m;
        }
        return null;
    }
}
