package training.java.core.Gym_Management_System;
import java.util.*;

class Gym {
    private List<Member> members = new ArrayList<>();
    private List<MembershipPlan> plans = new ArrayList<>();

    public Gym() {
        plans.add(new MembershipPlan("Basic", 1, 500));
        plans.add(new MembershipPlan("Premium", 3, 1200));
        plans.add(new MembershipPlan("Gold", 6, 2000));
    }

    public void addMember(Member member) {
        members.add(member);
        System.out.println("Member added successfully!");
    }

    public void assignPlanToMember(int memberId, int planIndex) {
        for (Member m : members) {
            if (m.getMemberId() == memberId) {
                if (planIndex >= 0 && planIndex < plans.size()) {
                    m.assignPlan(plans.get(planIndex));
                    System.out.println("Plan assigned successfully!");
                    return;
                }
            }
        }
        System.out.println("Member or Plan not found!");
    }

    public void showAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
        }
        for (Member m : members) {
            m.showDetails();
            System.out.println("------");
        }
    }

    public void showPlans() {
        for (int i = 0; i < plans.size(); i++) {
            System.out.println(i + ": " + plans.get(i).getDetails());
        }
    }
}
